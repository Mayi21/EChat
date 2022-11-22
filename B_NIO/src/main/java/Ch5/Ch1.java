package Ch5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

public class Ch1 {
	public static void main(String[] args) {
		//创建一个初始容量为10的ByteBuf缓冲区，这里的Unpooled是用于快速生成ByteBuf的工具类
		//至于为啥叫Unpooled是池化的意思，ByteBuf有池化和非池化两种，区别在于对内存的复用，我们之后再讨论
		ByteBuf buf = Unpooled.buffer(10);
		System.out.println("初始状态："+ Arrays.toString(buf.array()));
		buf.writeInt(-888888888);   //写入一个Int数据
		System.out.println("写入Int后："+Arrays.toString(buf.array()));
		buf.readShort();   //无需翻转，直接读取一个short数据出来
		System.out.println("读取Short后："+Arrays.toString(buf.array()));
		//丢弃操作，会将当前的可读部分内容丢到最前面，并且读写指针向前移动丢弃的距离
		buf.discardReadBytes();
		System.out.println("丢弃之后："+Arrays.toString(buf.array()));
		//清空操作，清空之后读写指针都归零
		buf.clear();
		System.out.println("清空之后："+Arrays.toString(buf.array()));
		/**
		 * 注意出栈站操作应该在入站操作的前面，当我们使用ChannelHandlerContext的write方法时，
		 * 是从流水线的当前位置倒着往前找下一个ChannelOutboundHandlerAdapter，而我们之前使用
		 * 的ChannelInboundHandlerAdapter是从前往后找下一个，如果我们使用的是Channel的write
		 * 方法，那么会从整个流水线的最后开始倒着往前找ChannelOutboundHandlerAdapter，一定要注意顺序。
		 * */
	}
}
