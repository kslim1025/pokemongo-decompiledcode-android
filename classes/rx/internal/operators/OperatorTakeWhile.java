package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorTakeWhile<T>
  implements Observable.Operator<T, T>
{
  private final Func2<? super T, ? super Integer, Boolean> predicate;
  
  public OperatorTakeWhile(Func1<? super T, Boolean> paramFunc1)
  {
    this(new Func2()
    {
      public Boolean call(T paramAnonymousT, Integer paramAnonymousInteger)
      {
        return (Boolean)OperatorTakeWhile.this.call(paramAnonymousT);
      }
    });
  }
  
  public OperatorTakeWhile(Func2<? super T, ? super Integer, Boolean> paramFunc2)
  {
    this.predicate = paramFunc2;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    Subscriber local2 = new Subscriber(paramSubscriber, false)
    {
      private int counter = 0;
      private boolean done = false;
      
      public void onCompleted()
      {
        if (!this.done) {
          paramSubscriber.onCompleted();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (!this.done) {
          paramSubscriber.onError(paramAnonymousThrowable);
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        try
        {
          Func2 localFunc2 = OperatorTakeWhile.this.predicate;
          int i = this.counter;
          this.counter = (i + 1);
          boolean bool = ((Boolean)localFunc2.call(paramAnonymousT, Integer.valueOf(i))).booleanValue();
          if (bool)
          {
            paramSubscriber.onNext(paramAnonymousT);
            return;
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            this.done = true;
            Exceptions.throwIfFatal(localThrowable);
            paramSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
            unsubscribe();
            continue;
            this.done = true;
            paramSubscriber.onCompleted();
            unsubscribe();
          }
        }
      }
    };
    paramSubscriber.add(local2);
    return local2;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTakeWhile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */