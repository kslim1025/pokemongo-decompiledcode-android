package com.upsight.android.analytics.internal.session;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class ActivityLifecycleTracker_Factory
  implements Factory<ActivityLifecycleTracker>
{
  private final Provider<ManualTracker> trackerProvider;
  
  static
  {
    if (!ActivityLifecycleTracker_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ActivityLifecycleTracker_Factory(Provider<ManualTracker> paramProvider)
  {
    assert (paramProvider != null);
    this.trackerProvider = paramProvider;
  }
  
  public static Factory<ActivityLifecycleTracker> create(Provider<ManualTracker> paramProvider)
  {
    return new ActivityLifecycleTracker_Factory(paramProvider);
  }
  
  public ActivityLifecycleTracker get()
  {
    return new ActivityLifecycleTracker((ManualTracker)this.trackerProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ActivityLifecycleTracker_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */