package vertx.fun.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *
 * @author HeCG
 * @description xxx
 * @date 2023/1/13 14:30
 * @since 1.0
 */
public class ScatteringTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);

        SocketChannel socketChannel = serverSocketChannel.accept();

        ByteBuffer header = ByteBuffer.allocateDirect(1);
        ByteBuffer body = ByteBuffer.allocateDirect(8);
        ByteBuffer[] buffers = {header, body};


        while (true) {
            int bytesRead = 0;
            while (bytesRead < 9) {
                long r = socketChannel.read(buffers);
                bytesRead += r;

                Arrays.asList(buffers).stream()
                        .map(buffer -> "position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity())
                        .forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            long bytesWrite = 0;
            while (bytesWrite < 9) {
                long w = socketChannel.write(buffers);
                bytesWrite += w;
            }

            Arrays.asList(buffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

            System.out.println("bytesRead:" + bytesRead + ",bytesWrite:" + bytesWrite + ",messageLength:" + 9);

        }

    }

}
