import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unused")
public class Program {
    public static void main(String[] args) {
        //server
        new Thread(new Thread(){
            @Override
            public void run() {
                try {
                    //1.
                    Selector selector = Selector.open();
                    //2.
                    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.configureBlocking(false);
                    serverSocketChannel.socket().bind(new InetSocketAddress(9050), 1024);
                    //3.
                    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                    //4.
                    boolean stop = false;
                    while (!stop) {
                        selector.select(1000);
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> it = selectionKeys.iterator();
                        SelectionKey key = null;
                        while (it.hasNext()) {
                            key = it.next();
                            it.remove();
                            if (key.isValid()) {
                                if (key.isAcceptable()) {
                                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                    SocketChannel sc = ssc.accept();
                                    sc.configureBlocking(false);
                                    sc.register(selector, SelectionKey.OP_READ);
                                }

                                if (key.isReadable()) {
                                    SocketChannel sc = (SocketChannel) key.channel();
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                    int readBytes = sc.read(readBuffer);
                                    if (readBytes > 0) {
                                        readBuffer.flip();
                                        byte[] bytes = new byte[readBuffer.remaining()];
                                        readBuffer.get(bytes);
                                        //打印readBuffer里的内容
                                        System.out.println(new String(bytes));

                                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                        writeBuffer.put("hi".getBytes());
                                        writeBuffer.flip();
                                        sc.write(writeBuffer);
                                    } else if (readBytes < 0) {
                                        //对端链路关闭
                                        key.cancel();
                                        sc.close();
                                    } else {
                                        ;//读到0字节，忽略
                                    }
                                }
                            }

                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();

        //client
        new Thread(new Thread(){
            @Override
            public void run() {
                try{
                    Selector selector = Selector.open();
                    SocketChannel socketChannel = SocketChannel.open();
                    socketChannel.bind(new InetSocketAddress(9051));
                    socketChannel.configureBlocking(false);
                    if(socketChannel.connect(new InetSocketAddress(9050))){
                        //write
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        byte[] req = "hello".getBytes();
                        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                        writeBuffer.put(req);
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                    }else {
                        socketChannel.register(selector,SelectionKey.OP_CONNECT);
                    }
                    boolean stop =false;
                    while (!stop) {
                        selector.select(1000);
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> it = selectionKeys.iterator();
                        SelectionKey key = null;
                        while (it.hasNext()) {
                            key = it.next();
                            it.remove();
                            //read
                            if (key.isValid()) {
                                //判断连接是否成功
                                SocketChannel sc = (SocketChannel) key.channel();
                                if (key.isConnectable()) {
                                    if (sc.finishConnect()) {
                                        sc.register(selector, SelectionKey.OP_READ);
                                        byte[] req = "hello".getBytes();
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                                        writeBuffer.put(req);
                                        writeBuffer.flip();
                                        socketChannel.write(writeBuffer);
                                    } else {
                                        System.exit(1);//连接失败，进程退出
                                    }
                                }
                                if (key.isReadable()) {
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                    int readBytes = sc.read(readBuffer);
                                    if (readBytes > 0) {
                                        readBuffer.flip();
                                        byte[] bytes = new byte[readBuffer.remaining()];
                                        readBuffer.get(bytes);
                                        //打印readBuffer里的内容
                                        System.out.println(new String(bytes));
                                        stop = true;
                                    } else if (readBytes < 0) {
                                        //对端链路关闭
                                        key.cancel();
                                        sc.close();
                                    } else {
                                        ;//读到0字节，忽略
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
