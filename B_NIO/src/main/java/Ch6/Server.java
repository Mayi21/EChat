package Ch6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

public class Server {
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
									.addLast(new ChannelOutboundHandlerAdapter(){
										@Override
										public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {  //当执行write操作时，会
											System.out.println(msg);
											ctx.writeAndFlush(Unpooled.wrappedBuffer(msg.toString().getBytes()));
										}
									})
									.addLast(new ChannelInboundHandlerAdapter(){
										@Override
										public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
											ByteBuf buf = (ByteBuf) msg;
											System.out.println("1接收到客户端发送的数据："+buf.toString(StandardCharsets.UTF_8));
											ctx.fireChannelRead(msg);
										}
									})
									.addLast(new ChannelInboundHandlerAdapter(){
										@Override
										public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
											ByteBuf buf = (ByteBuf) msg;
											System.out.println("2接收到客户端发送的数据："+buf.toString(StandardCharsets.UTF_8));
											ctx.writeAndFlush("不会吧不会吧，不会还有人都看到这里了还没三连吧");   //这里可以write任何对象
										}
									});
						}
					})
					.bind(8080);
		} catch (Exception e) {
		}
	}
}