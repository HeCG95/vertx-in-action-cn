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

    charBuffer.get(dst, 2,10);
    System.out.println("'"+new String(dst)+"'");

    printInfo(charBuffer);// Pos[10] - Limit[10] - Cap[12]
    charBuffer.rewind();
//    charBuffer.flip();// Pos[0] - Limit[10] - Cap[12]
    printInfo(charBuffer);

  }

  private static void printInfo(CharBuffer buffer){
    System.out.println(String.format("Pos[%s] - Limit[%s] - Cap[%s]",buffer.position(),buffer.limit(),buffer.capacity()));
    System.out.println("Buffer - '"+new String(buffer.array())+"'");
    System.out.println();
  }

}
