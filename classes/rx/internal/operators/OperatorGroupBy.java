package rx.internal.operators;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public class OperatorGroupBy<T, K, R>
  implements Observable.Operator<GroupedObservable<K, R>, T>
{
  private static final Func1<Object, Object> IDENTITY = new Func1()
  {
    public Object call(Object paramAnonymousObject)
    {
      return paramAnonymousObject;
    }
  };
  private static final Object NULL_KEY = new Object();
  final Func1<? super T, ? extends K> keySelector;
  final Func1<? super T, ? extends R> valueSelector;
  
  public OperatorGroupBy(Func1<? super T, ? extends K> paramFunc1)
  {
    this(paramFunc1, IDENTITY);
  }
  
  public OperatorGroupBy(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends R> paramFunc11)
  {
    this.keySelector = paramFunc1;
    this.valueSelector = paramFunc11;
  }
  
  public Subscriber<? super T> call(Subscriber<? super GroupedObservable<K, R>> paramSubscriber)
  {
    return new GroupBySubscriber(this.keySelector, this.valueSelector, paramSubscriber);
  }
  
  static final class GroupBySubscriber<K, T, R>
    extends Subscriber<T>
  {
    static final AtomicLongFieldUpdater<GroupBySubscriber> BUFFERED_COUNT = AtomicLongFieldUpdater.newUpdater(GroupBySubscriber.class, "bufferedCount");
    static final AtomicIntegerFieldUpdater<GroupBySubscriber> COMPLETION_EMITTED_UPDATER;
    private static final int MAX_QUEUE_SIZE = 1024;
    static final AtomicLongFieldUpdater<GroupBySubscriber> REQUESTED;
    static final AtomicIntegerFieldUpdater<GroupBySubscriber> TERMINATED_UPDATER;
    private static final int TERMINATED_WITH_COMPLETED = 1;
    private static final int TERMINATED_WITH_ERROR = 2;
    private static final int UNTERMINATED;
    static final AtomicIntegerFieldUpdater<GroupBySubscriber> WIP_FOR_UNSUBSCRIBE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "wipForUnsubscribe");
    private static final NotificationLite<Object> nl = NotificationLite.instance();
    volatile long bufferedCount;
    final Subscriber<? super GroupedObservable<K, R>> child;
    volatile int completionEmitted;
    final Func1<? super T, ? extends R> elementSelector;
    private final ConcurrentHashMap<Object, GroupState<K, T>> groups = new ConcurrentHashMap();
    final Func1<? super T, ? extends K> keySelector;
    volatile long requested;
    final GroupBySubscriber<K, T, R> self = this;
    volatile int terminated = 0;
    volatile int wipForUnsubscribe = 1;
    
    static
    {
      COMPLETION_EMITTED_UPDATER = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "completionEmitted");
      TERMINATED_UPDATER = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "terminated");
      REQUESTED = AtomicLongFieldUpdater.newUpdater(GroupBySubscriber.class, "requested");
    }
    
    public GroupBySubscriber(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends R> paramFunc11, Subscriber<? super GroupedObservable<K, R>> paramSubscriber)
    {
      this.keySelector = paramFunc1;
      this.elementSelector = paramFunc11;
      this.child = paramSubscriber;
      paramSubscriber.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          if (OperatorGroupBy.GroupBySubscriber.WIP_FOR_UNSUBSCRIBE_UPDATER.decrementAndGet(OperatorGroupBy.GroupBySubscriber.this.self) == 0) {
            OperatorGroupBy.GroupBySubscriber.this.self.unsubscribe();
          }
        }
      }));
    }
    
    private void cleanupGroup(Object paramObject)
    {
      GroupState localGroupState = (GroupState)this.groups.remove(paramObject);
      if (localGroupState != null)
      {
        if (!localGroupState.buffer.isEmpty()) {
          BUFFERED_COUNT.addAndGet(this.self, -localGroupState.buffer.size());
        }
        completeInner();
        requestMoreIfNecessary();
      }
    }
    
    private void completeInner()
    {
      if (WIP_FOR_UNSUBSCRIBE_UPDATER.decrementAndGet(this) == 0) {
        unsubscribe();
      }
      for (;;)
      {
        return;
        if ((this.groups.isEmpty()) && (this.terminated == 1) && (COMPLETION_EMITTED_UPDATER.compareAndSet(this, 0, 1))) {
          this.child.onCompleted();
        }
      }
    }
    
    private GroupState<K, T> createNewGroup(final Object paramObject)
    {
      final GroupState localGroupState = new GroupState(null);
      GroupedObservable localGroupedObservable = GroupedObservable.create(getKey(paramObject), new Observable.OnSubscribe()
      {
        public void call(final Subscriber<? super R> paramAnonymousSubscriber)
        {
          paramAnonymousSubscriber.setProducer(new Producer()
          {
            public void request(long paramAnonymous2Long)
            {
              OperatorGroupBy.GroupBySubscriber.this.requestFromGroupedObservable(paramAnonymous2Long, OperatorGroupBy.GroupBySubscriber.2.this.val$groupState);
            }
          });
          final AtomicBoolean localAtomicBoolean = new AtomicBoolean();
          localGroupState.getObservable().doOnUnsubscribe(new Action0()
          {
            public void call()
            {
              if (localAtomicBoolean.compareAndSet(false, true)) {
                OperatorGroupBy.GroupBySubscriber.this.cleanupGroup(OperatorGroupBy.GroupBySubscriber.2.this.val$key);
              }
            }
          }).unsafeSubscribe(new Subscriber(paramAnonymousSubscriber)
          {
            public void onCompleted()
            {
              paramAnonymousSubscriber.onCompleted();
              if (localAtomicBoolean.compareAndSet(false, true)) {
                OperatorGroupBy.GroupBySubscriber.this.cleanupGroup(OperatorGroupBy.GroupBySubscriber.2.this.val$key);
              }
            }
            
            public void onError(Throwable paramAnonymous2Throwable)
            {
              paramAnonymousSubscriber.onError(paramAnonymous2Throwable);
              if (localAtomicBoolean.compareAndSet(false, true)) {
                OperatorGroupBy.GroupBySubscriber.this.cleanupGroup(OperatorGroupBy.GroupBySubscriber.2.this.val$key);
              }
            }
            
            public void onNext(T paramAnonymous2T)
            {
              try
              {
                paramAnonymousSubscriber.onNext(OperatorGroupBy.GroupBySubscriber.this.elementSelector.call(paramAnonymous2T));
                return;
              }
              catch (Throwable localThrowable)
              {
                for (;;)
                {
                  onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymous2T));
                }
              }
            }
            
            public void onStart() {}
          });
        }
      });
      int i = this.wipForUnsubscribe;
      if (i <= 0) {
        localGroupState = null;
      }
      for (;;)
      {
        return localGroupState;
        if (!WIP_FOR_UNSUBSCRIBE_UPDATER.compareAndSet(this, i, i + 1)) {
          break;
        }
        if ((GroupState)this.groups.putIfAbsent(paramObject, localGroupState) != null) {
          throw new IllegalStateException("Group already existed while creating a new one");
        }
        this.child.onNext(localGroupedObservable);
      }
    }
    
    private void drainIfPossible(GroupState<K, T> paramGroupState)
    {
      while (paramGroupState.requested.get() > 0L)
      {
        Object localObject = paramGroupState.buffer.poll();
        if (localObject == null) {
          break;
        }
        Observer localObserver = paramGroupState.getObserver();
        nl.accept(localObserver, localObject);
        if (paramGroupState.requested.get() != Long.MAX_VALUE) {
          paramGroupState.requested.decrementAndGet();
        }
        BUFFERED_COUNT.decrementAndGet(this);
        requestMoreIfNecessary();
      }
    }
    
    private void emitItem(GroupState<K, T> paramGroupState, Object paramObject)
    {
      Queue localQueue = paramGroupState.buffer;
      AtomicLong localAtomicLong = paramGroupState.requested;
      REQUESTED.decrementAndGet(this);
      if ((localAtomicLong != null) && (localAtomicLong.get() > 0L) && ((localQueue == null) || (localQueue.isEmpty())))
      {
        Observer localObserver = paramGroupState.getObserver();
        nl.accept(localObserver, paramObject);
        if (localAtomicLong.get() != Long.MAX_VALUE) {
          localAtomicLong.decrementAndGet();
        }
      }
      for (;;)
      {
        requestMoreIfNecessary();
        return;
        localQueue.add(paramObject);
        BUFFERED_COUNT.incrementAndGet(this);
        if (paramGroupState.count.getAndIncrement() == 0L) {
          pollQueue(paramGroupState);
        }
      }
    }
    
    private K getKey(Object paramObject)
    {
      if (paramObject == OperatorGroupBy.NULL_KEY) {
        paramObject = null;
      }
      return (K)paramObject;
    }
    
    private Object groupedKey(K paramK)
    {
      if (paramK == null) {
        paramK = OperatorGroupBy.NULL_KEY;
      }
      return paramK;
    }
    
    private void pollQueue(GroupState<K, T> paramGroupState)
    {
      do
      {
        drainIfPossible(paramGroupState);
        if (paramGroupState.count.decrementAndGet() > 1L) {
          paramGroupState.count.set(1L);
        }
      } while (paramGroupState.count.get() > 0L);
    }
    
    private void requestMoreIfNecessary()
    {
      if ((REQUESTED.get(this) == 0L) && (this.terminated == 0))
      {
        long l = 1024L - BUFFERED_COUNT.get(this);
        if ((l > 0L) && (REQUESTED.compareAndSet(this, 0L, l))) {
          request(l);
        }
      }
    }
    
    public void onCompleted()
    {
      if (TERMINATED_UPDATER.compareAndSet(this, 0, 1))
      {
        Iterator localIterator = this.groups.values().iterator();
        while (localIterator.hasNext()) {
          emitItem((GroupState)localIterator.next(), nl.completed());
        }
        if ((this.groups.isEmpty()) && (COMPLETION_EMITTED_UPDATER.compareAndSet(this, 0, 1))) {
          this.child.onCompleted();
        }
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (TERMINATED_UPDATER.compareAndSet(this, 0, 2))
      {
        Iterator localIterator = this.groups.values().iterator();
        while (localIterator.hasNext()) {
          emitItem((GroupState)localIterator.next(), nl.error(paramThrowable));
        }
      }
      try
      {
        this.child.onError(paramThrowable);
        return;
      }
      finally
      {
        unsubscribe();
      }
    }
    
    public void onNext(T paramT)
    {
      try
      {
        Object localObject = groupedKey(this.keySelector.call(paramT));
        GroupState localGroupState = (GroupState)this.groups.get(localObject);
        if (localGroupState == null)
        {
          if (!this.child.isUnsubscribed()) {
            localGroupState = createNewGroup(localObject);
          }
        }
        else if (localGroupState != null) {
          emitItem(localGroupState, nl.next(paramT));
        }
      }
      catch (Throwable localThrowable)
      {
        onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
      }
    }
    
    public void onStart()
    {
      REQUESTED.set(this, 1024L);
      request(1024L);
    }
    
    void requestFromGroupedObservable(long paramLong, GroupState<K, T> paramGroupState)
    {
      BackpressureUtils.getAndAddRequest(paramGroupState.requested, paramLong);
      if (paramGroupState.count.getAndIncrement() == 0L) {
        pollQueue(paramGroupState);
      }
    }
    
    private static class GroupState<K, T>
    {
      private final Queue<Object> buffer = new ConcurrentLinkedQueue();
      private final AtomicLong count = new AtomicLong();
      private final AtomicLong requested = new AtomicLong();
      private final Subject<T, T> s = BufferUntilSubscriber.create();
      
      public Observable<T> getObservable()
      {
        return this.s;
      }
      
      public Observer<T> getObserver()
      {
        return this.s;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorGroupBy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */