package sjq.bitcoin.network.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sjq.bitcoin.network.packet.BitcoinPacket;

import java.io.Closeable;
import java.io.IOException;

public class ChannelClientHandler extends SimpleChannelInboundHandler<BitcoinPacket> implements Closeable {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                BitcoinPacket bitcoinPacket) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    }

    public void close() throws IOException {

    }
}
