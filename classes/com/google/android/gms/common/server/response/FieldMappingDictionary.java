package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FieldMappingDictionary
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  private final int mVersionCode;
  private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzahe;
  private final ArrayList<Entry> zzahf;
  private final String zzahg;
  
  FieldMappingDictionary(int paramInt, ArrayList<Entry> paramArrayList, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzahf = null;
    this.zzahe = zzc(paramArrayList);
    this.zzahg = ((String)zzx.zzw(paramString));
    zzpQ();
  }
  
  public FieldMappingDictionary(Class<? extends FastJsonResponse> paramClass)
  {
    this.mVersionCode = 1;
    this.zzahf = null;
    this.zzahe = new HashMap();
    this.zzahg = paramClass.getCanonicalName();
  }
  
  private static HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzc(ArrayList<Entry> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Entry localEntry = (Entry)paramArrayList.get(j);
      localHashMap.put(localEntry.className, localEntry.zzpU());
    }
    return localHashMap;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.zzahe.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      localStringBuilder.append(str1).append(":\n");
      Map localMap = (Map)this.zzahe.get(str1);
      Iterator localIterator2 = localMap.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        localStringBuilder.append("  ").append(str2).append(": ");
        localStringBuilder.append(localMap.get(str2));
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public void zza(Class<? extends FastJsonResponse> paramClass, Map<String, FastJsonResponse.Field<?, ?>> paramMap)
  {
    this.zzahe.put(paramClass.getCanonicalName(), paramMap);
  }
  
  public boolean zzb(Class<? extends FastJsonResponse> paramClass)
  {
    return this.zzahe.containsKey(paramClass.getCanonicalName());
  }
  
  public Map<String, FastJsonResponse.Field<?, ?>> zzcw(String paramString)
  {
    return (Map)this.zzahe.get(paramString);
  }
  
  public void zzpQ()
  {
    Iterator localIterator1 = this.zzahe.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      Map localMap = (Map)this.zzahe.get(str);
      Iterator localIterator2 = localMap.keySet().iterator();
      while (localIterator2.hasNext()) {
        ((FastJsonResponse.Field)localMap.get((String)localIterator2.next())).zza(this);
      }
    }
  }
  
  public void zzpR()
  {
    Iterator localIterator1 = this.zzahe.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      Map localMap = (Map)this.zzahe.get(str1);
      HashMap localHashMap = new HashMap();
      Iterator localIterator2 = localMap.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        localHashMap.put(str2, ((FastJsonResponse.Field)localMap.get(str2)).zzpG());
      }
      this.zzahe.put(str1, localHashMap);
    }
  }
  
  ArrayList<Entry> zzpS()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzahe.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new Entry(str, (Map)this.zzahe.get(str)));
    }
    return localArrayList;
  }
  
  public String zzpT()
  {
    return this.zzahg;
  }
  
  public static class FieldMapPair
    implements SafeParcelable
  {
    public static final zzb CREATOR = new zzb();
    final String key;
    final int versionCode;
    final FastJsonResponse.Field<?, ?> zzahi;
    
    FieldMapPair(int paramInt, String paramString, FastJsonResponse.Field<?, ?> paramField)
    {
      this.versionCode = paramInt;
      this.key = paramString;
      this.zzahi = paramField;
    }
    
    FieldMapPair(String paramString, FastJsonResponse.Field<?, ?> paramField)
    {
      this.versionCode = 1;
      this.key = paramString;
      this.zzahi = paramField;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzb.zza(this, paramParcel, paramInt);
    }
  }
  
  public static class Entry
    implements SafeParcelable
  {
    public static final zzd CREATOR = new zzd();
    final String className;
    final int versionCode;
    final ArrayList<FieldMappingDictionary.FieldMapPair> zzahh;
    
    Entry(int paramInt, String paramString, ArrayList<FieldMappingDictionary.FieldMapPair> paramArrayList)
    {
      this.versionCode = paramInt;
      this.className = paramString;
      this.zzahh = paramArrayList;
    }
    
    Entry(String paramString, Map<String, FastJsonResponse.Field<?, ?>> paramMap)
    {
      this.versionCode = 1;
      this.className = paramString;
      this.zzahh = zzF(paramMap);
    }
    
    private static ArrayList<FieldMappingDictionary.FieldMapPair> zzF(Map<String, FastJsonResponse.Field<?, ?>> paramMap)
    {
      if (paramMap == null) {}
      ArrayList localArrayList;
      for (Object localObject = null;; localObject = localArrayList)
      {
        return (ArrayList<FieldMappingDictionary.FieldMapPair>)localObject;
        localArrayList = new ArrayList();
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localArrayList.add(new FieldMappingDictionary.FieldMapPair(str, (FastJsonResponse.Field)paramMap.get(str)));
        }
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzd.zza(this, paramParcel, paramInt);
    }
    
    HashMap<String, FastJsonResponse.Field<?, ?>> zzpU()
    {
      HashMap localHashMap = new HashMap();
      int i = this.zzahh.size();
      for (int j = 0; j < i; j++)
      {
        FieldMappingDictionary.FieldMapPair localFieldMapPair = (FieldMappingDictionary.FieldMapPair)this.zzahh.get(j);
        localHashMap.put(localFieldMapPair.key, localFieldMapPair.zzahi);
      }
      return localHashMap;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/response/FieldMappingDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */