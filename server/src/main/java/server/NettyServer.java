package server;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class NettyServer {
	private Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

//	@Value("${netty.port}")
	private Integer port;

//	private NettyServerHandlerInitializer

	private EventLoopGroup bossGroup = new NioEventLoopGroup();

	private EventLoopGroup workerGroup = new NioEventLoopGroup();

	private Channel channel;

//	@PostConstruct
//	public void start() throws InterruptedException {
//		ServerBootstrap bootstrap = new ServerBootstrap();
//		bootstrap.group(bossGroup, workerGroup)
//				.channel(N)
//	}
}
