package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringGeneratorDelegate
  extends JsonGeneratorDelegate
{
  protected boolean _allowMultipleMatches;
  protected TokenFilterContext _filterContext;
  @Deprecated
  protected boolean _includeImmediateParent = false;
  protected boolean _includePath;
  protected TokenFilter _itemFilter;
  protected int _matchCount;
  protected TokenFilter rootFilter;
  
  public FilteringGeneratorDelegate(JsonGenerator paramJsonGenerator, TokenFilter paramTokenFilter, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramJsonGenerator, false);
    this.rootFilter = paramTokenFilter;
    this._itemFilter = paramTokenFilter;
    this._filterContext = TokenFilterContext.createRootContext(paramTokenFilter);
    this._includePath = paramBoolean1;
    this._allowMultipleMatches = paramBoolean2;
  }
  
  protected boolean _checkBinaryWrite()
    throws IOException
  {
    boolean bool = false;
    if (this._itemFilter == null) {}
    for (;;)
    {
      return bool;
      if (this._itemFilter == TokenFilter.INCLUDE_ALL)
      {
        bool = true;
      }
      else if (this._itemFilter.includeBinary())
      {
        _checkParentPath();
        bool = true;
      }
    }
  }
  
  protected void _checkParentPath()
    throws IOException
  {
    this._matchCount = (1 + this._matchCount);
    if (this._includePath) {
      this._filterContext.writePath(this.delegate);
    }
    if (!this._allowMultipleMatches) {
      this._filterContext.skipParentChecks();
    }
  }
  
  protected void _checkPropertyParentPath()
    throws IOException
  {
    this._matchCount = (1 + this._matchCount);
    if (this._includePath) {
      this._filterContext.writePath(this.delegate);
    }
    for (;;)
    {
      if (!this._allowMultipleMatches) {
        this._filterContext.skipParentChecks();
      }
      return;
      if (this._includeImmediateParent) {
        this._filterContext.writeImmediatePath(this.delegate);
      }
    }
  }
  
  protected boolean _checkRawValueWrite()
    throws IOException
  {
    boolean bool = false;
    if (this._itemFilter == null) {}
    for (;;)
    {
      return bool;
      if (this._itemFilter == TokenFilter.INCLUDE_ALL)
      {
        bool = true;
      }
      else if (this._itemFilter.includeRawValue())
      {
        _checkParentPath();
        bool = true;
      }
    }
  }
  
  public TokenFilter getFilter()
  {
    return this.rootFilter;
  }
  
  public JsonStreamContext getFilterContext()
  {
    return this._filterContext;
  }
  
  public int getMatchCount()
  {
    return this._matchCount;
  }
  
  public JsonStreamContext getOutputContext()
  {
    return this._filterContext;
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException
  {
    if (_checkBinaryWrite()) {}
    for (int i = this.delegate.writeBinary(paramBase64Variant, paramInputStream, paramInt);; i = -1) {
      return i;
    }
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkBinaryWrite()) {
      this.delegate.writeBinary(paramBase64Variant, paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeBoolean(paramBoolean)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeBoolean(paramBoolean);
      }
    }
  }
  
  public void writeEndArray()
    throws IOException
  {
    this._filterContext = this._filterContext.closeArray(this.delegate);
    if (this._filterContext != null) {
      this._itemFilter = this._filterContext.getFilter();
    }
  }
  
  public void writeEndObject()
    throws IOException
  {
    this._filterContext = this._filterContext.closeObject(this.delegate);
    if (this._filterContext != null) {
      this._itemFilter = this._filterContext.getFilter();
    }
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    TokenFilter localTokenFilter1 = this._filterContext.setFieldName(paramSerializableString.getValue());
    if (localTokenFilter1 == null) {
      this._itemFilter = null;
    }
    for (;;)
    {
      return;
      if (localTokenFilter1 == TokenFilter.INCLUDE_ALL)
      {
        this._itemFilter = localTokenFilter1;
        this.delegate.writeFieldName(paramSerializableString);
      }
      else
      {
        TokenFilter localTokenFilter2 = localTokenFilter1.includeProperty(paramSerializableString.getValue());
        this._itemFilter = localTokenFilter2;
        if (localTokenFilter2 == TokenFilter.INCLUDE_ALL) {
          _checkPropertyParentPath();
        }
      }
    }
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    TokenFilter localTokenFilter1 = this._filterContext.setFieldName(paramString);
    if (localTokenFilter1 == null) {
      this._itemFilter = null;
    }
    for (;;)
    {
      return;
      if (localTokenFilter1 == TokenFilter.INCLUDE_ALL)
      {
        this._itemFilter = localTokenFilter1;
        this.delegate.writeFieldName(paramString);
      }
      else
      {
        TokenFilter localTokenFilter2 = localTokenFilter1.includeProperty(paramString);
        this._itemFilter = localTokenFilter2;
        if (localTokenFilter2 == TokenFilter.INCLUDE_ALL) {
          _checkPropertyParentPath();
        }
      }
    }
  }
  
  public void writeNull()
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNull()))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNull();
      }
    }
  }
  
  public void writeNumber(double paramDouble)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramDouble)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramDouble);
      }
    }
  }
  
  public void writeNumber(float paramFloat)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramFloat)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramFloat);
      }
    }
  }
  
  public void writeNumber(int paramInt)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramInt)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramInt);
      }
    }
  }
  
  public void writeNumber(long paramLong)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramLong)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramLong);
      }
    }
  }
  
  public void writeNumber(String paramString)
    throws IOException, UnsupportedOperationException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeRawValue()))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramString);
      }
    }
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramBigDecimal)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramBigDecimal);
      }
    }
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramBigInteger)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramBigInteger);
      }
    }
  }
  
  public void writeNumber(short paramShort)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeNumber(paramShort)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeNumber(paramShort);
      }
    }
  }
  
  public void writeObjectId(Object paramObject)
    throws IOException
  {
    if (this._itemFilter != null) {
      this.delegate.writeObjectId(paramObject);
    }
  }
  
  public void writeObjectRef(Object paramObject)
    throws IOException
  {
    if (this._itemFilter != null) {
      this.delegate.writeObjectRef(paramObject);
    }
  }
  
  public void writeOmittedField(String paramString)
    throws IOException
  {
    if (this._itemFilter != null) {
      this.delegate.writeOmittedField(paramString);
    }
  }
  
  public void writeRaw(char paramChar)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramChar);
    }
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramSerializableString);
    }
  }
  
  public void writeRaw(String paramString)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramString);
    }
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramString);
    }
  }
  
  public void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRawUTF8String(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  public void writeRawValue(String paramString)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramString);
    }
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramString, paramInt1, paramInt2);
    }
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRaw(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  public void writeStartArray()
    throws IOException
  {
    if (this._itemFilter == null) {
      this._filterContext = this._filterContext.createChildArrayContext(null, false);
    }
    for (;;)
    {
      return;
      if (this._itemFilter == TokenFilter.INCLUDE_ALL)
      {
        this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
        this.delegate.writeStartArray();
      }
      else
      {
        this._itemFilter = this._filterContext.checkValue(this._itemFilter);
        if (this._itemFilter == null)
        {
          this._filterContext = this._filterContext.createChildArrayContext(null, false);
        }
        else
        {
          if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            this._itemFilter = this._itemFilter.filterStartArray();
          }
          if (this._itemFilter == TokenFilter.INCLUDE_ALL)
          {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray();
          }
          else
          {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, false);
          }
        }
      }
    }
  }
  
  public void writeStartArray(int paramInt)
    throws IOException
  {
    if (this._itemFilter == null) {
      this._filterContext = this._filterContext.createChildArrayContext(null, false);
    }
    for (;;)
    {
      return;
      if (this._itemFilter == TokenFilter.INCLUDE_ALL)
      {
        this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
        this.delegate.writeStartArray(paramInt);
      }
      else
      {
        this._itemFilter = this._filterContext.checkValue(this._itemFilter);
        if (this._itemFilter == null)
        {
          this._filterContext = this._filterContext.createChildArrayContext(null, false);
        }
        else
        {
          if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            this._itemFilter = this._itemFilter.filterStartArray();
          }
          if (this._itemFilter == TokenFilter.INCLUDE_ALL)
          {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(paramInt);
          }
          else
          {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, false);
          }
        }
      }
    }
  }
  
  public void writeStartObject()
    throws IOException
  {
    if (this._itemFilter == null) {
      this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, false);
    }
    for (;;)
    {
      return;
      if (this._itemFilter == TokenFilter.INCLUDE_ALL)
      {
        this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, true);
        this.delegate.writeStartObject();
      }
      else
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if (localTokenFilter != null)
        {
          if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
            localTokenFilter = localTokenFilter.filterStartObject();
          }
          if (localTokenFilter == TokenFilter.INCLUDE_ALL)
          {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(localTokenFilter, true);
            this.delegate.writeStartObject();
          }
          else
          {
            this._filterContext = this._filterContext.createChildObjectContext(localTokenFilter, false);
          }
        }
      }
    }
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeString(paramSerializableString.getValue())))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeString(paramSerializableString);
      }
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeString(paramString)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeString(paramString);
      }
    }
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._itemFilter == null) {}
    for (;;)
    {
      return;
      if (this._itemFilter != TokenFilter.INCLUDE_ALL)
      {
        String str = new String(paramArrayOfChar, paramInt1, paramInt2);
        TokenFilter localTokenFilter = this._filterContext.checkValue(this._itemFilter);
        if ((localTokenFilter != null) && ((localTokenFilter == TokenFilter.INCLUDE_ALL) || (localTokenFilter.includeString(str)))) {
          _checkParentPath();
        }
      }
      else
      {
        this.delegate.writeString(paramArrayOfChar, paramInt1, paramInt2);
      }
    }
  }
  
  public void writeTypeId(Object paramObject)
    throws IOException
  {
    if (this._itemFilter != null) {
      this.delegate.writeTypeId(paramObject);
    }
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_checkRawValueWrite()) {
      this.delegate.writeRawUTF8String(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/filter/FilteringGeneratorDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */