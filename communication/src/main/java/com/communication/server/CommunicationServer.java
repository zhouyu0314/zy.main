package com.communication.server;

import com.communication.client.CommunicationClient;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class CommunicationServer {


    private static class SingletionWebServer {
        static final CommunicationServer instnal = new CommunicationServer();
    }

    public static CommunicationServer getInstnal() {
        return SingletionWebServer.instnal;
    }
    NioEventLoopGroup bossGroup;
    NioEventLoopGroup workerGroup;
    ServerBootstrap serverBootstrap;
    ChannelFuture cf;
    private CommunicationServer(){
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new ChunkedWriteHandler())
                                .addLast(new HttpObjectAggregator(8192))
                                .addLast(new WebSocketServerProtocolHandler("/getFileSize"))
                                .addLast(new CommunicationClient());
                    }
                });
    }

    public void run() {
        try {
            cf = serverBootstrap.bind(34123).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }


}
