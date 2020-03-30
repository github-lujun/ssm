package com.jun.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            new Bootstrap().group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ChannelHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                byte[] req = "hello".getBytes();
                                ByteBuf firstMessage = Unpooled.buffer(req.length);
                                firstMessage.writeBytes(req);
                                ctx.writeAndFlush(firstMessage);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                byte[] req = new byte[buf.readableBytes()];
                                buf.readBytes(req);
                                System.out.println(new String(req));
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                ctx.close();
                            }
                        });
                    }
                })
                .connect("127.0.0.1", 9050)
                .sync()
                .channel()
                .closeFuture()
                .sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
