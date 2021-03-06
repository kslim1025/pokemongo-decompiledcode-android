package com.upsight.android.analytics.event.milestone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.milestone")
public class UpsightMilestoneEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightMilestoneEvent() {}
  
  protected UpsightMilestoneEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString)
  {
    return new Builder(paramString);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightMilestoneEvent, UpsightMilestoneEvent.UpsightData>
  {
    private String scope;
    
    protected Builder(String paramString)
    {
      this.scope = paramString;
    }
    
    protected UpsightMilestoneEvent build()
    {
      return new UpsightMilestoneEvent("upsight.milestone", new UpsightMilestoneEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
  }
  
  static class UpsightData
  {
    @JsonProperty("scope")
    String scope;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightMilestoneEvent.Builder paramBuilder)
    {
      this.scope = paramBuilder.scope;
    }
    
    public String getScope()
    {
      return this.scope;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/milestone/UpsightMilestoneEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */