package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class HiddenFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  public static final IOFileFilter HIDDEN = new HiddenFileFilter();
  public static final IOFileFilter VISIBLE = new NotFileFilter(HIDDEN);
  
  public boolean accept(File paramFile)
  {
    return paramFile.isHidden();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/HiddenFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */