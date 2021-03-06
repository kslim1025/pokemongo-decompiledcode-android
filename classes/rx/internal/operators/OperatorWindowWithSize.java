package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public final class OperatorWindowWithSize<T>
  implements Observable.Operator<Observable<T>, T>
{
  final int size;
  final int skip;
  
  public OperatorWindowWithSize(int paramInt1, int paramInt2)
  {
    this.size = paramInt1;
    this.skip = paramInt2;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    Object localObject;
    if (this.skip == this.size)
    {
      localObject = new ExactSubscriber(paramSubscriber);
      ((ExactSubscriber)localObject).init();
    }
    for (;;)
    {
      return (Subscriber<? super T>)localObject;
      InexactSubscriber localInexactSubscriber = new InexactSubscriber(paramSubscriber);
      localInexactSubscriber.init();
      localObject = localInexactSubscriber;
    }
  }
  
  static final class CountedSubject<T>
  {
    final Observer<T> consumer;
    int count;
    final Observable<T> producer;
    
    public CountedSubject(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      this.consumer = paramObserver;
      this.producer = paramObservable;
    }
  }
  
  final class InexactSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    final List<OperatorWindowWithSize.CountedSubject<T>> chunks = new LinkedList();
    int count;
    volatile boolean noWindow = true;
    
    public InexactSubscriber()
    {
      Subscriber localSubscriber;
      this.child = localSubscriber;
    }
    
    OperatorWindowWithSize.CountedSubject<T> createCountedSubject()
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      return new OperatorWindowWithSize.CountedSubject(localBufferUntilSubscriber, localBufferUntilSubscriber);
    }
    
    void init()
    {
      this.child.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          if (OperatorWindowWithSize.InexactSubscriber.this.noWindow) {
            OperatorWindowWithSize.InexactSubscriber.this.unsubscribe();
          }
        }
      }));
      this.child.setProducer(new Producer()
      {
        public void request(long paramAnonymousLong)
        {
          if (paramAnonymousLong > 0L)
          {
            long l = paramAnonymousLong * OperatorWindowWithSize.this.size;
            if ((l >>> 31 != 0L) && (l / paramAnonymousLong != OperatorWindowWithSize.this.size)) {
              l = Long.MAX_VALUE;
            }
            OperatorWindowWithSize.InexactSubscriber.this.requestMore(l);
          }
        }
      });
    }
    
    public void onCompleted()
    {
      ArrayList localArrayList = new ArrayList(this.chunks);
      this.chunks.clear();
      this.noWindow = true;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext()) {
        ((OperatorWindowWithSize.CountedSubject)localIterator.next()).consumer.onCompleted();
      }
      this.child.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      ArrayList localArrayList = new ArrayList(this.chunks);
      this.chunks.clear();
      this.noWindow = true;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext()) {
        ((OperatorWindowWithSize.CountedSubject)localIterator.next()).consumer.onError(paramThrowable);
      }
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      int i = this.count;
      this.count = (i + 1);
      if ((i % OperatorWindowWithSize.this.skip == 0) && (!this.child.isUnsubscribed()))
      {
        if (this.chunks.isEmpty()) {
          this.noWindow = false;
        }
        OperatorWindowWithSize.CountedSubject localCountedSubject2 = createCountedSubject();
        this.chunks.add(localCountedSubject2);
        this.child.onNext(localCountedSubject2.producer);
      }
      Iterator localIterator = this.chunks.iterator();
      while (localIterator.hasNext())
      {
        OperatorWindowWithSize.CountedSubject localCountedSubject1 = (OperatorWindowWithSize.CountedSubject)localIterator.next();
        localCountedSubject1.consumer.onNext(paramT);
        int j = 1 + localCountedSubject1.count;
        localCountedSubject1.count = j;
        if (j == OperatorWindowWithSize.this.size)
        {
          localIterator.remove();
          localCountedSubject1.consumer.onCompleted();
        }
      }
      if (this.chunks.isEmpty())
      {
        this.noWindow = true;
        if (this.child.isUnsubscribed()) {
          unsubscribe();
        }
      }
    }
    
    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }
  
  final class ExactSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    int count;
    volatile boolean noWindow = true;
    BufferUntilSubscriber<T> window;
    
    public ExactSubscriber()
    {
      Subscriber localSubscriber;
      this.child = localSubscriber;
    }
    
    void init()
    {
      this.child.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          if (OperatorWindowWithSize.ExactSubscriber.this.noWindow) {
            OperatorWindowWithSize.ExactSubscriber.this.unsubscribe();
          }
        }
      }));
      this.child.setProducer(new Producer()
      {
        public void request(long paramAnonymousLong)
        {
          if (paramAnonymousLong > 0L)
          {
            long l = paramAnonymousLong * OperatorWindowWithSize.this.size;
            if ((l >>> 31 != 0L) && (l / paramAnonymousLong != OperatorWindowWithSize.this.size)) {
              l = Long.MAX_VALUE;
            }
            OperatorWindowWithSize.ExactSubscriber.this.requestMore(l);
          }
        }
      });
    }
    
    public void onCompleted()
    {
      if (this.window != null) {
        this.window.onCompleted();
      }
      this.child.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.window != null) {
        this.window.onError(paramThrowable);
      }
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      if (this.window == null)
      {
        this.noWindow = false;
        this.window = BufferUntilSubscriber.create();
        this.child.onNext(this.window);
      }
      this.window.onNext(paramT);
      int i = 1 + this.count;
      this.count = i;
      if (i % OperatorWindowWithSize.this.size == 0)
      {
        this.window.onCompleted();
        this.window = null;
        this.noWindow = true;
        if (this.child.isUnsubscribed()) {
          unsubscribe();
        }
      }
    }
    
    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorWindowWithSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */