package vertx.fun.nio.buffer;

import java.nio.CharBuffer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 12:35
 * @since 1.0
 */
public class BufferFillDrain {

  public static void main(String[] args) {

    CharBuffer buffer = CharBuffer.allocate (100);

    while (fillBuffer (buffer)) {
      buffer.flip();
      drainBuffer(buffer);
      buffer.clear();
    }

  }

  private static void drainBuffer (CharBuffer buffer){
    while (buffer.hasRemaining()) {
      System.out.print (buffer.get());
    }
    System.out.println ();
  }

  private static boolean fillBuffer (CharBuffer buffer){
    if (index >= strings.length) {
      return (false);
    }

    String string = strings [index++];

    for (int i = 0; i < string.length(  ); i++) {
      buffer.put (string.charAt (i));
    }
    return (true);

    /*byte[] myByteArray = new byte[buffer.limit()];
    for (int i = 0; buffer.hasRemaining(); i++) {
      myByteArray [i] = buffer.get();
    }
    System.out.println("'"+new String(myByteArray)+"'");

    int remaining = buffer.remaining();
    for (int i = 0; i<remaining; i++) {
      myByteArray [i] = buffer.get();
    }
    System.out.println("'"+new String(myByteArray)+"'");*/

  }

  private static int index = 0;

  private static String [] strings = {
    "A random string value",
    "The product of an infinite number of monkeys",
    "Hey hey we're the Monkees",
    "Opening act for the Monkees: Jimi Hendrix",
    "'Scuse me while I kiss this fly",  // Sorry Jimi ;-)
    "Help Me!  Help Me!",
  };

}
