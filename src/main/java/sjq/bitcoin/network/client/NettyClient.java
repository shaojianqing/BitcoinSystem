package sjq.bitcoin.network.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import sjq.bitcoin.network.codec.BitcoinPacketDecoder;
import sjq.bitcoin.network.codec.BitcoinPacketEncoder;
import sjq.bitcoin.network.handler.ChannelClientHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class NettyClient implements Client {

    private static final int TIMEOUT_SECONDS = 5;

    private Callback callback;

    private EventLoopGroup executors;

    private Bootstrap bootstrap;

    private ChannelClientHandler clientHandler;

    private InetSocketAddress address;

    public NettyClient(InetSocketAddress address, Callback callback) {
        this.address = address;
        this.callback = callback;
    }

    public boolean openConnection() {
        executors = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .group(executors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2 * TIMEOUT_SECONDS)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline()
                                .addLast(new BitcoinPacketEncoder())
                                .addLast(new BitcoinPacketDecoder())
                                .addLast(clientHandler);
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(address);

        } catch (Exception e) {

        }
        return true;
    }

    public boolean isRunning() {
        return false;
    }

    public void sendData(byte[] data) throws IOException {

    }
}