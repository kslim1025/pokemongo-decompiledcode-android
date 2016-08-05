package com.upsight.android.marketing;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightMarketingExtension;
import com.upsight.android.logger.UpsightLogger;

public abstract class UpsightMarketingContentStore
{
  public static boolean isContentReady(UpsightContext paramUpsightContext, String paramString)
  {
    boolean bool = false;
    UpsightMarketingExtension localUpsightMarketingExtension = (UpsightMarketingExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.marketing");
    if (localUpsightMarketingExtension != null) {
      bool = localUpsightMarketingExtension.getApi().isContentReady(paramString);
    }
    for (;;)
    {
      return bool;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.marketing must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public abstract boolean isContentReady(String paramString);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightMarketingContentStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */