package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public abstract class af
{
  al a;
  CharArrayBuffer b;
  protected int c;
  private int d;
  
  public af(af paramaf)
  {
    a(paramaf.a, paramaf.c);
  }
  
  public af(al paramal)
  {
    a(paramal, 0);
  }
  
  private void a(al paramal, int paramInt)
  {
    this.a = paramal;
    this.d = e();
    this.b = new CharArrayBuffer(d());
    this.c = paramInt;
  }
  
  private void g()
  {
    this.a.a(as.d);
  }
  
  public final int a()
  {
    return this.c;
  }
  
  public final void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = b(paramArrayOfByte, paramInt1, paramInt2);
    while ((i > 0) && (i < paramInt2))
    {
      int j = this.a.a().b(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      if (j <= 0) {
        break;
      }
      i += j;
    }
  }
  
  public boolean a(int paramInt)
  {
    boolean bool = true;
    if (paramInt == -1)
    {
      g();
      return bool;
    }
    this.c = (1 + this.c);
    char c1 = (char)paramInt;
    Object localObject;
    if (c1 == '\n') {
      if (a(this.b)) {
        localObject = b();
      }
    }
    for (;;)
    {
      if (localObject != this) {
        this.a.a((af)localObject);
      }
      if (localObject != this) {
        break;
      }
      bool = false;
      break;
      localObject = as.d;
      continue;
      if (this.b.length() < this.d)
      {
        this.b.append(c1);
        localObject = this;
      }
      else
      {
        localObject = c();
      }
    }
  }
  
  public abstract boolean a(CharArrayBuffer paramCharArrayBuffer);
  
  protected int b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    int i = -1;
    if (paramInt2 == i) {
      g();
    }
    for (;;)
    {
      return i;
      if ((paramArrayOfByte != null) && (paramInt2 != 0)) {
        for (i = 0; (!bool) && (i < paramInt2); i++) {
          bool = a((char)paramArrayOfByte[(paramInt1 + i)]);
        }
      }
    }
  }
  
  public abstract af b();
  
  public final void b(int paramInt)
  {
    this.c = paramInt;
  }
  
  public abstract af c();
  
  protected abstract int d();
  
  protected abstract int e();
  
  public void f()
  {
    if (this.a != null) {
      this.a.a(as.d);
    }
  }
  
  public final String toString()
  {
    return this.b.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */