package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

public class SimpleType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected final String[] _typeNames;
  protected final JavaType[] _typeParameters;
  protected final Class<?> _typeParametersFor;
  
  protected SimpleType(Class<?> paramClass)
  {
    this(paramClass, null, null, null, null, false, null);
  }
  
  protected SimpleType(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramInt, paramObject1, paramObject2, paramBoolean);
    this._typeNames = null;
    this._typeParameters = null;
    this._typeParametersFor = paramClass;
  }
  
  @Deprecated
  protected SimpleType(Class<?> paramClass, String[] paramArrayOfString, JavaType[] paramArrayOfJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    this(paramClass, paramArrayOfString, paramArrayOfJavaType, paramObject1, paramObject2, paramBoolean, null);
  }
  
  protected SimpleType(Class<?> paramClass1, String[] paramArrayOfString, JavaType[] paramArrayOfJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean, Class<?> paramClass2)
  {
    super(paramClass1, 0, paramObject1, paramObject2, paramBoolean);
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      this._typeNames = null;
    }
    for (this._typeParameters = null;; this._typeParameters = paramArrayOfJavaType)
    {
      this._typeParametersFor = paramClass2;
      return;
      this._typeNames = paramArrayOfString;
    }
  }
  
  public static SimpleType construct(Class<?> paramClass)
  {
    if (Map.class.isAssignableFrom(paramClass)) {
      throw new IllegalArgumentException("Can not construct SimpleType for a Map (class: " + paramClass.getName() + ")");
    }
    if (Collection.class.isAssignableFrom(paramClass)) {
      throw new IllegalArgumentException("Can not construct SimpleType for a Collection (class: " + paramClass.getName() + ")");
    }
    if (paramClass.isArray()) {
      throw new IllegalArgumentException("Can not construct SimpleType for an array (class: " + paramClass.getName() + ")");
    }
    return new SimpleType(paramClass);
  }
  
  public static SimpleType constructUnsafe(Class<?> paramClass)
  {
    return new SimpleType(paramClass, null, null, null, null, false, null);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new SimpleType(paramClass, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic, this._typeParametersFor);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if ((this._typeParameters != null) && (this._typeParameters.length > 0))
    {
      localStringBuilder.append('<');
      int i = 1;
      JavaType[] arrayOfJavaType = this._typeParameters;
      int j = arrayOfJavaType.length;
      int k = 0;
      if (k < j)
      {
        JavaType localJavaType = arrayOfJavaType[k];
        if (i != 0) {
          i = 0;
        }
        for (;;)
        {
          localStringBuilder.append(localJavaType.toCanonical());
          k++;
          break;
          localStringBuilder.append(',');
        }
      }
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    if ((paramInt < 0) || (this._typeParameters == null) || (paramInt >= this._typeParameters.length)) {}
    for (JavaType localJavaType = null;; localJavaType = this._typeParameters[paramInt]) {
      return localJavaType;
    }
  }
  
  public int containedTypeCount()
  {
    if (this._typeParameters == null) {}
    for (int i = 0;; i = this._typeParameters.length) {
      return i;
    }
  }
  
  public String containedTypeName(int paramInt)
  {
    if ((paramInt < 0) || (this._typeNames == null) || (paramInt >= this._typeNames.length)) {}
    for (String str = null;; str = this._typeNames[paramInt]) {
      return str;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if ((paramObject != null) && (paramObject.getClass() == getClass()))
      {
        SimpleType localSimpleType = (SimpleType)paramObject;
        if (localSimpleType._class == this._class)
        {
          JavaType[] arrayOfJavaType1 = this._typeParameters;
          JavaType[] arrayOfJavaType2 = localSimpleType._typeParameters;
          if (arrayOfJavaType1 == null)
          {
            if ((arrayOfJavaType2 == null) || (arrayOfJavaType2.length == 0)) {
              bool = true;
            }
          }
          else if ((arrayOfJavaType2 != null) && (arrayOfJavaType1.length == arrayOfJavaType2.length))
          {
            int i = 0;
            int j = arrayOfJavaType1.length;
            for (;;)
            {
              if (i >= j) {
                break label126;
              }
              if (!arrayOfJavaType1[i].equals(arrayOfJavaType2[i])) {
                break;
              }
              i++;
            }
            label126:
            bool = true;
          }
        }
      }
    }
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    if (this._typeParameters != null)
    {
      paramStringBuilder.append('<');
      JavaType[] arrayOfJavaType = this._typeParameters;
      int i = arrayOfJavaType.length;
      for (int j = 0; j < i; j++) {
        paramStringBuilder = arrayOfJavaType[j].getGenericSignature(paramStringBuilder);
      }
      paramStringBuilder.append('>');
    }
    paramStringBuilder.append(';');
    return paramStringBuilder;
  }
  
  public Class<?> getParameterSource()
  {
    return this._typeParametersFor;
  }
  
  public boolean isContainerType()
  {
    return false;
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(40);
    localStringBuilder.append("[simple type, class ").append(buildCanonicalName()).append(']');
    return localStringBuilder.toString();
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
  }
  
  public JavaType withContentTypeHandler(Object paramObject)
  {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
  }
  
  public SimpleType withContentValueHandler(Object paramObject)
  {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
  }
  
  public SimpleType withStaticTyping()
  {
    if (this._asStatic) {}
    for (;;)
    {
      return this;
      this = new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, true, this._typeParametersFor);
    }
  }
  
  public SimpleType withTypeHandler(Object paramObject)
  {
    return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, paramObject, this._asStatic, this._typeParametersFor);
  }
  
  public SimpleType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {}
    for (;;)
    {
      return this;
      this = new SimpleType(this._class, this._typeNames, this._typeParameters, paramObject, this._typeHandler, this._asStatic, this._typeParametersFor);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/SimpleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */