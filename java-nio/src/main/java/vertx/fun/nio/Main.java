package vertx.fun.nio;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteOrder;

public class Main {

  public static void main(String[] args) {
    int i=58700999;
    // 03-7F-B4-C7 大端字节顺序
    printHexBinary(int2Byte(i));
    System.out.println(ByteOrder.nativeOrder());
  }

  private static void printHexBinary(byte[] val){
    String hexStr = DatatypeConverter.printHexBinary(val);
    String regex = "(.{2})";
    hexStr = hexStr.replaceAll(regex,"$1-");
    hexStr = hexStr.substring(0,hexStr.length() - 1);
    System.out.println(hexStr);
    System.out.println();
  }

  // https://blog.csdn.net/alvinhuai/article/details/82790888
  private static byte[] int2Byte(int i) {
    byte[] result = new byte[4];
    result[0] = (byte)((i >> 24) & 0xFF);
    result[1] = (byte)((i >> 16) & 0xFF);
    result[2] = (byte)((i >> 8) & 0xFF);
    result[3] = (byte)(i & 0xFF);
    return result;
  }

  private static int byte2Int(byte[] bytes) {
    int value=0;
    for(int i = 0; i < 4; i++) {
      int shift= (3-i) * 8;
      value +=(bytes[i] & 0xFF) << shift;
    }
    return value;
  }

}
