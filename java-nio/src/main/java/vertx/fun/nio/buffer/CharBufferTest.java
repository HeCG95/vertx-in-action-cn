package vertx.fun.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 14:43
 * @since 1.0
 */
public class CharBufferTest {

  public static void main(String[] args) {

    CharBuffer charBuffer = CharBuffer.allocate(12);

    charBuffer.put("HelloJava~");
    printInfo(charBuffer);

    char[] dst = new char[12];
    charBuffer.flip();// Pos[0] - Limit[10] - Cap[12]
//    charBuffer.rewind();// Pos[0] - Limit[12] - Cap[12]
    printInfo(charBuffer);

    // 批量传输的大小总是固定的。省略长度意味着整个数组会被填满

    charBuffer.get(dst, 2,10);
//    charBuffer.get(dst);// BufferUnderflowException
//    charBuffer.get(dst, 0,12);// BufferUnderflowException

    dst = new char[20];
    System.out.println(">> Big Array");
    charBuffer.flip();
    charBuffer.get(dst, 0, charBuffer.remaining());// Big Array
    System.out.println("'"+new String(dst)+"'");

    charBuffer.flip();
    System.out.println(">> Small Array");
    while (charBuffer.hasRemaining()) {
      dst = new char[3];// Small Array
      int length = Math.min (charBuffer.remaining(  ), dst.length);
      charBuffer.get (dst, 0, length);
      System.out.print("'"+new String(dst)+"'");
    }
    System.out.println();


    printInfo(charBuffer);// Pos[10] - Limit[10] - Cap[12]
    charBuffer.rewind();
//    charBuffer.flip();// Pos[0] - Limit[10] - Cap[12]
    printInfo(charBuffer);

    char[] bigArr = new char[20];
    charBuffer.get(bigArr, 0, charBuffer.remaining());
    System.out.println("'"+new String(bigArr)+"'");

  }

  private static void printInfo(CharBuffer buffer){
    System.out.println(String.format("Pos[%s] - Limit[%s] - Cap[%s] - Remaining[%s]",
      buffer.position(),buffer.limit(),buffer.capacity(),buffer.remaining()));
    System.out.println("Buffer - '"+new String(buffer.array())+"'");
    System.out.println();
  }

}
