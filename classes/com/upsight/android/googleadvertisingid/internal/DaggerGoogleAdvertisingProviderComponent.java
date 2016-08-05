package com.upsight.android.googleadvertisingid.internal;

import com.upsight.android.UpsightGoogleAdvertisingIdExtension;
import com.upsight.android.UpsightGoogleAdvertisingIdExtension_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;

public final class DaggerGoogleAdvertisingProviderComponent
  implements GoogleAdvertisingProviderComponent
{
  private Provider<GooglePlayAdvertisingProvider> provideGooglePlayAdvertisingProvider;
  private MembersInjector<UpsightGoogleAdvertisingIdExtension> upsightGoogleAdvertisingIdExtensionMembersInjector;
  
  static
  {
    if (!DaggerGoogleAdvertisingProviderComponent.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private DaggerGoogleAdvertisingProviderComponent(Builder paramBuilder)
  {
    assert (paramBuilder != null);
    initialize(paramBuilder);
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  private void initialize(Builder paramBuilder)
  {
    this.provideGooglePlayAdvertisingProvider = ScopedProvider.create(GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory.create(paramBuilder.googleAdvertisingProviderModule));
    this.upsightGoogleAdvertisingIdExtensionMembersInjector = UpsightGoogleAdvertisingIdExtension_MembersInjector.create(MembersInjectors.noOp(), this.provideGooglePlayAdvertisingProvider);
  }
  
  public void inject(UpsightGoogleAdvertisingIdExtension paramUpsightGoogleAdvertisingIdExtension)
  {
    this.upsightGoogleAdvertisingIdExtensionMembersInjector.injectMembers(paramUpsightGoogleAdvertisingIdExtension);
  }
  
  public static final class Builder
  {
    private GoogleAdvertisingProviderModule googleAdvertisingProviderModule;
    
    public GoogleAdvertisingProviderComponent build()
    {
      if (this.googleAdvertisingProviderModule == null) {
        throw new IllegalStateException("googleAdvertisingProviderModule must be set");
      }
      return new DaggerGoogleAdvertisingProviderComponent(this, null);
    }
    
    public Builder googleAdvertisingProviderModule(GoogleAdvertisingProviderModule paramGoogleAdvertisingProviderModule)
    {
      if (paramGoogleAdvertisingProviderModule == null) {
        throw new NullPointerException("googleAdvertisingProviderModule");
      }
      this.googleAdvertisingProviderModule = paramGoogleAdvertisingProviderModule;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googleadvertisingid/internal/DaggerGoogleAdvertisingProviderComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */