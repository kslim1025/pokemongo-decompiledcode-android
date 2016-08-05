package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzmd
  extends IInterface
{
  public abstract void zza(zzmc paramzzmc)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzmd
  {
    public static zzmd zzaQ(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzmd)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
        if ((localIInterface != null) && ((localIInterface instanceof zzmd))) {
          localObject = (zzmd)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.common.internal.service.ICommonService");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.service.ICommonService");
        zza(zzmc.zza.zzaP(paramParcel1.readStrongBinder()));
      }
    }
    
    private static class zza
      implements zzmd
    {
      private IBinder zznJ;
      
      zza(IBinder paramIBinder)
      {
        this.zznJ = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zznJ;
      }
      
      public void zza(zzmc paramzzmc)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
          if (paramzzmc != null) {
            localIBinder = paramzzmc.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          this.zznJ.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */