package vertx.fun.nio.buffer;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 11:20
 * @since 1.0
 */
public class ByteBufferTest2 {

  public static void main(String[] args) {

    ByteBuffer buffer = ByteBuffer.allocate(10);

    buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');

    buffer.put(0,(byte) 'M').put((byte)'w');
    printInfo(buffer);// 6 10 10

    // buffer.limit(buffer.position()).position(0);
    buffer.flip();// 翻转 'Mellow'
    printInfo(buffer);// 0 6 10

    buffer.position(2).mark().position(4);
    printInfo(buffer);// 4 6 10 'ow'

    buffer.reset();
    printInfo(buffer);// 2 6 10 'llow'

  }

  private static void printInfo(ByteBuffer buffer){
    // mark, int pos, int lim, int cap
    System.out.println(">>>>>>>>>> pos "+buffer.position());
    System.out.println(">>>>>>>>>> limit "+buffer.limit());
    System.out.println(">>>>>>>>>> capacity "+buffer.capacity());
    System.out.println("'"+new String(buffer.array())+"'");
    printHexBinary(buffer.array());
  }

  private static void printHexBinary(byte[] val){
    String hexStr = DatatypeConverter.printHexBinary(val);
    String regex = "(.{2})";
    hexStr = hexStr.replaceAll(regex,"$1-");
    hexStr = hexStr.substring(0,hexStr.length() - 1);
    System.out.println(hexStr);
    System.out.println();
  }

}
