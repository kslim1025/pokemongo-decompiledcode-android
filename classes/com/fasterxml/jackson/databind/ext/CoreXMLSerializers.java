package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLSerializers
  extends Serializers.Base
{
  public JsonSerializer<?> findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
  {
    Class localClass = paramJavaType.getRawClass();
    Object localObject;
    if ((Duration.class.isAssignableFrom(localClass)) || (QName.class.isAssignableFrom(localClass))) {
      localObject = ToStringSerializer.instance;
    }
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      if (XMLGregorianCalendar.class.isAssignableFrom(localClass)) {
        localObject = XMLGregorianCalendarSerializer.instance;
      } else {
        localObject = null;
      }
    }
  }
  
  public static class XMLGregorianCalendarSerializer
    extends StdSerializer<XMLGregorianCalendar>
    implements ContextualSerializer
  {
    static final XMLGregorianCalendarSerializer instance = new XMLGregorianCalendarSerializer();
    final JsonSerializer<Object> _delegate;
    
    public XMLGregorianCalendarSerializer()
    {
      this(CalendarSerializer.instance);
    }
    
    protected XMLGregorianCalendarSerializer(JsonSerializer<?> paramJsonSerializer)
    {
      super();
      this._delegate = paramJsonSerializer;
    }
    
    protected Calendar _convert(XMLGregorianCalendar paramXMLGregorianCalendar)
    {
      if (paramXMLGregorianCalendar == null) {}
      for (Object localObject = null;; localObject = paramXMLGregorianCalendar.toGregorianCalendar()) {
        return (Calendar)localObject;
      }
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      this._delegate.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, null);
    }
    
    public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
      throws JsonMappingException
    {
      JsonSerializer localJsonSerializer = paramSerializerProvider.handlePrimaryContextualization(this._delegate, paramBeanProperty);
      if (localJsonSerializer != this._delegate) {
        this = new XMLGregorianCalendarSerializer(localJsonSerializer);
      }
      return this;
    }
    
    public JsonSerializer<?> getDelegatee()
    {
      return this._delegate;
    }
    
    public boolean isEmpty(SerializerProvider paramSerializerProvider, XMLGregorianCalendar paramXMLGregorianCalendar)
    {
      return this._delegate.isEmpty(paramSerializerProvider, _convert(paramXMLGregorianCalendar));
    }
    
    public void serialize(XMLGregorianCalendar paramXMLGregorianCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      this._delegate.serialize(_convert(paramXMLGregorianCalendar), paramJsonGenerator, paramSerializerProvider);
    }
    
    public void serializeWithType(XMLGregorianCalendar paramXMLGregorianCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
      throws IOException
    {
      this._delegate.serializeWithType(_convert(paramXMLGregorianCalendar), paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ext/CoreXMLSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */