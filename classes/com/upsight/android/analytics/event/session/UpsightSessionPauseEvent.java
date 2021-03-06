package com.upsight.android.analytics.event.session;

import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.session.pause")
public class UpsightSessionPauseEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightSessionPauseEvent() {}
  
  protected UpsightSessionPauseEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder()
  {
    return new Builder();
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightSessionPauseEvent, UpsightSessionPauseEvent.UpsightData>
  {
    protected UpsightSessionPauseEvent build()
    {
      return new UpsightSessionPauseEvent("upsight.session.pause", new UpsightSessionPauseEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
  }
  
  static class UpsightData
  {
    protected UpsightData() {}
    
    protected UpsightData(UpsightSessionPauseEvent.Builder paramBuilder) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/session/UpsightSessionPauseEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */