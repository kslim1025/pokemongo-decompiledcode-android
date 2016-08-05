package com.upsight.android.internal.persistence;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

class OnSubscribeRemove
  implements Observable.OnSubscribe<Storable>
{
  private final Context mContext;
  private final Storable mStorable;
  
  OnSubscribeRemove(Context paramContext, Storable paramStorable)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Provided Context can not be null.");
    }
    if (paramStorable == null) {
      throw new IllegalArgumentException("Provided UpsightStorable can not be null.");
    }
    this.mContext = paramContext;
    this.mStorable = paramStorable;
  }
  
  public void call(Subscriber<? super Storable> paramSubscriber)
  {
    Uri localUri = Uri.withAppendedPath(Content.getContentTypeUri(this.mContext, this.mStorable.getType()), this.mStorable.getID());
    if (this.mContext.getContentResolver().delete(localUri, null, null) > 0)
    {
      paramSubscriber.onNext(this.mStorable);
      paramSubscriber.onCompleted();
    }
    for (;;)
    {
      return;
      paramSubscriber.onError(new IllegalStateException("Object could not be removed. Already removed?"));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/OnSubscribeRemove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */