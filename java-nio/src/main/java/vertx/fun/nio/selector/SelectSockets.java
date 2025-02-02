package vertx.fun.nio.selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author HeCG
 * @description 使用select( )来为多个通道提供服务
 * @date 2023/2/10 23:56
 * @since 1.0
 */
public class SelectSockets {

    public static int PORT_NUMBER = 1234;

    public static void main(String[] args) throws Exception {
        // nc -v 127.0.0.1 1234
        new SelectSockets().go(args);
    }

    public void go(String[] argv) throws Exception {
        int port = PORT_NUMBER;

        if (argv.length > 0) { // Override default listen port
            port = Integer.parseInt(argv[0]);
        }
        System.out.println("Listening on port " + port);

        // Allocate an unbound server socket channel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // Get the associated ServerSocket to bind it with
        ServerSocket serverSocket = serverChannel.socket();

        // Set the port the server channel will listen to
        serverSocket.bind(new InetSocketAddress(port));
        // Set nonblocking mode for the listening socket
        serverChannel.configureBlocking(false);

        // Create a new Selector for use below
        Selector selector = Selector.open();
        // Register the ServerSocketChannel with the Selector
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // This may block for a long time. Upon returning, the
            // selected set contains keys of the ready channels.
            int n = selector.select();
            if (n == 0) {
                System.out.println("Nothing to do ...");
                Thread.sleep(3000);
                continue; // nothing to do
            }

            // Get an iterator over the set of selected keys
            Iterator it = selector.selectedKeys().iterator();
            // Look at each key in the selected set
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                System.out.println("SelectionKey: "+key);

                // Is a new connection coming in?
                if (key.isAcceptable()) {
                    System.out.println("Is accepted...");

                    ServerSocketChannel server =
                            (ServerSocketChannel) key.channel();

                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel,
                            SelectionKey.OP_READ);

                    sayHello(channel);
                }

                // Is there data to read on this channel?
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                // Remove key from selected set; it's been handled
                it.remove();
            }
        }

    }

    /**
     * Register the given channel with the given selector for the given operations of interest
     * @param selector
     * @param channel
     * @param ops
     * @throws Exception
     */
    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception {
        if (channel == null) {
            return; // could happen
        }

        // Set the new channel nonblocking
        channel.configureBlocking(false);
        // Register it with the selector
        channel.register(selector, ops);
    }

     // Use the same byte buffer for all channels. A single thread is
     // servicing all the channels, so no danger of concurrent acccess.
     private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    /**
     * Sample data handler method for a channel with data ready to read.
     * @param key   A SelectionKey object associated with a channel determined by
     *                the selector to be ready for reading. If the channel returns
     *                an EOF condition, it is closed here, which automatically
     *                invalidates the associated key. The selector will then
     *                de-register the channel on the next select call
     * @throws Exception
     */
    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear(); // Empty buffer

        // Loop while data is available; channel is nonblocking
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip(); // Make buffer readable
            // Send the data; don't assume it goes all at once
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            // WARNING: the above loop is evil. Because
            // it's writing back to the same nonblocking
            // channel it read the data from, this code can
            // potentially spin in a busy loop. In real life
            // you'd do something more useful than this.
            buffer.clear(); // Empty buffer
        }
        if (count < 0) {
            // Close channel on EOF, invalidates the key
            socketChannel.close();
        }
    }

    /**
     * Spew a greeting to the incoming client connection.
     * @param channel   The newly connected SocketChannel to say hello to.
     * @throws Exception
     */
    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

}
