package com.howechen.javaMud.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

public class EchoServer {

  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws InterruptedException {
    new EchoServer(8080).start();
  }

  private void start() throws InterruptedException {
    EchoServerHandler echoServerHandler = new EchoServerHandler();
    NioEventLoopGroup group = new NioEventLoopGroup();
    try{
      ServerBootstrap b = new ServerBootstrap();
      b.group(group)
          .channel(NioServerSocketChannel.class)
          .localAddress(new InetSocketAddress(port))
          .childHandler(
              new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  ch.pipeline().addLast(echoServerHandler);
                }
              });

      ChannelFuture chF = b.bind().sync();
      chF.channel().closeFuture().sync();
    } finally{
      group.shutdownGracefully().sync();
    }
  }
}
