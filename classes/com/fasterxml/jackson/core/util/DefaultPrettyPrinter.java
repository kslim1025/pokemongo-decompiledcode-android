package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;

public class DefaultPrettyPrinter
  implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable
{
  public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
  private static final long serialVersionUID = 1L;
  protected Indenter _arrayIndenter = FixedSpaceIndenter.instance;
  protected transient int _nesting = 0;
  protected Indenter _objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
  protected final SerializableString _rootSeparator;
  protected boolean _spacesInObjectEntries = true;
  
  public DefaultPrettyPrinter()
  {
    this(DEFAULT_ROOT_VALUE_SEPARATOR);
  }
  
  public DefaultPrettyPrinter(SerializableString paramSerializableString)
  {
    this._rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter)
  {
    this(paramDefaultPrettyPrinter, paramDefaultPrettyPrinter._rootSeparator);
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter, SerializableString paramSerializableString)
  {
    this._arrayIndenter = paramDefaultPrettyPrinter._arrayIndenter;
    this._objectIndenter = paramDefaultPrettyPrinter._objectIndenter;
    this._spacesInObjectEntries = paramDefaultPrettyPrinter._spacesInObjectEntries;
    this._nesting = paramDefaultPrettyPrinter._nesting;
    this._rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(String paramString) {}
  
  protected DefaultPrettyPrinter _withSpaces(boolean paramBoolean)
  {
    if (this._spacesInObjectEntries == paramBoolean) {}
    for (;;)
    {
      return this;
      DefaultPrettyPrinter localDefaultPrettyPrinter = new DefaultPrettyPrinter(this);
      localDefaultPrettyPrinter._spacesInObjectEntries = paramBoolean;
      this = localDefaultPrettyPrinter;
    }
  }
  
  public void beforeArrayValues(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void beforeObjectEntries(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public DefaultPrettyPrinter createInstance()
  {
    return new DefaultPrettyPrinter(this);
  }
  
  public void indentArraysWith(Indenter paramIndenter)
  {
    if (paramIndenter == null) {
      paramIndenter = NopIndenter.instance;
    }
    this._arrayIndenter = paramIndenter;
  }
  
  public void indentObjectsWith(Indenter paramIndenter)
  {
    if (paramIndenter == null) {
      paramIndenter = NopIndenter.instance;
    }
    this._objectIndenter = paramIndenter;
  }
  
  @Deprecated
  public void spacesInObjectEntries(boolean paramBoolean)
  {
    this._spacesInObjectEntries = paramBoolean;
  }
  
  public DefaultPrettyPrinter withArrayIndenter(Indenter paramIndenter)
  {
    if (paramIndenter == null) {
      paramIndenter = NopIndenter.instance;
    }
    if (this._arrayIndenter == paramIndenter) {}
    for (;;)
    {
      return this;
      DefaultPrettyPrinter localDefaultPrettyPrinter = new DefaultPrettyPrinter(this);
      localDefaultPrettyPrinter._arrayIndenter = paramIndenter;
      this = localDefaultPrettyPrinter;
    }
  }
  
  public DefaultPrettyPrinter withObjectIndenter(Indenter paramIndenter)
  {
    if (paramIndenter == null) {
      paramIndenter = NopIndenter.instance;
    }
    if (this._objectIndenter == paramIndenter) {}
    for (;;)
    {
      return this;
      DefaultPrettyPrinter localDefaultPrettyPrinter = new DefaultPrettyPrinter(this);
      localDefaultPrettyPrinter._objectIndenter = paramIndenter;
      this = localDefaultPrettyPrinter;
    }
  }
  
  public DefaultPrettyPrinter withRootSeparator(SerializableString paramSerializableString)
  {
    if ((this._rootSeparator == paramSerializableString) || ((paramSerializableString != null) && (paramSerializableString.equals(this._rootSeparator)))) {}
    for (;;)
    {
      return this;
      this = new DefaultPrettyPrinter(this, paramSerializableString);
    }
  }
  
  public DefaultPrettyPrinter withRootSeparator(String paramString)
  {
    if (paramString == null) {}
    for (Object localObject = null;; localObject = new SerializedString(paramString)) {
      return withRootSeparator((SerializableString)localObject);
    }
  }
  
  public DefaultPrettyPrinter withSpacesInObjectEntries()
  {
    return _withSpaces(true);
  }
  
  public DefaultPrettyPrinter withoutSpacesInObjectEntries()
  {
    return _withSpaces(false);
  }
  
  public void writeArrayValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    paramJsonGenerator.writeRaw(',');
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException
  {
    if (!this._arrayIndenter.isInline()) {
      this._nesting = (-1 + this._nesting);
    }
    if (paramInt > 0) {
      this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw(']');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (!this._objectIndenter.isInline()) {
      this._nesting = (-1 + this._nesting);
    }
    if (paramInt > 0) {
      this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw('}');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(',');
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (this._spacesInObjectEntries) {
      paramJsonGenerator.writeRaw(" : ");
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeRaw(':');
    }
  }
  
  public void writeRootValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (this._rootSeparator != null) {
      paramJsonGenerator.writeRaw(this._rootSeparator);
    }
  }
  
  public void writeStartArray(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (!this._arrayIndenter.isInline()) {
      this._nesting = (1 + this._nesting);
    }
    paramJsonGenerator.writeRaw('[');
  }
  
  public void writeStartObject(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw('{');
    if (!this._objectIndenter.isInline()) {
      this._nesting = (1 + this._nesting);
    }
  }
  
  @Deprecated
  public static class Lf2SpacesIndenter
    extends DefaultIndenter
  {
    @Deprecated
    public static final Lf2SpacesIndenter instance = new Lf2SpacesIndenter();
    
    @Deprecated
    public Lf2SpacesIndenter()
    {
      super(DefaultIndenter.SYS_LF);
    }
    
    @Deprecated
    public Lf2SpacesIndenter(String paramString)
    {
      super(paramString);
    }
    
    public Lf2SpacesIndenter withLinefeed(String paramString)
    {
      if (paramString.equals(getEol())) {}
      for (;;)
      {
        return this;
        this = new Lf2SpacesIndenter(paramString);
      }
    }
  }
  
  public static class FixedSpaceIndenter
    extends DefaultPrettyPrinter.NopIndenter
  {
    public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException
    {
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public static class NopIndenter
    implements DefaultPrettyPrinter.Indenter, Serializable
  {
    public static final NopIndenter instance = new NopIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException
    {}
  }
  
  public static abstract interface Indenter
  {
    public abstract boolean isInline();
    
    public abstract void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/DefaultPrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */