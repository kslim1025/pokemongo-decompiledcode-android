package org.apache.commons.io.output;

import java.io.OutputStream;

public class CloseShieldOutputStream
  extends ProxyOutputStream
{
  public CloseShieldOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }
  
  public void close()
  {
    this.out = new ClosedOutputStream();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/output/CloseShieldOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */