package vertx.fun.nio.bytebuffer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 16:29
 * @since 1.0
 */
public class DirectByteBufferTest {

  public static void main(String[] args) {

    ByteBuffer buffer = ByteBuffer.allocateDirect(16);
    try {
      Class<?> aClass = Class.forName("java.nio.DirectByteBuffer");
      Method addressMethod = aClass.getDeclaredMethod("address");
      addressMethod.setAccessible(true);
      long address = (long)addressMethod.invoke(buffer);
      System.out.println(String.format("addr - [%s]",address));
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

  }

}
