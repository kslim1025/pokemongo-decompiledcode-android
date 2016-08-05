package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PlacePhotoMetadataResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzh();
  final int mVersionCode;
  private final Status zzSC;
  final DataHolder zzaGq;
  private final PlacePhotoMetadataBuffer zzaGr;
  
  PlacePhotoMetadataResult(int paramInt, Status paramStatus, DataHolder paramDataHolder)
  {
    this.mVersionCode = paramInt;
    this.zzSC = paramStatus;
    this.zzaGq = paramDataHolder;
    if (paramDataHolder == null) {}
    for (this.zzaGr = null;; this.zzaGr = new PlacePhotoMetadataBuffer(this.zzaGq)) {
      return;
    }
  }
  
  public PlacePhotoMetadataResult(Status paramStatus, DataHolder paramDataHolder)
  {
    this(0, paramStatus, paramDataHolder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PlacePhotoMetadataBuffer getPhotoMetadata()
  {
    return this.zzaGr;
  }
  
  public Status getStatus()
  {
    return this.zzSC;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoMetadataResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */