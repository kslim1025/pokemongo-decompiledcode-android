package rx.internal.util.unsafe;

public final class SpmcArrayQueue<E>
  extends SpmcArrayQueueL3Pad<E>
{
  public SpmcArrayQueue(int paramInt)
  {
    super(paramInt);
  }
  
  public boolean isEmpty()
  {
    if (lvConsumerIndex() == lvProducerIndex()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new NullPointerException("Null is not a valid element");
    }
    Object[] arrayOfObject = this.buffer;
    long l1 = this.mask;
    long l2 = lvProducerIndex();
    long l3 = calcElementOffset(l2);
    if (lvElement(arrayOfObject, l3) != null) {
      if (l2 - lvConsumerIndex() <= l1) {}
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      while (lvElement(arrayOfObject, l3) != null) {}
      spElement(arrayOfObject, l3, paramE);
      soTail(1L + l2);
    }
  }
  
  public E peek()
  {
    long l1 = lvProducerIndexCache();
    long l2 = lvConsumerIndex();
    long l3;
    Object localObject;
    if (l2 >= l1)
    {
      l3 = lvProducerIndex();
      if (l2 >= l3) {
        localObject = null;
      }
    }
    for (;;)
    {
      return (E)localObject;
      svProducerIndexCache(l3);
      localObject = lvElement(calcElementOffset(l2));
      if (localObject == null) {
        break;
      }
    }
  }
  
  public E poll()
  {
    long l1 = lvProducerIndexCache();
    long l2 = lvConsumerIndex();
    long l4;
    Object localObject;
    if (l2 >= l1)
    {
      l4 = lvProducerIndex();
      if (l2 >= l4) {
        localObject = null;
      }
    }
    for (;;)
    {
      return (E)localObject;
      svProducerIndexCache(l4);
      if (!casHead(l2, 1L + l2)) {
        break;
      }
      long l3 = calcElementOffset(l2);
      Object[] arrayOfObject = this.buffer;
      localObject = lpElement(arrayOfObject, l3);
      soElement(arrayOfObject, l3, null);
    }
  }
  
  public int size()
  {
    long l1 = lvConsumerIndex();
    long l2;
    long l3;
    do
    {
      l2 = l1;
      l3 = lvProducerIndex();
      l1 = lvConsumerIndex();
    } while (l2 != l1);
    return (int)(l3 - l1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpmcArrayQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */