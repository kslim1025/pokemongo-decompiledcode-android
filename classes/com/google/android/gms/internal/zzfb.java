package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

@zzgr
public class zzfb
  extends zzfh
{
  private final Context mContext;
  private final Map<String, String> zzvS;
  private String zzzV;
  private long zzzW;
  private long zzzX;
  private String zzzY;
  private String zzzZ;
  
  public zzfb(zziz paramzziz, Map<String, String> paramMap)
  {
    super(paramzziz, "createCalendarEvent");
    this.zzvS = paramMap;
    this.mContext = paramzziz.zzgZ();
    zzec();
  }
  
  private String zzah(String paramString)
  {
    if (TextUtils.isEmpty((CharSequence)this.zzvS.get(paramString))) {}
    for (String str = "";; str = (String)this.zzvS.get(paramString)) {
      return str;
    }
  }
  
  private long zzai(String paramString)
  {
    String str = (String)this.zzvS.get(paramString);
    long l1;
    if (str == null) {
      l1 = -1L;
    }
    for (;;)
    {
      return l1;
      try
      {
        long l2 = Long.parseLong(str);
        l1 = l2;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        l1 = -1L;
      }
    }
  }
  
  private void zzec()
  {
    this.zzzV = zzah("description");
    this.zzzY = zzah("summary");
    this.zzzW = zzai("start_ticks");
    this.zzzX = zzai("end_ticks");
    this.zzzZ = zzah("location");
  }
  
  Intent createIntent()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
    localIntent.putExtra("title", this.zzzV);
    localIntent.putExtra("eventLocation", this.zzzZ);
    localIntent.putExtra("description", this.zzzY);
    if (this.zzzW > -1L) {
      localIntent.putExtra("beginTime", this.zzzW);
    }
    if (this.zzzX > -1L) {
      localIntent.putExtra("endTime", this.zzzX);
    }
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public void execute()
  {
    if (this.mContext == null) {
      zzak("Activity context is not available.");
    }
    for (;;)
    {
      return;
      if (!zzp.zzbv().zzL(this.mContext).zzdb())
      {
        zzak("This feature is not available on the device.");
      }
      else
      {
        AlertDialog.Builder localBuilder = zzp.zzbv().zzK(this.mContext);
        localBuilder.setTitle(zzp.zzby().zzd(R.string.create_calendar_title, "Create calendar event"));
        localBuilder.setMessage(zzp.zzby().zzd(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
        localBuilder.setPositiveButton(zzp.zzby().zzd(R.string.accept, "Accept"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Intent localIntent = zzfb.this.createIntent();
            zzp.zzbv().zzb(zzfb.zza(zzfb.this), localIntent);
          }
        });
        localBuilder.setNegativeButton(zzp.zzby().zzd(R.string.decline, "Decline"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            zzfb.this.zzak("Operation denied by user.");
          }
        });
        localBuilder.create().show();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */