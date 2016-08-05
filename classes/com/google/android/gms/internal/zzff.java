package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzff
{
  private final boolean zzAv;
  private final boolean zzAw;
  private final boolean zzAx;
  private final boolean zzAy;
  private final boolean zzAz;
  
  private zzff(zza paramzza)
  {
    this.zzAv = zza.zza(paramzza);
    this.zzAw = zza.zzb(paramzza);
    this.zzAx = zza.zzc(paramzza);
    this.zzAy = zza.zzd(paramzza);
    this.zzAz = zza.zze(paramzza);
  }
  
  public JSONObject toJson()
  {
    try
    {
      JSONObject localJSONObject2 = new JSONObject().put("sms", this.zzAv).put("tel", this.zzAw).put("calendar", this.zzAx).put("storePicture", this.zzAy).put("inlineVideo", this.zzAz);
      localJSONObject1 = localJSONObject2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while obtaining the MRAID capabilities.", localJSONException);
        JSONObject localJSONObject1 = null;
      }
    }
    return localJSONObject1;
  }
  
  public static final class zza
  {
    private boolean zzAv;
    private boolean zzAw;
    private boolean zzAx;
    private boolean zzAy;
    private boolean zzAz;
    
    public zzff zzeh()
    {
      return new zzff(this, null);
    }
    
    public zza zzo(boolean paramBoolean)
    {
      this.zzAv = paramBoolean;
      return this;
    }
    
    public zza zzp(boolean paramBoolean)
    {
      this.zzAw = paramBoolean;
      return this;
    }
    
    public zza zzq(boolean paramBoolean)
    {
      this.zzAx = paramBoolean;
      return this;
    }
    
    public zza zzr(boolean paramBoolean)
    {
      this.zzAy = paramBoolean;
      return this;
    }
    
    public zza zzs(boolean paramBoolean)
    {
      this.zzAz = paramBoolean;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */