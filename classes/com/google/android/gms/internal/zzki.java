package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

public final class zzki
  extends zzj<zzkk>
{
  private final Bundle zzSa;
  
  public zzki(Context paramContext, Looper paramLooper, zzf paramzzf, Auth.zza paramzza, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 16, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    if (paramzza == null) {}
    for (Bundle localBundle = new Bundle();; localBundle = paramzza.zzlE())
    {
      this.zzSa = localBundle;
      return;
    }
  }
  
  protected zzkk zzau(IBinder paramIBinder)
  {
    return zzkk.zza.zzaw(paramIBinder);
  }
  
  protected String zzfK()
  {
    return "com.google.android.gms.auth.service.START";
  }
  
  protected String zzfL()
  {
    return "com.google.android.gms.auth.api.internal.IAuthService";
  }
  
  public boolean zzlN()
  {
    zzf localzzf = zzpa();
    if ((!TextUtils.isEmpty(localzzf.getAccountName())) && (!localzzf.zzb(Auth.PROXY_API).isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Bundle zzly()
  {
    return this.zzSa;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzki.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */