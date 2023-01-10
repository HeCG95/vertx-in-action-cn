package vertx.fun.nio.buffer;

import java.nio.CharBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 15:22
 * @since 1.0
 */
public class CharBufferTest3 {

  public static void main(String[] args) {

    char[] mArr = new char[10];
    CharBuffer buffer = CharBuffer.wrap(mArr,5,5);

    System.out.println("buffer.order() "+buffer.order());

    printInfo(buffer);

    buffer.rewind();
    buffer.put("Java~");
    printInfo(buffer);

    CharBuffer bufferC = buffer.duplicate();
    bufferC.put(0, 'D');

    bufferC.flip();

    printInfo(bufferC);
    printInfo(buffer);

  }

  private static void printInfo(CharBuffer buffer){
    System.out.println(String.format("Pos[%s] - Limit[%s] - Cap[%s] - Remaining[%s]",
      buffer.position(),buffer.limit(),buffer.capacity(),buffer.remaining()));
    System.out.println("Buffer - '"+new String(buffer.array())+"'");
    System.out.println();
  }

}
