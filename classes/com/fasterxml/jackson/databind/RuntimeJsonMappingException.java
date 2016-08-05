package com.fasterxml.jackson.databind;

public class RuntimeJsonMappingException
  extends RuntimeException
{
  public RuntimeJsonMappingException(JsonMappingException paramJsonMappingException)
  {
    super(paramJsonMappingException);
  }
  
  public RuntimeJsonMappingException(String paramString)
  {
    super(paramString);
  }
  
  public RuntimeJsonMappingException(String paramString, JsonMappingException paramJsonMappingException)
  {
    super(paramString, paramJsonMappingException);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/RuntimeJsonMappingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */