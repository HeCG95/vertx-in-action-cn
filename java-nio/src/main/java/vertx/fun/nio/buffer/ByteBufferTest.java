package vertx.fun.nio.buffer;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 11:20
 * @since 1.0
 */
public class ByteBufferTest {

  public static void main(String[] args) {

    ByteBuffer buffer = ByteBuffer.allocate(10);
//    ByteBuffer dByteBuffer = ByteBuffer.allocateDirect(10);

    printInfo(buffer);

    buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
    printInfo(buffer);

    buffer.put(0,(byte) 'M').put((byte)'w');
    printInfo(buffer);

    buffer.rewind();
    printInfo(buffer);

//    buffer.limit(buffer.position()).position(0);
//    buffer.flip();// 翻转
//    printInfo(buffer);

    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");

    buffer.position(2);
    buffer.limit(6);
    printInfo(buffer);

    // 原文链接：https://blog.csdn.net/qq_36951116/article/details/87209456
    // 把buffer中内部数组剩余未读取的数据复制到该数组从索引为0开始，
    // 然后position设置为复制剩余数据后的最后一位元素的索引+1，limit设置为capcity，
    // 此时在0~position之间是未读数据，而position~limit之间是buffer的剩余空间，可以put数据
    // 当buffer被读取过，但想继续复用buffer时，可以执行compact把剩余未读取数据往缓冲数据前面移动，
    // compact移动完后，可以再次使用put往该buffer里put数据，此时数据会被写到剩余数据之后
    buffer.compact();
    buffer.put((byte)'0').put((byte)'1');
    printInfo(buffer);

//    buffer.reset();

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
