package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzme<K, V>
  extends zzmi<K, V>
  implements Map<K, V>
{
  zzmh<K, V> zzagz;
  
  private zzmh<K, V> zzpx()
  {
    if (this.zzagz == null) {
      this.zzagz = new zzmh()
      {
        protected void colClear()
        {
          zzme.this.clear();
        }
        
        protected Object colGetEntry(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          return zzme.this.mArray[(paramAnonymousInt2 + (paramAnonymousInt1 << 1))];
        }
        
        protected Map<K, V> colGetMap()
        {
          return zzme.this;
        }
        
        protected int colGetSize()
        {
          return zzme.this.mSize;
        }
        
        protected int colIndexOfKey(Object paramAnonymousObject)
        {
          if (paramAnonymousObject == null) {}
          for (int i = zzme.this.indexOfNull();; i = zzme.this.indexOf(paramAnonymousObject, paramAnonymousObject.hashCode())) {
            return i;
          }
        }
        
        protected int colIndexOfValue(Object paramAnonymousObject)
        {
          return zzme.this.indexOfValue(paramAnonymousObject);
        }
        
        protected void colPut(K paramAnonymousK, V paramAnonymousV)
        {
          zzme.this.put(paramAnonymousK, paramAnonymousV);
        }
        
        protected void colRemoveAt(int paramAnonymousInt)
        {
          zzme.this.removeAt(paramAnonymousInt);
        }
        
        protected V colSetValue(int paramAnonymousInt, V paramAnonymousV)
        {
          return (V)zzme.this.setValueAt(paramAnonymousInt, paramAnonymousV);
        }
      };
    }
    return this.zzagz;
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    return zzpx().getEntrySet();
  }
  
  public Set<K> keySet()
  {
    return zzpx().getKeySet();
  }
  
  public void putAll(Map<? extends K, ? extends V> paramMap)
  {
    ensureCapacity(this.mSize + paramMap.size());
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      put(localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public Collection<V> values()
  {
    return zzpx().getValues();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzme.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */