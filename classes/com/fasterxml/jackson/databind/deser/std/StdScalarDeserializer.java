package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public abstract class StdScalarDeserializer<T>
  extends StdDeserializer<T>
{
  private static final long serialVersionUID = 1L;
  
  protected StdScalarDeserializer(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  protected StdScalarDeserializer(StdScalarDeserializer<?> paramStdScalarDeserializer)
  {
    super(paramStdScalarDeserializer);
  }
  
  protected StdScalarDeserializer(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromScalar(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdScalarDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */