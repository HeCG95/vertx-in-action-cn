import java.nio.ByteBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 17:30
 * @since 1.0
 */
public class HelloBuffer {

  static {
    System.loadLibrary("my_jni");
  }

  private ByteBuffer image_info_bb;
  public native static void initc(ByteBuffer bb);

  public HelloBuffer() {
    image_info_bb = ByteBuffer.allocateDirect( 5 * 4 );
    initc( image_info_bb );
  }

  public ByteBuffer getBB() {
    return image_info_bb;
  }

  public static void main(String[] args) {

    HelloBuffer fii = new HelloBuffer();
    java.nio.ByteBuffer bb = fii.getBB();
    System.out.println("1:" + bb.getInt(0));
    System.out.println("2:" + bb.getInt(4));
    System.out.println("3:" + bb.getInt(8));
    System.out.println("4:" + bb.getInt(12));
    System.out.println("5:" + bb.getInt(16));

  }

}
