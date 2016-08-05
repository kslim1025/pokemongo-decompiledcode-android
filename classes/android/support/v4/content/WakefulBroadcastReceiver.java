package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;

public abstract class WakefulBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
  private static final SparseArray<PowerManager.WakeLock> mActiveWakeLocks = new SparseArray();
  private static int mNextId = 1;
  
  public static boolean completeWakefulIntent(Intent paramIntent)
  {
    boolean bool = false;
    int i = paramIntent.getIntExtra("android.support.content.wakelockid", 0);
    if (i == 0) {}
    for (;;)
    {
      return bool;
      synchronized (mActiveWakeLocks)
      {
        PowerManager.WakeLock localWakeLock = (PowerManager.WakeLock)mActiveWakeLocks.get(i);
        if (localWakeLock != null)
        {
          localWakeLock.release();
          mActiveWakeLocks.remove(i);
          bool = true;
          continue;
        }
        Log.w("WakefulBroadcastReceiver", "No active wake lock id #" + i);
        bool = true;
      }
    }
  }
  
  public static ComponentName startWakefulService(Context paramContext, Intent paramIntent)
  {
    ComponentName localComponentName;
    synchronized (mActiveWakeLocks)
    {
      int i = mNextId;
      mNextId = 1 + mNextId;
      if (mNextId <= 0) {
        mNextId = 1;
      }
      paramIntent.putExtra("android.support.content.wakelockid", i);
      localComponentName = paramContext.startService(paramIntent);
      if (localComponentName == null)
      {
        localComponentName = null;
      }
      else
      {
        PowerManager.WakeLock localWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "wake:" + localComponentName.flattenToShortString());
        localWakeLock.setReferenceCounted(false);
        localWakeLock.acquire(60000L);
        mActiveWakeLocks.put(i, localWakeLock);
      }
    }
    return localComponentName;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/content/WakefulBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */