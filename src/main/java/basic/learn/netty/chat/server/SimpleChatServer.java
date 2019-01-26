package basic.learn.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wangtao
 * @date 2019-01-26
 * @desc
 */
public class SimpleChatServer {

    private int port;

    public SimpleChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new SimpleChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer 启动了");

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = sb.bind(port).sync();

            // 等待服务器 socket 关闭 。
            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

            System.out.println("SimpleChatServer 关闭了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new SimpleChatServer(port).run();
    }
}
