package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.personalized.internal.TestDataImpl;
import java.util.ArrayList;

public class zze
  implements Parcelable.Creator<PlaceUserData>
{
  static void zza(PlaceUserData paramPlaceUserData, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlaceUserData.zzxr(), false);
    zzb.zzc(paramParcel, 1000, paramPlaceUserData.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlaceUserData.getPlaceId(), false);
    zzb.zzc(paramParcel, 5, paramPlaceUserData.zzxu(), false);
    zzb.zzc(paramParcel, 6, paramPlaceUserData.zzxs(), false);
    zzb.zzc(paramParcel, 7, paramPlaceUserData.zzxt(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceUserData zzfd(Parcel paramParcel)
  {
    ArrayList localArrayList1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
    String str1 = null;
    String str2 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        localArrayList3 = zza.zzc(paramParcel, k, TestDataImpl.CREATOR);
        break;
      case 6: 
        localArrayList2 = zza.zzc(paramParcel, k, PlaceAlias.CREATOR);
        break;
      case 7: 
        localArrayList1 = zza.zzc(paramParcel, k, HereContent.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceUserData(j, str2, str1, localArrayList3, localArrayList2, localArrayList1);
  }
  
  public PlaceUserData[] zzhB(int paramInt)
  {
    return new PlaceUserData[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */