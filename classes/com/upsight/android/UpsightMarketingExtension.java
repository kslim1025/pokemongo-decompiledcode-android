package com.upsight.android;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.internal.action.ActionMapResponse;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightMarketingApi;
import com.upsight.android.marketing.UpsightMarketingComponent;
import com.upsight.android.marketing.internal.BaseMarketingModule;
import com.upsight.android.marketing.internal.DaggerMarketingComponent;
import com.upsight.android.marketing.internal.DaggerMarketingComponent.Builder;
import com.upsight.android.marketing.internal.content.DefaultContentMediator;
import com.upsight.android.marketing.internal.content.MarketingContent;
import com.upsight.android.marketing.internal.content.MarketingContentFactory;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.annotation.Created;
import java.io.IOException;
import javax.inject.Inject;

public class UpsightMarketingExtension
  extends UpsightExtension<UpsightMarketingComponent, UpsightMarketingApi>
{
  public static final String EXTENSION_NAME = "com.upsight.extension.marketing";
  private static final String UPSIGHT_ACTION_MAP = "upsight.action_map";
  @Inject
  UpsightBillboardManager mBillboardManager;
  @Inject
  DefaultContentMediator mDefaultContentMediator;
  private UpsightLogger mLogger;
  private ObjectMapper mMapper;
  @Inject
  UpsightMarketingApi mMarketing;
  @Inject
  MarketingContentFactory mMarketingContentFactory;
  
  public UpsightMarketingApi getApi()
  {
    return this.mMarketing;
  }
  
  protected void onCreate(UpsightContext paramUpsightContext)
  {
    this.mMapper = paramUpsightContext.getCoreComponent().objectMapper();
    this.mLogger = paramUpsightContext.getLogger();
    this.mBillboardManager.registerContentMediator(this.mDefaultContentMediator);
    paramUpsightContext.getDataStore().subscribe(this);
  }
  
  protected UpsightMarketingComponent onResolve(UpsightContext paramUpsightContext)
  {
    return DaggerMarketingComponent.builder().baseMarketingModule(new BaseMarketingModule(paramUpsightContext)).build();
  }
  
  @Created
  public void onResponse(EndpointResponse paramEndpointResponse)
  {
    if (!"upsight.action_map".equals(paramEndpointResponse.getType())) {}
    for (;;)
    {
      return;
      try
      {
        JsonNode localJsonNode = this.mMapper.readTree(paramEndpointResponse.getContent());
        ActionMapResponse localActionMapResponse = (ActionMapResponse)this.mMapper.treeToValue(localJsonNode, ActionMapResponse.class);
        if ("marketing_content_factory".equals(localActionMapResponse.getActionFactory()))
        {
          MarketingContent localMarketingContent = this.mMarketingContentFactory.create(localActionMapResponse);
          if (localMarketingContent != null) {
            localMarketingContent.executeActions("content_received");
          }
        }
      }
      catch (IOException localIOException)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.w("Upsight", "Unable to parse action map", arrayOfObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightMarketingExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */