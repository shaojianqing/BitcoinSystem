package sjq.bitcoin.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import sjq.bitcoin.network.packet.BitcoinPacket;

public class BitcoinPacketEncoder extends MessageToByteEncoder<BitcoinPacket> {

    protected void encode(ChannelHandlerContext channelHandlerContext,
                          BitcoinPacket bitcoinPacket, ByteBuf byteBuf) throws Exception {

    }
}
