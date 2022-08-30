package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//创建一个收集http请求的pipeline
		ChannelPipeline pipeline = ch.pipeline();
		//定义一下使用的编码的解码格式
		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		//定义一下使用的编码的编码格式
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast(new MyClientHandler());
	}
}