package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;

public class MediaSessionCompatApi8
{
  public static void registerMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).registerMediaButtonEventReceiver(paramComponentName);
  }
  
  public static void unregisterMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(paramComponentName);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/media/session/MediaSessionCompatApi8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */