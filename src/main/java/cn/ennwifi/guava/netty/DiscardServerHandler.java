package cn.ennwifi.guava.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author zhangbo
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

  private ByteBuf buf;

  public void handlerAdded(ChannelHandlerContext ctx) {
    buf = ctx.alloc().buffer(4); // (1)
  }

  public void handlerRemoved(ChannelHandlerContext ctx) {
    buf.release(); // (1)
    buf = null;
  }

  /*
   * public void channelActive(ChannelHandlerContext ctx) throws Exception { final ByteBuf time =
   * ctx.alloc().buffer(4); time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
   * final ChannelFuture f = ctx.writeAndFlush(time); f.addListener(new ChannelFutureListener() {
   * 
   * @Override public void operationComplete(ChannelFuture future) throws Exception { assert f ==
   * future; ctx.close();
   * 
   * } }); }
   */

  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf m = (ByteBuf) msg;
    buf.writeBytes(m);
    m.release();

    if (buf.readableBytes() >= 4) {
      long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
      System.out.println(new Date(currentTimeMillis));
      // ctx.close();
    }
  }

  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

}
