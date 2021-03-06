package com.upsight.android.managedvariables.internal;

import com.upsight.android.managedvariables.R.raw;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ResourceModule
{
  public static final String RES_UXM_SCHEMA = "resUxmSchema";
  
  @Provides
  @Named("resUxmSchema")
  @Singleton
  Integer provideUxmSchemaResource()
  {
    return Integer.valueOf(R.raw.uxm_schema);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/ResourceModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */