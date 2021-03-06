package com.upsight.android.internal.persistence.subscription;

import com.upsight.android.persistence.annotation.UpsightStorableType;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class SubscriptionHandlerVisitor
  implements ClassSubscriptionVisitor
{
  private final Set<SubscriptionHandler> mHandlers = new HashSet();
  private final Object mTarget;
  
  SubscriptionHandlerVisitor(Object paramObject)
  {
    this.mTarget = paramObject;
  }
  
  public Set<SubscriptionHandler> getHandlers()
  {
    return new HashSet(this.mHandlers);
  }
  
  public void visitCreatedSubscription(Method paramMethod, Class<?> paramClass)
  {
    UpsightStorableType localUpsightStorableType = (UpsightStorableType)paramClass.getAnnotation(UpsightStorableType.class);
    if (localUpsightStorableType != null) {
      this.mHandlers.add(new SubscriptionHandler(this.mTarget, paramMethod, DataStoreEvent.Action.Created, localUpsightStorableType.value()));
    }
  }
  
  public void visitRemovedSubscription(Method paramMethod, Class<?> paramClass)
  {
    UpsightStorableType localUpsightStorableType = (UpsightStorableType)paramClass.getAnnotation(UpsightStorableType.class);
    if (localUpsightStorableType != null) {
      this.mHandlers.add(new SubscriptionHandler(this.mTarget, paramMethod, DataStoreEvent.Action.Removed, localUpsightStorableType.value()));
    }
  }
  
  public void visitUpdatedSubscription(Method paramMethod, Class<?> paramClass)
  {
    UpsightStorableType localUpsightStorableType = (UpsightStorableType)paramClass.getAnnotation(UpsightStorableType.class);
    if (localUpsightStorableType != null) {
      this.mHandlers.add(new SubscriptionHandler(this.mTarget, paramMethod, DataStoreEvent.Action.Updated, localUpsightStorableType.value()));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/SubscriptionHandlerVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */