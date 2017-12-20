package cn.ennwifi.guava.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhangbo
 *
 */
public class TimeClient {

  public static void main(String[] args) throws Exception {
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
          .handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new DiscardServerHandler());

            }
          });

      ChannelFuture f = bootstrap.connect("localhost", 8080).sync();
      f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

}
