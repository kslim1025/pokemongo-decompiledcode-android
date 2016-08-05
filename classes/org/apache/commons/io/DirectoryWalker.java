package org.apache.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public abstract class DirectoryWalker<T>
{
  private final int depthLimit;
  private final FileFilter filter;
  
  protected DirectoryWalker()
  {
    this(null, -1);
  }
  
  protected DirectoryWalker(FileFilter paramFileFilter, int paramInt)
  {
    this.filter = paramFileFilter;
    this.depthLimit = paramInt;
  }
  
  protected DirectoryWalker(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2, int paramInt)
  {
    if ((paramIOFileFilter1 == null) && (paramIOFileFilter2 == null))
    {
      this.filter = null;
      this.depthLimit = paramInt;
      return;
    }
    if (paramIOFileFilter1 != null) {
      label27:
      if (paramIOFileFilter2 == null) {
        break label80;
      }
    }
    for (;;)
    {
      IOFileFilter localIOFileFilter1 = FileFilterUtils.makeDirectoryOnly(paramIOFileFilter1);
      IOFileFilter localIOFileFilter2 = FileFilterUtils.makeFileOnly(paramIOFileFilter2);
      IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
      arrayOfIOFileFilter[0] = localIOFileFilter1;
      arrayOfIOFileFilter[1] = localIOFileFilter2;
      this.filter = FileFilterUtils.or(arrayOfIOFileFilter);
      break;
      paramIOFileFilter1 = TrueFileFilter.TRUE;
      break label27;
      label80:
      paramIOFileFilter2 = TrueFileFilter.TRUE;
    }
  }
  
  private void walk(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {
    checkIfCancelled(paramFile, paramInt, paramCollection);
    int i;
    if (handleDirectory(paramFile, paramInt, paramCollection))
    {
      handleDirectoryStart(paramFile, paramInt, paramCollection);
      i = paramInt + 1;
      if ((this.depthLimit < 0) || (i <= this.depthLimit))
      {
        checkIfCancelled(paramFile, paramInt, paramCollection);
        if (this.filter != null) {
          break label103;
        }
      }
    }
    File[] arrayOfFile2;
    label103:
    for (File[] arrayOfFile1 = paramFile.listFiles();; arrayOfFile1 = paramFile.listFiles(this.filter))
    {
      arrayOfFile2 = filterDirectoryContents(paramFile, paramInt, arrayOfFile1);
      if (arrayOfFile2 != null) {
        break;
      }
      handleRestricted(paramFile, i, paramCollection);
      handleDirectoryEnd(paramFile, paramInt, paramCollection);
      checkIfCancelled(paramFile, paramInt, paramCollection);
      return;
    }
    int j = arrayOfFile2.length;
    int k = 0;
    label124:
    File localFile;
    if (k < j)
    {
      localFile = arrayOfFile2[k];
      if (!localFile.isDirectory()) {
        break label161;
      }
      walk(localFile, i, paramCollection);
    }
    for (;;)
    {
      k++;
      break label124;
      break;
      label161:
      checkIfCancelled(localFile, i, paramCollection);
      handleFile(localFile, i, paramCollection);
      checkIfCancelled(localFile, i, paramCollection);
    }
  }
  
  protected final void checkIfCancelled(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {
    if (handleIsCancelled(paramFile, paramInt, paramCollection)) {
      throw new CancelException(paramFile, paramInt);
    }
  }
  
  protected File[] filterDirectoryContents(File paramFile, int paramInt, File[] paramArrayOfFile)
    throws IOException
  {
    return paramArrayOfFile;
  }
  
  protected void handleCancelled(File paramFile, Collection<T> paramCollection, CancelException paramCancelException)
    throws IOException
  {
    throw paramCancelException;
  }
  
  protected boolean handleDirectory(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {
    return true;
  }
  
  protected void handleDirectoryEnd(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {}
  
  protected void handleDirectoryStart(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {}
  
  protected void handleEnd(Collection<T> paramCollection)
    throws IOException
  {}
  
  protected void handleFile(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {}
  
  protected boolean handleIsCancelled(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {
    return false;
  }
  
  protected void handleRestricted(File paramFile, int paramInt, Collection<T> paramCollection)
    throws IOException
  {}
  
  protected void handleStart(File paramFile, Collection<T> paramCollection)
    throws IOException
  {}
  
  protected final void walk(File paramFile, Collection<T> paramCollection)
    throws IOException
  {
    if (paramFile == null) {
      throw new NullPointerException("Start Directory is null");
    }
    try
    {
      handleStart(paramFile, paramCollection);
      walk(paramFile, 0, paramCollection);
      handleEnd(paramCollection);
      return;
    }
    catch (CancelException localCancelException)
    {
      for (;;)
      {
        handleCancelled(paramFile, paramCollection, localCancelException);
      }
    }
  }
  
  public static class CancelException
    extends IOException
  {
    private static final long serialVersionUID = 1347339620135041008L;
    private final int depth;
    private final File file;
    
    public CancelException(File paramFile, int paramInt)
    {
      this("Operation Cancelled", paramFile, paramInt);
    }
    
    public CancelException(String paramString, File paramFile, int paramInt)
    {
      super();
      this.file = paramFile;
      this.depth = paramInt;
    }
    
    public int getDepth()
    {
      return this.depth;
    }
    
    public File getFile()
    {
      return this.file;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/DirectoryWalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */