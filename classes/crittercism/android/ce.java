package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ce
  extends ci
{
  private String a;
  private String b;
  private a c;
  private String d;
  private String e;
  private String f;
  
  public ce(a parama)
  {
    if (parama != a.a) {}
    this.a = cg.a.a();
    this.b = ed.a.a();
    this.c = parama;
  }
  
  public ce(a parama, String paramString)
  {
    if (parama != a.c) {}
    this.a = cg.a.a();
    this.b = ed.a.a();
    this.c = parama;
    this.d = paramString;
  }
  
  public ce(a parama, String paramString1, String paramString2)
  {
    this.a = cg.a.a();
    this.b = ed.a.a();
    this.c = parama;
    this.e = paramString1;
    this.f = paramString2;
  }
  
  public final JSONArray a()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("change", Integer.valueOf(this.c.ordinal()));
    if ((this.c == a.c) || (this.c == a.d)) {
      localHashMap.put("type", this.d);
    }
    for (;;)
    {
      return new JSONArray().put(this.b).put(4).put(new JSONObject(localHashMap));
      if (this.c == a.e)
      {
        localHashMap.put("oldType", this.e);
        localHashMap.put("newType", this.f);
      }
    }
  }
  
  public final String e()
  {
    return this.a;
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[5];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      arrayOfa[3] = d;
      arrayOfa[4] = e;
      f = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */