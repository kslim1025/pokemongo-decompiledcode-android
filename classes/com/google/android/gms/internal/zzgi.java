package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;

@zzgr
public class zzgi
  extends zzgc
  implements zzja.zza
{
  zzgi(Context paramContext, zzhs.zza paramzza, zziz paramzziz, zzgg.zza paramzza1)
  {
    super(paramContext, paramzza, paramzziz, paramzza1);
  }
  
  protected void zzfs()
  {
    if (this.zzDf.errorCode != -2) {}
    for (;;)
    {
      return;
      this.zzoM.zzhe().zza(this);
      zzfz();
      zzb.v("Loading HTML in WebView.");
      this.zzoM.loadDataWithBaseURL(zzp.zzbv().zzaz(this.zzDf.zzBF), this.zzDf.body, "text/html", "UTF-8", null);
    }
  }
  
  protected void zzfz() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */