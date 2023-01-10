package vertx.fun.nio.buffer;

import java.nio.CharBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 15:22
 * @since 1.0
 */
public class CharBufferTest2 {

  public static void main(String[] args) {

    char[] mArr = new char[10];
    CharBuffer buffer = CharBuffer.wrap(mArr,5,5);
    printInfo(buffer);

    buffer.put("Java~");
    printInfo(buffer);
    System.out.println("array -> '"+new String(mArr, 5, 5)+"'");

    mArr[5] = 'D';
    System.out.println("buffer -> '"+new String(buffer.array())+"'");

  }

  private static void printInfo(CharBuffer buffer){
    System.out.println(String.format("Pos[%s] - Limit[%s] - Cap[%s] - Remaining[%s]",
      buffer.position(),buffer.limit(),buffer.capacity(),buffer.remaining()));
    System.out.println("Buffer - '"+new String(buffer.array())+"'");
    System.out.println();
  }

}
