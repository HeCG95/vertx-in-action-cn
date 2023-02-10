package vertx.fun.nio.channel.http;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/9 22:06
 * @since 1.0
 */
public class ChannelTransfer {

    public static void main(String[] argv) throws Exception {

        if (argv.length == 0) {
            System.err.println ("Usage: filename ...");
            return;
        }
        catFiles (Channels.newChannel (System.out), argv);

    }

    private static void catFiles(WritableByteChannel target, String[] files) throws Exception {
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream (files [i]);
            FileChannel channel = fis.getChannel( );
            channel.transferTo (0, channel.size( ), target);
            channel.close( );
            fis.close( );
        }
    }

}
