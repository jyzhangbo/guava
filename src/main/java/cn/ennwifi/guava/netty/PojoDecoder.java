package cn.ennwifi.guava.netty;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import cn.ennwifi.guava.netty.pojo.StationData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhangbo
 *
 */
public class PojoDecoder extends MessageToByteEncoder<StationData> {

  @Override
  protected void encode(ChannelHandlerContext ctx, StationData msg, ByteBuf out) throws Exception {
    out.writeBytes(Json.toJson(msg, JsonFormat.tidy()).getBytes());
  }


}
