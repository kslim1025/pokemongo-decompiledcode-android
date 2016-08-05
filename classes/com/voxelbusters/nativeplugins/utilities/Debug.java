package com.voxelbusters.nativeplugins.utilities;

import android.util.Log;
import android.widget.Toast;
import com.voxelbusters.nativeplugins.NativePluginHelper;

public class Debug
{
  public static boolean ENABLED = false;
  
  public static void error(String paramString1, String paramString2)
  {
    if (ENABLED)
    {
      Log.e(paramString1, paramString2);
      toast("[" + paramString1 + "]" + paramString2);
    }
  }
  
  public static void log(String paramString1, String paramString2)
  {
    log(paramString1, paramString2, false);
  }
  
  public static void log(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (ENABLED) {
      Log.d(paramString1, paramString2);
    }
  }
  
  static void toast(String paramString)
  {
    NativePluginHelper.executeOnUIThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(NativePluginHelper.getCurrentContext(), Debug.this, 1).show();
      }
    });
  }
  
  public static void warning(String paramString1, String paramString2)
  {
    if (ENABLED) {
      Log.w(paramString1, paramString2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/Debug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */