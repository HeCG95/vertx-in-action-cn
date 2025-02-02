package vertx.fun.nio.bytebuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/13 9:37
 * @since 1.0
 */
public class ByteBufferCharView {

  public static void main(String[] args) {

    ByteBuffer byteBuffer =
      ByteBuffer.allocate (7).order (ByteOrder.BIG_ENDIAN);
//      ByteBuffer.allocate (7).order (ByteOrder.LITTLE_ENDIAN);
    CharBuffer charBuffer = byteBuffer.asCharBuffer(  );

    // Load the ByteBuffer with some bytes
    byteBuffer.put (0, (byte)0);
    byteBuffer.put (1, (byte)'H');
    byteBuffer.put (2, (byte)0);
    byteBuffer.put (3, (byte)'i');
    byteBuffer.put (4, (byte)0);
    byteBuffer.put (5, (byte)'!');
    byteBuffer.put (6, (byte)0);

    println (byteBuffer);
    println (charBuffer);

  }

  private static void println (Buffer buffer)
  {
    System.out.println ("pos=" + buffer.position(  )
      + ", limit=" + buffer.limit(  )
      + ", capacity=" + buffer.capacity(  )
      + ": '" + buffer + "'");
  }

}
