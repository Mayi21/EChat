package Ch7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

public class Ch1 {
	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel) {
							channel.pipeline()
									.addLast(new HttpRequestDecoder())   //Http请求解码器
									.addLast(new HttpObjectAggregator(Integer.MAX_VALUE))  //搞一个聚合器，将内容聚合为一个FullHttpRequest，参数是最大内容长度
									.addLast(new ChannelInboundHandlerAdapter(){
										@Override
										public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
											FullHttpRequest request = (FullHttpRequest) msg;
											System.out.println("浏览器请求路径："+request.uri());  //直接获取请求相关信息
											FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
											response.content().writeCharSequence("Hello World!", StandardCharsets.UTF_8);
											ctx.channel().writeAndFlush(response);
											ctx.channel().close();
										}
									})
									.addLast(new HttpResponseEncoder());
						}
					})
					.bind(8080);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
