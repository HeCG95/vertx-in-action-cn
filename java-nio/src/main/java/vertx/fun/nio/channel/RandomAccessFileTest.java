package vertx.fun.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/13 16:36
 * @since 1.0
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile ("blahblah.txt", "r");
        // Set the file position
        randomAccessFile.seek (1000);
        // Create a channel from the file
        FileChannel fileChannel = randomAccessFile.getChannel( );
        // This will print "1000"
        System.out.println ("file pos: " + fileChannel.position( ));
        // Change the position using the RandomAccessFile object
        randomAccessFile.seek (500);
        // This will print "500"
        System.out.println ("file pos: " + fileChannel.position( ));
        // Change the position using the FileChannel object
        fileChannel.position (200);
        // This will print "200"
        System.out.println ("file pos: " + randomAccessFile.getFilePointer( ));

    }

}
