package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class by
  extends ci
{
  private String a = cg.a.a();
  private String b = ed.a.a();
  private String c;
  private String d;
  
  public by(String paramString1, String paramString2)
  {
    this.c = paramString1;
    this.d = paramString2;
  }
  
  public final JSONArray a()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("name", this.c);
    localHashMap.put("reason", this.d);
    return new JSONArray().put(this.b).put(6).put(new JSONObject(localHashMap));
  }
  
  public final String e()
  {
    return this.a;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/by.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */