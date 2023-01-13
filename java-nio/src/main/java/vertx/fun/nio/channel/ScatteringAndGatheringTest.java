package vertx.fun.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author HeCG
 * @description 在多个缓冲区上实现一个简单的I/O 操作
 * 对于read 操作而言，从通道读取的数据会按顺序被散布（称为scatter）到多个缓冲区，将每个缓冲区填满直至通道中的数
 * 据或者缓冲区的最大空间被消耗完
 *
 * @date 2023/1/13 14:19
 * @since 1.0
 */
public class ScatteringAndGatheringTest {

    public static void test () throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);

        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        while (true) {
            int bytesRead = 0;

            while (bytesRead < messageLength) {
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;

                System.out.println("bytesRead:" + bytesRead);
                System.out.println("after read,show every buffer:");

                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "position:" + buffer.position() + ",limit:" + buffer.limit() + ",capacity:" + buffer.capacity())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            long bytesWrite = 0;
            while (bytesWrite < messageLength) {
                long w = socketChannel.write(byteBuffers);
                bytesWrite += w;
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

            System.out.println("bytesRead:" + bytesRead + ",bytesWrite:" + bytesWrite + ",messageLength:" + messageLength);

        }
    }

    public static void main(String[] args) throws IOException {
        ScatteringAndGatheringTest.test();
        // telnet localhost 8899
        // abcd12345
    }

}
