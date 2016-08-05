package rx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.annotations.Beta;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.functions.Functions;
import rx.internal.operators.OnSubscribeAmb;
import rx.internal.operators.OnSubscribeCache;
import rx.internal.operators.OnSubscribeCombineLatest;
import rx.internal.operators.OnSubscribeDefer;
import rx.internal.operators.OnSubscribeDelaySubscription;
import rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.operators.OnSubscribeGroupJoin;
import rx.internal.operators.OnSubscribeJoin;
import rx.internal.operators.OnSubscribeMulticastSelector;
import rx.internal.operators.OnSubscribeRange;
import rx.internal.operators.OnSubscribeRedo;
import rx.internal.operators.OnSubscribeSingle;
import rx.internal.operators.OnSubscribeTimerOnce;
import rx.internal.operators.OnSubscribeTimerPeriodically;
import rx.internal.operators.OnSubscribeToObservableFuture;
import rx.internal.operators.OnSubscribeUsing;
import rx.internal.operators.OperatorAll;
import rx.internal.operators.OperatorAny;
import rx.internal.operators.OperatorAsObservable;
import rx.internal.operators.OperatorBufferWithSingleObservable;
import rx.internal.operators.OperatorBufferWithSize;
import rx.internal.operators.OperatorBufferWithStartEndObservable;
import rx.internal.operators.OperatorBufferWithTime;
import rx.internal.operators.OperatorCast;
import rx.internal.operators.OperatorConcat;
import rx.internal.operators.OperatorDebounceWithSelector;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDelayWithSelector;
import rx.internal.operators.OperatorDematerialize;
import rx.internal.operators.OperatorDistinct;
import rx.internal.operators.OperatorDistinctUntilChanged;
import rx.internal.operators.OperatorDoOnEach;
import rx.internal.operators.OperatorDoOnRequest;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorElementAt;
import rx.internal.operators.OperatorFilter;
import rx.internal.operators.OperatorFinally;
import rx.internal.operators.OperatorGroupBy;
import rx.internal.operators.OperatorIgnoreElements;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorMapNotification;
import rx.internal.operators.OperatorMapPair;
import rx.internal.operators.OperatorMaterialize;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorMulticast;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnBackpressureBlock;
import rx.internal.operators.OperatorOnBackpressureBuffer;
import rx.internal.operators.OperatorOnBackpressureDrop;
import rx.internal.operators.OperatorOnBackpressureLatest;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorOnErrorResumeNextViaObservable;
import rx.internal.operators.OperatorOnErrorReturn;
import rx.internal.operators.OperatorOnExceptionResumeNextViaObservable;
import rx.internal.operators.OperatorPublish;
import rx.internal.operators.OperatorReplay;
import rx.internal.operators.OperatorRetryWithPredicate;
import rx.internal.operators.OperatorSampleWithObservable;
import rx.internal.operators.OperatorSampleWithTime;
import rx.internal.operators.OperatorScan;
import rx.internal.operators.OperatorSequenceEqual;
import rx.internal.operators.OperatorSerialize;
import rx.internal.operators.OperatorSingle;
import rx.internal.operators.OperatorSkip;
import rx.internal.operators.OperatorSkipLast;
import rx.internal.operators.OperatorSkipLastTimed;
import rx.internal.operators.OperatorSkipTimed;
import rx.internal.operators.OperatorSkipUntil;
import rx.internal.operators.OperatorSkipWhile;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.operators.OperatorSwitch;
import rx.internal.operators.OperatorSwitchIfEmpty;
import rx.internal.operators.OperatorTake;
import rx.internal.operators.OperatorTakeLast;
import rx.internal.operators.OperatorTakeLastOne;
import rx.internal.operators.OperatorTakeLastTimed;
import rx.internal.operators.OperatorTakeTimed;
import rx.internal.operators.OperatorTakeUntil;
import rx.internal.operators.OperatorTakeUntilPredicate;
import rx.internal.operators.OperatorTakeWhile;
import rx.internal.operators.OperatorThrottleFirst;
import rx.internal.operators.OperatorTimeInterval;
import rx.internal.operators.OperatorTimeout;
import rx.internal.operators.OperatorTimeoutWithSelector;
import rx.internal.operators.OperatorTimestamp;
import rx.internal.operators.OperatorToMap;
import rx.internal.operators.OperatorToMultimap;
import rx.internal.operators.OperatorToObservableList;
import rx.internal.operators.OperatorToObservableSortedList;
import rx.internal.operators.OperatorUnsubscribeOn;
import rx.internal.operators.OperatorWindowWithObservable;
import rx.internal.operators.OperatorWindowWithObservableFactory;
import rx.internal.operators.OperatorWindowWithSize;
import rx.internal.operators.OperatorWindowWithStartEndObservable;
import rx.internal.operators.OperatorWindowWithTime;
import rx.internal.operators.OperatorWithLatestFrom;
import rx.internal.operators.OperatorZip;
import rx.internal.operators.OperatorZipIterable;
import rx.internal.producers.SingleProducer;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.UtilityFunctions;
import rx.observables.BlockingObservable;
import rx.observables.ConnectableObservable;
import rx.observables.GroupedObservable;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;
import rx.schedulers.Timestamped;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public class Observable<T>
{
  private static final RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
  final OnSubscribe<T> onSubscribe;
  
  protected Observable(OnSubscribe<T> paramOnSubscribe)
  {
    this.onSubscribe = paramOnSubscribe;
  }
  
  public static final <T> Observable<T> amb(Iterable<? extends Observable<? extends T>> paramIterable)
  {
    return create(OnSubscribeAmb.amb(paramIterable));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8));
  }
  
  public static final <T> Observable<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8, Observable<? extends T> paramObservable9)
  {
    return create(OnSubscribeAmb.amb(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8, paramObservable9));
  }
  
  public static final <T, R> Observable<R> combineLatest(List<? extends Observable<? extends T>> paramList, FuncN<? extends R> paramFuncN)
  {
    return create(new OnSubscribeCombineLatest(paramList, paramFuncN));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Observable<? extends T8> paramObservable7, Observable<? extends T9> paramObservable8, Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> paramFunc9)
  {
    Observable[] arrayOfObservable = new Observable[9];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    arrayOfObservable[7] = paramObservable7;
    arrayOfObservable[8] = paramObservable8;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc9));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Observable<? extends T8> paramObservable7, Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> paramFunc8)
  {
    Observable[] arrayOfObservable = new Observable[8];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    arrayOfObservable[7] = paramObservable7;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc8));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> paramFunc7)
  {
    Observable[] arrayOfObservable = new Observable[7];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc7));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> paramFunc6)
  {
    Observable[] arrayOfObservable = new Observable[6];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc6));
  }
  
  public static final <T1, T2, T3, T4, T5, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> paramFunc5)
  {
    Observable[] arrayOfObservable = new Observable[5];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc5));
  }
  
  public static final <T1, T2, T3, T4, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> paramFunc4)
  {
    Observable[] arrayOfObservable = new Observable[4];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc4));
  }
  
  public static final <T1, T2, T3, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Func3<? super T1, ? super T2, ? super T3, ? extends R> paramFunc3)
  {
    Observable[] arrayOfObservable = new Observable[3];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc3));
  }
  
  public static final <T1, T2, R> Observable<R> combineLatest(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Func2<? super T1, ? super T2, ? extends R> paramFunc2)
  {
    Observable[] arrayOfObservable = new Observable[2];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    return combineLatest(Arrays.asList(arrayOfObservable), Functions.fromFunc(paramFunc2));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends Observable<? extends T>> paramObservable)
  {
    return paramObservable.lift(OperatorConcat.instance());
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    return concat(just(paramObservable1, paramObservable2));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8));
  }
  
  public static final <T> Observable<T> concat(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8, Observable<? extends T> paramObservable9)
  {
    return concat(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8, paramObservable9));
  }
  
  public static final <T> Observable<T> create(OnSubscribe<T> paramOnSubscribe)
  {
    return new Observable(hook.onCreate(paramOnSubscribe));
  }
  
  public static final <T> Observable<T> defer(Func0<Observable<T>> paramFunc0)
  {
    return create(new OnSubscribeDefer(paramFunc0));
  }
  
  public static final <T> Observable<T> empty()
  {
    return EmptyHolder.INSTANCE;
  }
  
  public static final <T> Observable<T> error(Throwable paramThrowable)
  {
    return new ThrowObservable(paramThrowable);
  }
  
  public static final <T> Observable<T> from(Iterable<? extends T> paramIterable)
  {
    return create(new OnSubscribeFromIterable(paramIterable));
  }
  
  public static final <T> Observable<T> from(Future<? extends T> paramFuture)
  {
    return create(OnSubscribeToObservableFuture.toObservableFuture(paramFuture));
  }
  
  public static final <T> Observable<T> from(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
  {
    return create(OnSubscribeToObservableFuture.toObservableFuture(paramFuture, paramLong, paramTimeUnit));
  }
  
  public static final <T> Observable<T> from(Future<? extends T> paramFuture, Scheduler paramScheduler)
  {
    return create(OnSubscribeToObservableFuture.toObservableFuture(paramFuture)).subscribeOn(paramScheduler);
  }
  
  public static final <T> Observable<T> from(T[] paramArrayOfT)
  {
    return from(Arrays.asList(paramArrayOfT));
  }
  
  public static final Observable<Long> interval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    return interval(paramLong1, paramLong2, paramTimeUnit, Schedulers.computation());
  }
  
  public static final Observable<Long> interval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return create(new OnSubscribeTimerPeriodically(paramLong1, paramLong2, paramTimeUnit, paramScheduler));
  }
  
  public static final Observable<Long> interval(long paramLong, TimeUnit paramTimeUnit)
  {
    return interval(paramLong, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public static final Observable<Long> interval(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return interval(paramLong, paramLong, paramTimeUnit, paramScheduler);
  }
  
  public static final <T> Observable<T> just(T paramT)
  {
    return ScalarSynchronousObservable.create(paramT);
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6)
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    arrayOfObject[5] = paramT6;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7)
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    arrayOfObject[5] = paramT6;
    arrayOfObject[6] = paramT7;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7, T paramT8)
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    arrayOfObject[5] = paramT6;
    arrayOfObject[6] = paramT7;
    arrayOfObject[7] = paramT8;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7, T paramT8, T paramT9)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    arrayOfObject[5] = paramT6;
    arrayOfObject[6] = paramT7;
    arrayOfObject[7] = paramT8;
    arrayOfObject[8] = paramT9;
    return from(Arrays.asList(arrayOfObject));
  }
  
  public static final <T> Observable<T> just(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7, T paramT8, T paramT9, T paramT10)
  {
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = paramT1;
    arrayOfObject[1] = paramT2;
    arrayOfObject[2] = paramT3;
    arrayOfObject[3] = paramT4;
    arrayOfObject[4] = paramT5;
    arrayOfObject[5] = paramT6;
    arrayOfObject[6] = paramT7;
    arrayOfObject[7] = paramT8;
    arrayOfObject[8] = paramT9;
    arrayOfObject[9] = paramT10;
    return from(Arrays.asList(arrayOfObject));
  }
  
  private final <R> Observable<R> mapNotification(Func1<? super T, ? extends R> paramFunc1, Func1<? super Throwable, ? extends R> paramFunc11, Func0<? extends R> paramFunc0)
  {
    return lift(new OperatorMapNotification(paramFunc1, paramFunc11, paramFunc0));
  }
  
  public static final <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> paramIterable)
  {
    return merge(from(paramIterable));
  }
  
  public static final <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> paramIterable, int paramInt)
  {
    return merge(from(paramIterable), paramInt);
  }
  
  public static final <T> Observable<T> merge(Observable<? extends Observable<? extends T>> paramObservable)
  {
    if (paramObservable.getClass() == ScalarSynchronousObservable.class) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)paramObservable).scalarFlatMap(UtilityFunctions.identity());; localObservable = paramObservable.lift(OperatorMerge.instance(false))) {
      return localObservable;
    }
  }
  
  @Experimental
  public static final <T> Observable<T> merge(Observable<? extends Observable<? extends T>> paramObservable, int paramInt)
  {
    if (paramObservable.getClass() == ScalarSynchronousObservable.class) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)paramObservable).scalarFlatMap(UtilityFunctions.identity());; localObservable = paramObservable.lift(OperatorMerge.instance(false, paramInt))) {
      return localObservable;
    }
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    Observable[] arrayOfObservable = new Observable[2];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3)
  {
    Observable[] arrayOfObservable = new Observable[3];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4)
  {
    Observable[] arrayOfObservable = new Observable[4];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5)
  {
    Observable[] arrayOfObservable = new Observable[5];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    arrayOfObservable[4] = paramObservable5;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6)
  {
    Observable[] arrayOfObservable = new Observable[6];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    arrayOfObservable[4] = paramObservable5;
    arrayOfObservable[5] = paramObservable6;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7)
  {
    Observable[] arrayOfObservable = new Observable[7];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    arrayOfObservable[4] = paramObservable5;
    arrayOfObservable[5] = paramObservable6;
    arrayOfObservable[6] = paramObservable7;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8)
  {
    Observable[] arrayOfObservable = new Observable[8];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    arrayOfObservable[4] = paramObservable5;
    arrayOfObservable[5] = paramObservable6;
    arrayOfObservable[6] = paramObservable7;
    arrayOfObservable[7] = paramObservable8;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8, Observable<? extends T> paramObservable9)
  {
    Observable[] arrayOfObservable = new Observable[9];
    arrayOfObservable[0] = paramObservable1;
    arrayOfObservable[1] = paramObservable2;
    arrayOfObservable[2] = paramObservable3;
    arrayOfObservable[3] = paramObservable4;
    arrayOfObservable[4] = paramObservable5;
    arrayOfObservable[5] = paramObservable6;
    arrayOfObservable[6] = paramObservable7;
    arrayOfObservable[7] = paramObservable8;
    arrayOfObservable[8] = paramObservable9;
    return merge(from(Arrays.asList(arrayOfObservable)));
  }
  
  public static final <T> Observable<T> merge(Observable<? extends T>[] paramArrayOfObservable)
  {
    return merge(from(paramArrayOfObservable));
  }
  
  @Experimental
  public static final <T> Observable<T> merge(Observable<? extends T>[] paramArrayOfObservable, int paramInt)
  {
    return merge(from(paramArrayOfObservable), paramInt);
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends Observable<? extends T>> paramObservable)
  {
    return paramObservable.lift(OperatorMerge.instance(true));
  }
  
  @Experimental
  public static final <T> Observable<T> mergeDelayError(Observable<? extends Observable<? extends T>> paramObservable, int paramInt)
  {
    return paramObservable.lift(OperatorMerge.instance(true, paramInt));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8));
  }
  
  public static final <T> Observable<T> mergeDelayError(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8, Observable<? extends T> paramObservable9)
  {
    return mergeDelayError(just(paramObservable1, paramObservable2, paramObservable3, paramObservable4, paramObservable5, paramObservable6, paramObservable7, paramObservable8, paramObservable9));
  }
  
  public static final <T> Observable<T> never()
  {
    return NeverObservable.instance();
  }
  
  public static final Observable<Integer> range(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0) {
      throw new IllegalArgumentException("Count can not be negative");
    }
    Observable localObservable;
    if (paramInt2 == 0) {
      localObservable = empty();
    }
    for (;;)
    {
      return localObservable;
      if (paramInt1 > 1 + (Integer.MAX_VALUE - paramInt2)) {
        throw new IllegalArgumentException("start + count can not exceed Integer.MAX_VALUE");
      }
      if (paramInt2 == 1) {
        localObservable = just(Integer.valueOf(paramInt1));
      } else {
        localObservable = create(new OnSubscribeRange(paramInt1, paramInt1 + (paramInt2 - 1)));
      }
    }
  }
  
  public static final Observable<Integer> range(int paramInt1, int paramInt2, Scheduler paramScheduler)
  {
    return range(paramInt1, paramInt2).subscribeOn(paramScheduler);
  }
  
  public static final <T> Observable<Boolean> sequenceEqual(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    sequenceEqual(paramObservable1, paramObservable2, new Func2()
    {
      public final Boolean call(T paramAnonymousT1, T paramAnonymousT2)
      {
        boolean bool;
        if (paramAnonymousT1 == null) {
          if (paramAnonymousT2 == null) {
            bool = true;
          }
        }
        for (Boolean localBoolean = Boolean.valueOf(bool);; localBoolean = Boolean.valueOf(paramAnonymousT1.equals(paramAnonymousT2)))
        {
          return localBoolean;
          bool = false;
          break;
        }
      }
    });
  }
  
  public static final <T> Observable<Boolean> sequenceEqual(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Func2<? super T, ? super T, Boolean> paramFunc2)
  {
    return OperatorSequenceEqual.sequenceEqual(paramObservable1, paramObservable2, paramFunc2);
  }
  
  public static final <T> Observable<T> switchOnNext(Observable<? extends Observable<? extends T>> paramObservable)
  {
    return paramObservable.lift(OperatorSwitch.instance());
  }
  
  @Deprecated
  public static final Observable<Long> timer(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    return interval(paramLong1, paramLong2, paramTimeUnit, Schedulers.computation());
  }
  
  @Deprecated
  public static final Observable<Long> timer(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return interval(paramLong1, paramLong2, paramTimeUnit, paramScheduler);
  }
  
  public static final Observable<Long> timer(long paramLong, TimeUnit paramTimeUnit)
  {
    return timer(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public static final Observable<Long> timer(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return create(new OnSubscribeTimerOnce(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public static final <T, Resource> Observable<T> using(Func0<Resource> paramFunc0, Func1<? super Resource, ? extends Observable<? extends T>> paramFunc1, Action1<? super Resource> paramAction1)
  {
    return using(paramFunc0, paramFunc1, paramAction1, false);
  }
  
  @Experimental
  public static final <T, Resource> Observable<T> using(Func0<Resource> paramFunc0, Func1<? super Resource, ? extends Observable<? extends T>> paramFunc1, Action1<? super Resource> paramAction1, boolean paramBoolean)
  {
    return create(new OnSubscribeUsing(paramFunc0, paramFunc1, paramAction1, paramBoolean));
  }
  
  public static final <R> Observable<R> zip(Iterable<? extends Observable<?>> paramIterable, FuncN<? extends R> paramFuncN)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((Observable)localIterator.next());
    }
    return just(localArrayList.toArray(new Observable[localArrayList.size()])).lift(new OperatorZip(paramFuncN));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Observable<? extends T8> paramObservable7, Observable<? extends T9> paramObservable8, Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> paramFunc9)
  {
    Observable[] arrayOfObservable = new Observable[9];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    arrayOfObservable[7] = paramObservable7;
    arrayOfObservable[8] = paramObservable8;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc9));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Observable<? extends T8> paramObservable7, Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> paramFunc8)
  {
    Observable[] arrayOfObservable = new Observable[8];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    arrayOfObservable[7] = paramObservable7;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc8));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Observable<? extends T7> paramObservable6, Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> paramFunc7)
  {
    Observable[] arrayOfObservable = new Observable[7];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    arrayOfObservable[6] = paramObservable6;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc7));
  }
  
  public static final <T1, T2, T3, T4, T5, T6, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Observable<? extends T6> paramObservable5, Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> paramFunc6)
  {
    Observable[] arrayOfObservable = new Observable[6];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    arrayOfObservable[5] = paramObservable5;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc6));
  }
  
  public static final <T1, T2, T3, T4, T5, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Observable<? extends T5> paramObservable4, Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> paramFunc5)
  {
    Observable[] arrayOfObservable = new Observable[5];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    arrayOfObservable[4] = paramObservable4;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc5));
  }
  
  public static final <T1, T2, T3, T4, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Observable<? extends T4> paramObservable3, Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> paramFunc4)
  {
    Observable[] arrayOfObservable = new Observable[4];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    arrayOfObservable[3] = paramObservable3;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc4));
  }
  
  public static final <T1, T2, T3, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Observable<? extends T3> paramObservable2, Func3<? super T1, ? super T2, ? super T3, ? extends R> paramFunc3)
  {
    Observable[] arrayOfObservable = new Observable[3];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    arrayOfObservable[2] = paramObservable2;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc3));
  }
  
  public static final <T1, T2, R> Observable<R> zip(Observable<? extends T1> paramObservable, Observable<? extends T2> paramObservable1, Func2<? super T1, ? super T2, ? extends R> paramFunc2)
  {
    Observable[] arrayOfObservable = new Observable[2];
    arrayOfObservable[0] = paramObservable;
    arrayOfObservable[1] = paramObservable1;
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc2));
  }
  
  public static final <R> Observable<R> zip(Observable<? extends Observable<?>> paramObservable, FuncN<? extends R> paramFuncN)
  {
    paramObservable.toList().map(new Func1()
    {
      public Observable<?>[] call(List<? extends Observable<?>> paramAnonymousList)
      {
        return (Observable[])paramAnonymousList.toArray(new Observable[paramAnonymousList.size()]);
      }
    }).lift(new OperatorZip(paramFuncN));
  }
  
  public final Observable<Boolean> all(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorAll(paramFunc1));
  }
  
  public final Observable<T> ambWith(Observable<? extends T> paramObservable)
  {
    return amb(this, paramObservable);
  }
  
  public final Observable<T> asObservable()
  {
    return lift(OperatorAsObservable.instance());
  }
  
  public final Observable<List<T>> buffer(int paramInt)
  {
    return buffer(paramInt, paramInt);
  }
  
  public final Observable<List<T>> buffer(int paramInt1, int paramInt2)
  {
    return lift(new OperatorBufferWithSize(paramInt1, paramInt2));
  }
  
  public final Observable<List<T>> buffer(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    return buffer(paramLong1, paramLong2, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<List<T>> buffer(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorBufferWithTime(paramLong1, paramLong2, paramTimeUnit, Integer.MAX_VALUE, paramScheduler));
  }
  
  public final Observable<List<T>> buffer(long paramLong, TimeUnit paramTimeUnit)
  {
    return buffer(paramLong, paramTimeUnit, Integer.MAX_VALUE, Schedulers.computation());
  }
  
  public final Observable<List<T>> buffer(long paramLong, TimeUnit paramTimeUnit, int paramInt)
  {
    return lift(new OperatorBufferWithTime(paramLong, paramLong, paramTimeUnit, paramInt, Schedulers.computation()));
  }
  
  public final Observable<List<T>> buffer(long paramLong, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    return lift(new OperatorBufferWithTime(paramLong, paramLong, paramTimeUnit, paramInt, paramScheduler));
  }
  
  public final Observable<List<T>> buffer(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return buffer(paramLong, paramLong, paramTimeUnit, paramScheduler);
  }
  
  public final <B> Observable<List<T>> buffer(Observable<B> paramObservable)
  {
    return buffer(paramObservable, 16);
  }
  
  public final <B> Observable<List<T>> buffer(Observable<B> paramObservable, int paramInt)
  {
    return lift(new OperatorBufferWithSingleObservable(paramObservable, paramInt));
  }
  
  public final <TOpening, TClosing> Observable<List<T>> buffer(Observable<? extends TOpening> paramObservable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> paramFunc1)
  {
    return lift(new OperatorBufferWithStartEndObservable(paramObservable, paramFunc1));
  }
  
  public final <TClosing> Observable<List<T>> buffer(Func0<? extends Observable<? extends TClosing>> paramFunc0)
  {
    return lift(new OperatorBufferWithSingleObservable(paramFunc0, 16));
  }
  
  public final Observable<T> cache()
  {
    return create(new OnSubscribeCache(this));
  }
  
  public final Observable<T> cache(int paramInt)
  {
    return create(new OnSubscribeCache(this, paramInt));
  }
  
  public final <R> Observable<R> cast(Class<R> paramClass)
  {
    return lift(new OperatorCast(paramClass));
  }
  
  public final <R> Observable<R> collect(Func0<R> paramFunc0, final Action2<R, ? super T> paramAction2)
  {
    lift(new OperatorScan(paramFunc0, new Func2()
    {
      public final R call(R paramAnonymousR, T paramAnonymousT)
      {
        paramAction2.call(paramAnonymousR, paramAnonymousT);
        return paramAnonymousR;
      }
    })).last();
  }
  
  public <R> Observable<R> compose(Transformer<? super T, ? extends R> paramTransformer)
  {
    return (Observable)paramTransformer.call(this);
  }
  
  public final <R> Observable<R> concatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    return concat(map(paramFunc1));
  }
  
  public final Observable<T> concatWith(Observable<? extends T> paramObservable)
  {
    return concat(this, paramObservable);
  }
  
  public final Observable<Boolean> contains(final Object paramObject)
  {
    exists(new Func1()
    {
      public final Boolean call(T paramAnonymousT)
      {
        boolean bool;
        if (paramObject == null) {
          if (paramAnonymousT == null) {
            bool = true;
          }
        }
        for (;;)
        {
          return Boolean.valueOf(bool);
          bool = false;
          continue;
          bool = paramObject.equals(paramAnonymousT);
        }
      }
    });
  }
  
  public final Observable<Integer> count()
  {
    return reduce(Integer.valueOf(0), CountHolder.INSTANCE);
  }
  
  public final Observable<Long> countLong()
  {
    return reduce(Long.valueOf(0L), CountLongHolder.INSTANCE);
  }
  
  public final Observable<T> debounce(long paramLong, TimeUnit paramTimeUnit)
  {
    return debounce(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> debounce(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorDebounceWithTime(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final <U> Observable<T> debounce(Func1<? super T, ? extends Observable<U>> paramFunc1)
  {
    return lift(new OperatorDebounceWithSelector(paramFunc1));
  }
  
  public final Observable<T> defaultIfEmpty(final T paramT)
  {
    switchIfEmpty(create(new OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        paramAnonymousSubscriber.setProducer(new SingleProducer(paramAnonymousSubscriber, paramT));
      }
    }));
  }
  
  public final Observable<T> delay(long paramLong, TimeUnit paramTimeUnit)
  {
    return delay(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> delay(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorDelay(this, paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final <U, V> Observable<T> delay(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1)
  {
    return delaySubscription(paramFunc0).lift(new OperatorDelayWithSelector(this, paramFunc1));
  }
  
  public final <U> Observable<T> delay(Func1<? super T, ? extends Observable<U>> paramFunc1)
  {
    return lift(new OperatorDelayWithSelector(this, paramFunc1));
  }
  
  public final Observable<T> delaySubscription(long paramLong, TimeUnit paramTimeUnit)
  {
    return delaySubscription(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> delaySubscription(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return create(new OnSubscribeDelaySubscription(this, paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final <U> Observable<T> delaySubscription(Func0<? extends Observable<U>> paramFunc0)
  {
    return create(new OnSubscribeDelaySubscriptionWithSelector(this, paramFunc0));
  }
  
  public final <T2> Observable<T2> dematerialize()
  {
    return lift(OperatorDematerialize.instance());
  }
  
  public final Observable<T> distinct()
  {
    return lift(OperatorDistinct.instance());
  }
  
  public final <U> Observable<T> distinct(Func1<? super T, ? extends U> paramFunc1)
  {
    return lift(new OperatorDistinct(paramFunc1));
  }
  
  public final Observable<T> distinctUntilChanged()
  {
    return lift(OperatorDistinctUntilChanged.instance());
  }
  
  public final <U> Observable<T> distinctUntilChanged(Func1<? super T, ? extends U> paramFunc1)
  {
    return lift(new OperatorDistinctUntilChanged(paramFunc1));
  }
  
  public final Observable<T> doOnCompleted(final Action0 paramAction0)
  {
    lift(new OperatorDoOnEach(new Observer()
    {
      public final void onCompleted()
      {
        paramAction0.call();
      }
      
      public final void onError(Throwable paramAnonymousThrowable) {}
      
      public final void onNext(T paramAnonymousT) {}
    }));
  }
  
  public final Observable<T> doOnEach(Observer<? super T> paramObserver)
  {
    return lift(new OperatorDoOnEach(paramObserver));
  }
  
  public final Observable<T> doOnEach(final Action1<Notification<? super T>> paramAction1)
  {
    lift(new OperatorDoOnEach(new Observer()
    {
      public final void onCompleted()
      {
        paramAction1.call(Notification.createOnCompleted());
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction1.call(Notification.createOnError(paramAnonymousThrowable));
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(Notification.createOnNext(paramAnonymousT));
      }
    }));
  }
  
  public final Observable<T> doOnError(final Action1<Throwable> paramAction1)
  {
    lift(new OperatorDoOnEach(new Observer()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction1.call(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT) {}
    }));
  }
  
  public final Observable<T> doOnNext(final Action1<? super T> paramAction1)
  {
    lift(new OperatorDoOnEach(new Observer()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable) {}
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    }));
  }
  
  @Beta
  public final Observable<T> doOnRequest(Action1<Long> paramAction1)
  {
    return lift(new OperatorDoOnRequest(paramAction1));
  }
  
  public final Observable<T> doOnSubscribe(Action0 paramAction0)
  {
    return lift(new OperatorDoOnSubscribe(paramAction0));
  }
  
  public final Observable<T> doOnTerminate(final Action0 paramAction0)
  {
    lift(new OperatorDoOnEach(new Observer()
    {
      public final void onCompleted()
      {
        paramAction0.call();
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction0.call();
      }
      
      public final void onNext(T paramAnonymousT) {}
    }));
  }
  
  public final Observable<T> doOnUnsubscribe(Action0 paramAction0)
  {
    return lift(new OperatorDoOnUnsubscribe(paramAction0));
  }
  
  public final Observable<T> elementAt(int paramInt)
  {
    return lift(new OperatorElementAt(paramInt));
  }
  
  public final Observable<T> elementAtOrDefault(int paramInt, T paramT)
  {
    return lift(new OperatorElementAt(paramInt, paramT));
  }
  
  public final Observable<Boolean> exists(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorAny(paramFunc1, false));
  }
  
  public final Observable<T> filter(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorFilter(paramFunc1));
  }
  
  public final Observable<T> finallyDo(Action0 paramAction0)
  {
    return lift(new OperatorFinally(paramAction0));
  }
  
  public final Observable<T> first()
  {
    return take(1).single();
  }
  
  public final Observable<T> first(Func1<? super T, Boolean> paramFunc1)
  {
    return takeFirst(paramFunc1).single();
  }
  
  public final Observable<T> firstOrDefault(T paramT)
  {
    return take(1).singleOrDefault(paramT);
  }
  
  public final Observable<T> firstOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return takeFirst(paramFunc1).singleOrDefault(paramT);
  }
  
  public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    if (getClass() == ScalarSynchronousObservable.class) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)this).scalarFlatMap(paramFunc1);; localObservable = merge(map(paramFunc1))) {
      return localObservable;
    }
  }
  
  @Beta
  public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1, int paramInt)
  {
    if (getClass() == ScalarSynchronousObservable.class) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)this).scalarFlatMap(paramFunc1);; localObservable = merge(map(paramFunc1), paramInt)) {
      return localObservable;
    }
  }
  
  public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1, Func1<? super Throwable, ? extends Observable<? extends R>> paramFunc11, Func0<? extends Observable<? extends R>> paramFunc0)
  {
    return merge(mapNotification(paramFunc1, paramFunc11, paramFunc0));
  }
  
  @Beta
  public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1, Func1<? super Throwable, ? extends Observable<? extends R>> paramFunc11, Func0<? extends Observable<? extends R>> paramFunc0, int paramInt)
  {
    return merge(mapNotification(paramFunc1, paramFunc11, paramFunc0), paramInt);
  }
  
  public final <U, R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends U>> paramFunc1, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    return merge(lift(new OperatorMapPair(paramFunc1, paramFunc2)));
  }
  
  @Beta
  public final <U, R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends U>> paramFunc1, Func2<? super T, ? super U, ? extends R> paramFunc2, int paramInt)
  {
    return merge(lift(new OperatorMapPair(paramFunc1, paramFunc2)), paramInt);
  }
  
  public final <R> Observable<R> flatMapIterable(Func1<? super T, ? extends Iterable<? extends R>> paramFunc1)
  {
    return merge(map(OperatorMapPair.convertSelector(paramFunc1)));
  }
  
  public final <U, R> Observable<R> flatMapIterable(Func1<? super T, ? extends Iterable<? extends U>> paramFunc1, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    return flatMap(OperatorMapPair.convertSelector(paramFunc1), paramFunc2);
  }
  
  public final void forEach(Action1<? super T> paramAction1)
  {
    subscribe(paramAction1);
  }
  
  public final void forEach(Action1<? super T> paramAction1, Action1<Throwable> paramAction11)
  {
    subscribe(paramAction1, paramAction11);
  }
  
  public final void forEach(Action1<? super T> paramAction1, Action1<Throwable> paramAction11, Action0 paramAction0)
  {
    subscribe(paramAction1, paramAction11, paramAction0);
  }
  
  public final <K> Observable<GroupedObservable<K, T>> groupBy(Func1<? super T, ? extends K> paramFunc1)
  {
    return lift(new OperatorGroupBy(paramFunc1));
  }
  
  public final <K, R> Observable<GroupedObservable<K, R>> groupBy(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends R> paramFunc11)
  {
    return lift(new OperatorGroupBy(paramFunc1, paramFunc11));
  }
  
  public final <T2, D1, D2, R> Observable<R> groupJoin(Observable<T2> paramObservable, Func1<? super T, ? extends Observable<D1>> paramFunc1, Func1<? super T2, ? extends Observable<D2>> paramFunc11, Func2<? super T, ? super Observable<T2>, ? extends R> paramFunc2)
  {
    return create(new OnSubscribeGroupJoin(this, paramObservable, paramFunc1, paramFunc11, paramFunc2));
  }
  
  public final Observable<T> ignoreElements()
  {
    return lift(OperatorIgnoreElements.instance());
  }
  
  public final Observable<Boolean> isEmpty()
  {
    return lift(HolderAnyForEmpty.INSTANCE);
  }
  
  public final <TRight, TLeftDuration, TRightDuration, R> Observable<R> join(Observable<TRight> paramObservable, Func1<T, Observable<TLeftDuration>> paramFunc1, Func1<TRight, Observable<TRightDuration>> paramFunc11, Func2<T, TRight, R> paramFunc2)
  {
    return create(new OnSubscribeJoin(this, paramObservable, paramFunc1, paramFunc11, paramFunc2));
  }
  
  public final Observable<T> last()
  {
    return takeLast(1).single();
  }
  
  public final Observable<T> last(Func1<? super T, Boolean> paramFunc1)
  {
    return filter(paramFunc1).takeLast(1).single();
  }
  
  public final Observable<T> lastOrDefault(T paramT)
  {
    return takeLast(1).singleOrDefault(paramT);
  }
  
  public final Observable<T> lastOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return filter(paramFunc1).takeLast(1).singleOrDefault(paramT);
  }
  
  public final <R> Observable<R> lift(final Operator<? extends R, ? super T> paramOperator)
  {
    new Observable(new OnSubscribe()
    {
      public void call(Subscriber<? super R> paramAnonymousSubscriber)
      {
        for (;;)
        {
          try
          {
            localSubscriber = (Subscriber)Observable.hook.onLift(paramOperator).call(paramAnonymousSubscriber);
          }
          catch (Throwable localThrowable1)
          {
            Subscriber localSubscriber;
            if (!(localThrowable1 instanceof OnErrorNotImplementedException)) {
              continue;
            }
            throw ((OnErrorNotImplementedException)localThrowable1);
            localSubscriber.onError(localThrowable2);
            continue;
            paramAnonymousSubscriber.onError(localThrowable1);
            continue;
          }
          try
          {
            localSubscriber.onStart();
            Observable.this.onSubscribe.call(localSubscriber);
            return;
          }
          catch (Throwable localThrowable2)
          {
            if (!(localThrowable2 instanceof OnErrorNotImplementedException)) {
              continue;
            }
          }
        }
        throw ((OnErrorNotImplementedException)localThrowable2);
      }
    });
  }
  
  public final Observable<T> limit(int paramInt)
  {
    return take(paramInt);
  }
  
  public final <R> Observable<R> map(Func1<? super T, ? extends R> paramFunc1)
  {
    return lift(new OperatorMap(paramFunc1));
  }
  
  public final Observable<Notification<T>> materialize()
  {
    return lift(OperatorMaterialize.instance());
  }
  
  public final Observable<T> mergeWith(Observable<? extends T> paramObservable)
  {
    return merge(this, paramObservable);
  }
  
  public final Observable<Observable<T>> nest()
  {
    return just(this);
  }
  
  public final Observable<T> observeOn(Scheduler paramScheduler)
  {
    if ((this instanceof ScalarSynchronousObservable)) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)this).scalarScheduleOn(paramScheduler);; localObservable = lift(new OperatorObserveOn(paramScheduler))) {
      return localObservable;
    }
  }
  
  public final <R> Observable<R> ofType(final Class<R> paramClass)
  {
    filter(new Func1()
    {
      public final Boolean call(T paramAnonymousT)
      {
        return Boolean.valueOf(paramClass.isInstance(paramAnonymousT));
      }
    }).cast(paramClass);
  }
  
  @Deprecated
  @Experimental
  public final Observable<T> onBackpressureBlock()
  {
    return onBackpressureBlock(RxRingBuffer.SIZE);
  }
  
  @Deprecated
  @Experimental
  public final Observable<T> onBackpressureBlock(int paramInt)
  {
    return lift(new OperatorOnBackpressureBlock(paramInt));
  }
  
  public final Observable<T> onBackpressureBuffer()
  {
    return lift(OperatorOnBackpressureBuffer.instance());
  }
  
  @Beta
  public final Observable<T> onBackpressureBuffer(long paramLong)
  {
    return lift(new OperatorOnBackpressureBuffer(paramLong));
  }
  
  @Beta
  public final Observable<T> onBackpressureBuffer(long paramLong, Action0 paramAction0)
  {
    return lift(new OperatorOnBackpressureBuffer(paramLong, paramAction0));
  }
  
  public final Observable<T> onBackpressureDrop()
  {
    return lift(OperatorOnBackpressureDrop.instance());
  }
  
  @Experimental
  public final Observable<T> onBackpressureDrop(Action1<? super T> paramAction1)
  {
    return lift(new OperatorOnBackpressureDrop(paramAction1));
  }
  
  @Experimental
  public final Observable<T> onBackpressureLatest()
  {
    return lift(OperatorOnBackpressureLatest.instance());
  }
  
  public final Observable<T> onErrorResumeNext(Observable<? extends T> paramObservable)
  {
    return lift(new OperatorOnErrorResumeNextViaObservable(paramObservable));
  }
  
  public final Observable<T> onErrorResumeNext(Func1<Throwable, ? extends Observable<? extends T>> paramFunc1)
  {
    return lift(new OperatorOnErrorResumeNextViaFunction(paramFunc1));
  }
  
  public final Observable<T> onErrorReturn(Func1<Throwable, ? extends T> paramFunc1)
  {
    return lift(new OperatorOnErrorReturn(paramFunc1));
  }
  
  public final Observable<T> onExceptionResumeNext(Observable<? extends T> paramObservable)
  {
    return lift(new OperatorOnExceptionResumeNextViaObservable(paramObservable));
  }
  
  public final <R> Observable<R> publish(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1)
  {
    return OperatorPublish.create(this, paramFunc1);
  }
  
  public final ConnectableObservable<T> publish()
  {
    return OperatorPublish.create(this);
  }
  
  public final <R> Observable<R> reduce(R paramR, Func2<R, ? super T, R> paramFunc2)
  {
    return scan(paramR, paramFunc2).takeLast(1);
  }
  
  public final Observable<T> reduce(Func2<T, T, T> paramFunc2)
  {
    return scan(paramFunc2).last();
  }
  
  public final Observable<T> repeat()
  {
    return OnSubscribeRedo.repeat(this);
  }
  
  public final Observable<T> repeat(long paramLong)
  {
    return OnSubscribeRedo.repeat(this, paramLong);
  }
  
  public final Observable<T> repeat(long paramLong, Scheduler paramScheduler)
  {
    return OnSubscribeRedo.repeat(this, paramLong, paramScheduler);
  }
  
  public final Observable<T> repeat(Scheduler paramScheduler)
  {
    return OnSubscribeRedo.repeat(this, paramScheduler);
  }
  
  public final Observable<T> repeatWhen(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> paramFunc1)
  {
    OnSubscribeRedo.repeat(this, new Func1()
    {
      public Observable<?> call(Observable<? extends Notification<?>> paramAnonymousObservable)
      {
        (Observable)paramFunc1.call(paramAnonymousObservable.map(new Func1()
        {
          public Void call(Notification<?> paramAnonymous2Notification)
          {
            return null;
          }
        }));
      }
    });
  }
  
  public final Observable<T> repeatWhen(final Func1<? super Observable<? extends Void>, ? extends Observable<?>> paramFunc1, Scheduler paramScheduler)
  {
    OnSubscribeRedo.repeat(this, new Func1()
    {
      public Observable<?> call(Observable<? extends Notification<?>> paramAnonymousObservable)
      {
        (Observable)paramFunc1.call(paramAnonymousObservable.map(new Func1()
        {
          public Void call(Notification<?> paramAnonymous2Notification)
          {
            return null;
          }
        }));
      }
    }, paramScheduler);
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1)
  {
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return ReplaySubject.create();
      }
    }, paramFunc1));
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, final int paramInt)
  {
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return ReplaySubject.createWithSize(paramInt);
      }
    }, paramFunc1));
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    return replay(paramFunc1, paramInt, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, final int paramInt, final long paramLong, TimeUnit paramTimeUnit, final Scheduler paramScheduler)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("bufferSize < 0");
    }
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return ReplaySubject.createWithTimeAndSize(paramLong, paramInt, paramScheduler, this.val$scheduler);
      }
    }, paramFunc1));
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, final int paramInt, final Scheduler paramScheduler)
  {
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return OperatorReplay.createScheduledSubject(ReplaySubject.createWithSize(paramInt), paramScheduler);
      }
    }, paramFunc1));
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, long paramLong, TimeUnit paramTimeUnit)
  {
    return replay(paramFunc1, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, final long paramLong, TimeUnit paramTimeUnit, final Scheduler paramScheduler)
  {
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return ReplaySubject.createWithTime(paramLong, paramScheduler, this.val$scheduler);
      }
    }, paramFunc1));
  }
  
  public final <R> Observable<R> replay(Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, final Scheduler paramScheduler)
  {
    create(new OnSubscribeMulticastSelector(this, new Func0()
    {
      public final Subject<T, T> call()
      {
        return OperatorReplay.createScheduledSubject(ReplaySubject.create(), paramScheduler);
      }
    }, paramFunc1));
  }
  
  public final ConnectableObservable<T> replay()
  {
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return ReplaySubject.create();
      }
    });
  }
  
  public final ConnectableObservable<T> replay(final int paramInt)
  {
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return ReplaySubject.createWithSize(paramInt);
      }
    });
  }
  
  public final ConnectableObservable<T> replay(int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    return replay(paramInt, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final ConnectableObservable<T> replay(final int paramInt, final long paramLong, TimeUnit paramTimeUnit, final Scheduler paramScheduler)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("bufferSize < 0");
    }
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return ReplaySubject.createWithTimeAndSize(paramLong, paramInt, paramScheduler, this.val$scheduler);
      }
    });
  }
  
  public final ConnectableObservable<T> replay(final int paramInt, final Scheduler paramScheduler)
  {
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return OperatorReplay.createScheduledSubject(ReplaySubject.createWithSize(paramInt), paramScheduler);
      }
    });
  }
  
  public final ConnectableObservable<T> replay(long paramLong, TimeUnit paramTimeUnit)
  {
    return replay(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final ConnectableObservable<T> replay(final long paramLong, TimeUnit paramTimeUnit, final Scheduler paramScheduler)
  {
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return ReplaySubject.createWithTime(paramLong, paramScheduler, this.val$scheduler);
      }
    });
  }
  
  public final ConnectableObservable<T> replay(final Scheduler paramScheduler)
  {
    new OperatorMulticast(this, new Func0()
    {
      public Subject<? super T, ? extends T> call()
      {
        return OperatorReplay.createScheduledSubject(ReplaySubject.create(), paramScheduler);
      }
    });
  }
  
  public final Observable<T> retry()
  {
    return OnSubscribeRedo.retry(this);
  }
  
  public final Observable<T> retry(long paramLong)
  {
    return OnSubscribeRedo.retry(this, paramLong);
  }
  
  public final Observable<T> retry(Func2<Integer, Throwable, Boolean> paramFunc2)
  {
    return nest().lift(new OperatorRetryWithPredicate(paramFunc2));
  }
  
  public final Observable<T> retryWhen(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> paramFunc1)
  {
    OnSubscribeRedo.retry(this, new Func1()
    {
      public Observable<?> call(Observable<? extends Notification<?>> paramAnonymousObservable)
      {
        (Observable)paramFunc1.call(paramAnonymousObservable.map(new Func1()
        {
          public Throwable call(Notification<?> paramAnonymous2Notification)
          {
            return paramAnonymous2Notification.getThrowable();
          }
        }));
      }
    });
  }
  
  public final Observable<T> retryWhen(final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> paramFunc1, Scheduler paramScheduler)
  {
    OnSubscribeRedo.retry(this, new Func1()
    {
      public Observable<?> call(Observable<? extends Notification<?>> paramAnonymousObservable)
      {
        (Observable)paramFunc1.call(paramAnonymousObservable.map(new Func1()
        {
          public Throwable call(Notification<?> paramAnonymous2Notification)
          {
            return paramAnonymous2Notification.getThrowable();
          }
        }));
      }
    }, paramScheduler);
  }
  
  public final Observable<T> sample(long paramLong, TimeUnit paramTimeUnit)
  {
    return sample(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> sample(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorSampleWithTime(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final <U> Observable<T> sample(Observable<U> paramObservable)
  {
    return lift(new OperatorSampleWithObservable(paramObservable));
  }
  
  public final <R> Observable<R> scan(R paramR, Func2<R, ? super T, R> paramFunc2)
  {
    return lift(new OperatorScan(paramR, paramFunc2));
  }
  
  public final Observable<T> scan(Func2<T, T, T> paramFunc2)
  {
    return lift(new OperatorScan(paramFunc2));
  }
  
  public final Observable<T> serialize()
  {
    return lift(OperatorSerialize.instance());
  }
  
  public final Observable<T> share()
  {
    return publish().refCount();
  }
  
  public final Observable<T> single()
  {
    return lift(OperatorSingle.instance());
  }
  
  public final Observable<T> single(Func1<? super T, Boolean> paramFunc1)
  {
    return filter(paramFunc1).single();
  }
  
  public final Observable<T> singleOrDefault(T paramT)
  {
    return lift(new OperatorSingle(paramT));
  }
  
  public final Observable<T> singleOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return filter(paramFunc1).singleOrDefault(paramT);
  }
  
  public final Observable<T> skip(int paramInt)
  {
    return lift(new OperatorSkip(paramInt));
  }
  
  public final Observable<T> skip(long paramLong, TimeUnit paramTimeUnit)
  {
    return skip(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> skip(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorSkipTimed(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final Observable<T> skipLast(int paramInt)
  {
    return lift(new OperatorSkipLast(paramInt));
  }
  
  public final Observable<T> skipLast(long paramLong, TimeUnit paramTimeUnit)
  {
    return skipLast(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> skipLast(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorSkipLastTimed(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final <U> Observable<T> skipUntil(Observable<U> paramObservable)
  {
    return lift(new OperatorSkipUntil(paramObservable));
  }
  
  public final Observable<T> skipWhile(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorSkipWhile(OperatorSkipWhile.toPredicate2(paramFunc1)));
  }
  
  public final Observable<T> startWith(Iterable<T> paramIterable)
  {
    return concat(from(paramIterable), this);
  }
  
  public final Observable<T> startWith(T paramT)
  {
    return concat(just(paramT), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2)
  {
    return concat(just(paramT1, paramT2), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3)
  {
    return concat(just(paramT1, paramT2, paramT3), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4, paramT5), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7, T paramT8)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7, paramT8), this);
  }
  
  public final Observable<T> startWith(T paramT1, T paramT2, T paramT3, T paramT4, T paramT5, T paramT6, T paramT7, T paramT8, T paramT9)
  {
    return concat(just(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7, paramT8, paramT9), this);
  }
  
  public final Observable<T> startWith(Observable<T> paramObservable)
  {
    return concat(paramObservable, this);
  }
  
  public final Subscription subscribe()
  {
    subscribe(new Subscriber()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        throw new OnErrorNotImplementedException(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT) {}
    });
  }
  
  public final Subscription subscribe(final Observer<? super T> paramObserver)
  {
    if ((paramObserver instanceof Subscriber)) {}
    for (Subscription localSubscription = subscribe((Subscriber)paramObserver);; localSubscription = subscribe(new Subscriber()
        {
          public void onCompleted()
          {
            paramObserver.onCompleted();
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            paramObserver.onError(paramAnonymousThrowable);
          }
          
          public void onNext(T paramAnonymousT)
          {
            paramObserver.onNext(paramAnonymousT);
          }
        })) {
      return localSubscription;
    }
  }
  
  public final Subscription subscribe(Subscriber<? super T> paramSubscriber)
  {
    if (paramSubscriber == null) {
      throw new IllegalArgumentException("observer can not be null");
    }
    if (this.onSubscribe == null) {
      throw new IllegalStateException("onSubscribe function can not be null.");
    }
    paramSubscriber.onStart();
    if (!(paramSubscriber instanceof SafeSubscriber)) {
      paramSubscriber = new SafeSubscriber(paramSubscriber);
    }
    try
    {
      hook.onSubscribeStart(this, this.onSubscribe).call(paramSubscriber);
      Subscription localSubscription2 = hook.onSubscribeReturn(paramSubscriber);
      localSubscription1 = localSubscription2;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        Subscription localSubscription1;
        Exceptions.throwIfFatal(localThrowable1);
        try
        {
          paramSubscriber.onError(hook.onSubscribeError(localThrowable1));
          localSubscription1 = Subscriptions.unsubscribed();
        }
        catch (OnErrorNotImplementedException localOnErrorNotImplementedException)
        {
          throw localOnErrorNotImplementedException;
        }
        catch (Throwable localThrowable2)
        {
          RuntimeException localRuntimeException = new RuntimeException("Error occurred attempting to subscribe [" + localThrowable1.getMessage() + "] and then again while trying to pass to onError.", localThrowable2);
          hook.onSubscribeError(localRuntimeException);
          throw localRuntimeException;
        }
      }
    }
    return localSubscription1;
  }
  
  public final Subscription subscribe(final Action1<? super T> paramAction1)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    subscribe(new Subscriber()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        throw new OnErrorNotImplementedException(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    });
  }
  
  public final Subscription subscribe(final Action1<? super T> paramAction1, final Action1<Throwable> paramAction11)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    if (paramAction11 == null) {
      throw new IllegalArgumentException("onError can not be null");
    }
    subscribe(new Subscriber()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction11.call(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    });
  }
  
  public final Subscription subscribe(final Action1<? super T> paramAction1, final Action1<Throwable> paramAction11, final Action0 paramAction0)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    if (paramAction11 == null) {
      throw new IllegalArgumentException("onError can not be null");
    }
    if (paramAction0 == null) {
      throw new IllegalArgumentException("onComplete can not be null");
    }
    subscribe(new Subscriber()
    {
      public final void onCompleted()
      {
        paramAction0.call();
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction11.call(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    });
  }
  
  public final Observable<T> subscribeOn(Scheduler paramScheduler)
  {
    if ((this instanceof ScalarSynchronousObservable)) {}
    for (Observable localObservable = ((ScalarSynchronousObservable)this).scalarScheduleOn(paramScheduler);; localObservable = nest().lift(new OperatorSubscribeOn(paramScheduler))) {
      return localObservable;
    }
  }
  
  @Experimental
  public final Observable<T> switchIfEmpty(Observable<? extends T> paramObservable)
  {
    return lift(new OperatorSwitchIfEmpty(paramObservable));
  }
  
  public final <R> Observable<R> switchMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    return switchOnNext(map(paramFunc1));
  }
  
  public final Observable<T> take(int paramInt)
  {
    return lift(new OperatorTake(paramInt));
  }
  
  public final Observable<T> take(long paramLong, TimeUnit paramTimeUnit)
  {
    return take(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> take(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorTakeTimed(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final Observable<T> takeFirst(Func1<? super T, Boolean> paramFunc1)
  {
    return filter(paramFunc1).take(1);
  }
  
  public final Observable<T> takeLast(int paramInt)
  {
    Observable localObservable;
    if (paramInt == 0) {
      localObservable = ignoreElements();
    }
    for (;;)
    {
      return localObservable;
      if (paramInt == 1) {
        localObservable = lift(OperatorTakeLastOne.instance());
      } else {
        localObservable = lift(new OperatorTakeLast(paramInt));
      }
    }
  }
  
  public final Observable<T> takeLast(int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    return takeLast(paramInt, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> takeLast(int paramInt, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorTakeLastTimed(paramInt, paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final Observable<T> takeLast(long paramLong, TimeUnit paramTimeUnit)
  {
    return takeLast(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> takeLast(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorTakeLastTimed(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final Observable<List<T>> takeLastBuffer(int paramInt)
  {
    return takeLast(paramInt).toList();
  }
  
  public final Observable<List<T>> takeLastBuffer(int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    return takeLast(paramInt, paramLong, paramTimeUnit).toList();
  }
  
  public final Observable<List<T>> takeLastBuffer(int paramInt, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return takeLast(paramInt, paramLong, paramTimeUnit, paramScheduler).toList();
  }
  
  public final Observable<List<T>> takeLastBuffer(long paramLong, TimeUnit paramTimeUnit)
  {
    return takeLast(paramLong, paramTimeUnit).toList();
  }
  
  public final Observable<List<T>> takeLastBuffer(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return takeLast(paramLong, paramTimeUnit, paramScheduler).toList();
  }
  
  public final <E> Observable<T> takeUntil(Observable<? extends E> paramObservable)
  {
    return lift(new OperatorTakeUntil(paramObservable));
  }
  
  @Experimental
  public final Observable<T> takeUntil(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorTakeUntilPredicate(paramFunc1));
  }
  
  public final Observable<T> takeWhile(Func1<? super T, Boolean> paramFunc1)
  {
    return lift(new OperatorTakeWhile(paramFunc1));
  }
  
  public final Observable<T> throttleFirst(long paramLong, TimeUnit paramTimeUnit)
  {
    return throttleFirst(paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<T> throttleFirst(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorThrottleFirst(paramLong, paramTimeUnit, paramScheduler));
  }
  
  public final Observable<T> throttleLast(long paramLong, TimeUnit paramTimeUnit)
  {
    return sample(paramLong, paramTimeUnit);
  }
  
  public final Observable<T> throttleLast(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return sample(paramLong, paramTimeUnit, paramScheduler);
  }
  
  public final Observable<T> throttleWithTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    return debounce(paramLong, paramTimeUnit);
  }
  
  public final Observable<T> throttleWithTimeout(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return debounce(paramLong, paramTimeUnit, paramScheduler);
  }
  
  public final Observable<TimeInterval<T>> timeInterval()
  {
    return timeInterval(Schedulers.immediate());
  }
  
  public final Observable<TimeInterval<T>> timeInterval(Scheduler paramScheduler)
  {
    return lift(new OperatorTimeInterval(paramScheduler));
  }
  
  public final Observable<T> timeout(long paramLong, TimeUnit paramTimeUnit)
  {
    return timeout(paramLong, paramTimeUnit, null, Schedulers.computation());
  }
  
  public final Observable<T> timeout(long paramLong, TimeUnit paramTimeUnit, Observable<? extends T> paramObservable)
  {
    return timeout(paramLong, paramTimeUnit, paramObservable, Schedulers.computation());
  }
  
  public final Observable<T> timeout(long paramLong, TimeUnit paramTimeUnit, Observable<? extends T> paramObservable, Scheduler paramScheduler)
  {
    return lift(new OperatorTimeout(paramLong, paramTimeUnit, paramObservable, paramScheduler));
  }
  
  public final Observable<T> timeout(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return timeout(paramLong, paramTimeUnit, null, paramScheduler);
  }
  
  public final <U, V> Observable<T> timeout(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1)
  {
    return timeout(paramFunc0, paramFunc1, null);
  }
  
  public final <U, V> Observable<T> timeout(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1, Observable<? extends T> paramObservable)
  {
    if (paramFunc1 == null) {
      throw new NullPointerException("timeoutSelector is null");
    }
    return lift(new OperatorTimeoutWithSelector(paramFunc0, paramFunc1, paramObservable));
  }
  
  public final <V> Observable<T> timeout(Func1<? super T, ? extends Observable<V>> paramFunc1)
  {
    return timeout(null, paramFunc1, null);
  }
  
  public final <V> Observable<T> timeout(Func1<? super T, ? extends Observable<V>> paramFunc1, Observable<? extends T> paramObservable)
  {
    return timeout(null, paramFunc1, paramObservable);
  }
  
  public final Observable<Timestamped<T>> timestamp()
  {
    return timestamp(Schedulers.immediate());
  }
  
  public final Observable<Timestamped<T>> timestamp(Scheduler paramScheduler)
  {
    return lift(new OperatorTimestamp(paramScheduler));
  }
  
  public final BlockingObservable<T> toBlocking()
  {
    return BlockingObservable.from(this);
  }
  
  public final Observable<List<T>> toList()
  {
    return lift(OperatorToObservableList.instance());
  }
  
  public final <K> Observable<Map<K, T>> toMap(Func1<? super T, ? extends K> paramFunc1)
  {
    return lift(new OperatorToMap(paramFunc1, UtilityFunctions.identity()));
  }
  
  public final <K, V> Observable<Map<K, V>> toMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11)
  {
    return lift(new OperatorToMap(paramFunc1, paramFunc11));
  }
  
  public final <K, V> Observable<Map<K, V>> toMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, V>> paramFunc0)
  {
    return lift(new OperatorToMap(paramFunc1, paramFunc11, paramFunc0));
  }
  
  public final <K> Observable<Map<K, Collection<T>>> toMultimap(Func1<? super T, ? extends K> paramFunc1)
  {
    return lift(new OperatorToMultimap(paramFunc1, UtilityFunctions.identity()));
  }
  
  public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11)
  {
    return lift(new OperatorToMultimap(paramFunc1, paramFunc11));
  }
  
  public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, Collection<V>>> paramFunc0)
  {
    return lift(new OperatorToMultimap(paramFunc1, paramFunc11, paramFunc0));
  }
  
  public final <K, V> Observable<Map<K, Collection<V>>> toMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, Collection<V>>> paramFunc0, Func1<? super K, ? extends Collection<V>> paramFunc12)
  {
    return lift(new OperatorToMultimap(paramFunc1, paramFunc11, paramFunc0, paramFunc12));
  }
  
  @Experimental
  public Single<T> toSingle()
  {
    return new Single(OnSubscribeSingle.create(this));
  }
  
  public final Observable<List<T>> toSortedList()
  {
    return lift(new OperatorToObservableSortedList(10));
  }
  
  @Experimental
  public final Observable<List<T>> toSortedList(int paramInt)
  {
    return lift(new OperatorToObservableSortedList(paramInt));
  }
  
  public final Observable<List<T>> toSortedList(Func2<? super T, ? super T, Integer> paramFunc2)
  {
    return lift(new OperatorToObservableSortedList(paramFunc2, 10));
  }
  
  @Experimental
  public final Observable<List<T>> toSortedList(Func2<? super T, ? super T, Integer> paramFunc2, int paramInt)
  {
    return lift(new OperatorToObservableSortedList(paramFunc2, paramInt));
  }
  
  public final Subscription unsafeSubscribe(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      paramSubscriber.onStart();
      hook.onSubscribeStart(this, this.onSubscribe).call(paramSubscriber);
      Subscription localSubscription2 = hook.onSubscribeReturn(paramSubscriber);
      localSubscription1 = localSubscription2;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        Subscription localSubscription1;
        Exceptions.throwIfFatal(localThrowable1);
        try
        {
          paramSubscriber.onError(hook.onSubscribeError(localThrowable1));
          localSubscription1 = Subscriptions.unsubscribed();
        }
        catch (OnErrorNotImplementedException localOnErrorNotImplementedException)
        {
          throw localOnErrorNotImplementedException;
        }
        catch (Throwable localThrowable2)
        {
          RuntimeException localRuntimeException = new RuntimeException("Error occurred attempting to subscribe [" + localThrowable1.getMessage() + "] and then again while trying to pass to onError.", localThrowable2);
          hook.onSubscribeError(localRuntimeException);
          throw localRuntimeException;
        }
      }
    }
    return localSubscription1;
  }
  
  public final Observable<T> unsubscribeOn(Scheduler paramScheduler)
  {
    return lift(new OperatorUnsubscribeOn(paramScheduler));
  }
  
  public final Observable<Observable<T>> window(int paramInt)
  {
    return window(paramInt, paramInt);
  }
  
  public final Observable<Observable<T>> window(int paramInt1, int paramInt2)
  {
    return lift(new OperatorWindowWithSize(paramInt1, paramInt2));
  }
  
  public final Observable<Observable<T>> window(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    return window(paramLong1, paramLong2, paramTimeUnit, Integer.MAX_VALUE, Schedulers.computation());
  }
  
  public final Observable<Observable<T>> window(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    return lift(new OperatorWindowWithTime(paramLong1, paramLong2, paramTimeUnit, paramInt, paramScheduler));
  }
  
  public final Observable<Observable<T>> window(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return window(paramLong1, paramLong2, paramTimeUnit, Integer.MAX_VALUE, paramScheduler);
  }
  
  public final Observable<Observable<T>> window(long paramLong, TimeUnit paramTimeUnit)
  {
    return window(paramLong, paramLong, paramTimeUnit, Schedulers.computation());
  }
  
  public final Observable<Observable<T>> window(long paramLong, TimeUnit paramTimeUnit, int paramInt)
  {
    return window(paramLong, paramTimeUnit, paramInt, Schedulers.computation());
  }
  
  public final Observable<Observable<T>> window(long paramLong, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    return window(paramLong, paramLong, paramTimeUnit, paramInt, paramScheduler);
  }
  
  public final Observable<Observable<T>> window(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return window(paramLong, paramTimeUnit, Integer.MAX_VALUE, paramScheduler);
  }
  
  public final <U> Observable<Observable<T>> window(Observable<U> paramObservable)
  {
    return lift(new OperatorWindowWithObservable(paramObservable));
  }
  
  public final <TOpening, TClosing> Observable<Observable<T>> window(Observable<? extends TOpening> paramObservable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> paramFunc1)
  {
    return lift(new OperatorWindowWithStartEndObservable(paramObservable, paramFunc1));
  }
  
  public final <TClosing> Observable<Observable<T>> window(Func0<? extends Observable<? extends TClosing>> paramFunc0)
  {
    return lift(new OperatorWindowWithObservableFactory(paramFunc0));
  }
  
  @Experimental
  public final <U, R> Observable<R> withLatestFrom(Observable<? extends U> paramObservable, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    return lift(new OperatorWithLatestFrom(paramObservable, paramFunc2));
  }
  
  public final <T2, R> Observable<R> zipWith(Iterable<? extends T2> paramIterable, Func2<? super T, ? super T2, ? extends R> paramFunc2)
  {
    return lift(new OperatorZipIterable(paramIterable, paramFunc2));
  }
  
  public final <T2, R> Observable<R> zipWith(Observable<? extends T2> paramObservable, Func2<? super T, ? super T2, ? extends R> paramFunc2)
  {
    return zip(this, paramObservable, paramFunc2);
  }
  
  private static class ThrowObservable<T>
    extends Observable<T>
  {
    public ThrowObservable(Throwable paramThrowable)
    {
      super()
      {
        public void call(Subscriber<? super T> paramAnonymousSubscriber)
        {
          paramAnonymousSubscriber.onError(Observable.ThrowObservable.this);
        }
      };
    }
  }
  
  private static class NeverObservable<T>
    extends Observable<T>
  {
    NeverObservable()
    {
      super()
      {
        public void call(Subscriber<? super T> paramAnonymousSubscriber) {}
      };
    }
    
    static <T> NeverObservable<T> instance()
    {
      return Holder.INSTANCE;
    }
    
    private static class Holder
    {
      static final Observable.NeverObservable<?> INSTANCE = new Observable.NeverObservable();
    }
  }
  
  private static class HolderAnyForEmpty
  {
    static final OperatorAny<?> INSTANCE = new OperatorAny(UtilityFunctions.alwaysTrue(), true);
  }
  
  private static final class CountLongHolder
  {
    static final Func2<Long, Object, Long> INSTANCE = new Func2()
    {
      public final Long call(Long paramAnonymousLong, Object paramAnonymousObject)
      {
        return Long.valueOf(1L + paramAnonymousLong.longValue());
      }
    };
  }
  
  private static final class CountHolder
  {
    static final Func2<Integer, Object, Integer> INSTANCE = new Func2()
    {
      public final Integer call(Integer paramAnonymousInteger, Object paramAnonymousObject)
      {
        return Integer.valueOf(1 + paramAnonymousInteger.intValue());
      }
    };
  }
  
  private static final class EmptyHolder
  {
    static final Observable<Object> INSTANCE = Observable.create(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super Object> paramAnonymousSubscriber)
      {
        paramAnonymousSubscriber.onCompleted();
      }
    });
  }
  
  public static abstract interface Transformer<T, R>
    extends Func1<Observable<T>, Observable<R>>
  {}
  
  public static abstract interface Operator<R, T>
    extends Func1<Subscriber<? super R>, Subscriber<? super T>>
  {}
  
  public static abstract interface OnSubscribe<T>
    extends Action1<Subscriber<? super T>>
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/Observable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */