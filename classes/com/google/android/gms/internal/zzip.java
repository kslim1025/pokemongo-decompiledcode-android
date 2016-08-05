package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class zzip
{
  public static <A, B> zziq<B> zza(final zziq<A> paramzziq, final zza<A, B> paramzza)
  {
    zzin localzzin = new zzin();
    paramzziq.zzc(new Runnable()
    {
      public void run()
      {
        try
        {
          zzip.this.zzf(paramzza.zze(paramzziq.get()));
          return;
        }
        catch (ExecutionException localExecutionException)
        {
          for (;;)
          {
            zzip.this.cancel(true);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
        catch (CancellationException localCancellationException)
        {
          for (;;) {}
        }
      }
    });
    return localzzin;
  }
  
  public static <V> zziq<List<V>> zzh(final List<zziq<V>> paramList)
  {
    final zzin localzzin = new zzin();
    final int i = paramList.size();
    AtomicInteger localAtomicInteger = new AtomicInteger(0);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      ((zziq)localIterator.next()).zzc(new Runnable()
      {
        public void run()
        {
          if (zzip.this.incrementAndGet() >= i) {}
          try
          {
            localzzin.zzf(zzip.zzj(paramList));
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              zzb.zzd("Unable to convert list of futures to a future of list", localInterruptedException);
            }
          }
          catch (ExecutionException localExecutionException)
          {
            for (;;) {}
          }
        }
      });
    }
    return localzzin;
  }
  
  private static <V> List<V> zzi(List<zziq<V>> paramList)
    throws ExecutionException, InterruptedException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = ((zziq)localIterator.next()).get();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public static abstract interface zza<D, R>
  {
    public abstract R zze(D paramD);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */