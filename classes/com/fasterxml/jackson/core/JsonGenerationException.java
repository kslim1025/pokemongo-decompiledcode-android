package com.fasterxml.jackson.core;

public class JsonGenerationException
  extends JsonProcessingException
{
  private static final long serialVersionUID = 123L;
  
  public JsonGenerationException(String paramString)
  {
    super(paramString, (JsonLocation)null);
  }
  
  public JsonGenerationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, null, paramThrowable);
  }
  
  public JsonGenerationException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonGenerationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */