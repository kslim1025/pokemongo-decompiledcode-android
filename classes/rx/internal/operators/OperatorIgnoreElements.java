package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;

public class OperatorIgnoreElements<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorIgnoreElements<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    Subscriber local1 = new Subscriber()
    {
      public void onCompleted()
      {
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT) {}
    };
    paramSubscriber.add(local1);
    return local1;
  }
  
  private static class Holder
  {
    static final OperatorIgnoreElements<?> INSTANCE = new OperatorIgnoreElements(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorIgnoreElements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */