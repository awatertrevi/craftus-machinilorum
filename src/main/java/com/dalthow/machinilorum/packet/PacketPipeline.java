package com.dalthow.machinilorum.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarId;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarRadius;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarSignal;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class PacketPipeline.java
 * 
 **/

@ChannelHandler.Sharable
public class PacketPipeline extends MessageToMessageCodec <FMLProxyPacket, AbstractPacket> 
{
	// Declaration of the packets and channels.
	
    private EnumMap <Side, FMLEmbeddedChannel> channels;
    private LinkedList <Class <? extends AbstractPacket>> packets = new LinkedList <Class <? extends AbstractPacket>>();
  
    private boolean isPostInitialised = false;

    
    /**
     * registerPacket Adds a packet to the linked list if the size is smaller then 256.
     * 
     * @param  {Class}   The abstract class.
     * 
     * @return {boolean} If the packet was registered successfully or not.
     */
    public boolean registerPacket(Class <? extends AbstractPacket> abstractClass) 
    {
        if(packets.size() > 256) 
        {
            return false;
        }

        if(packets.contains(abstractClass)) 
        {
            return false;
        }

        if(isPostInitialised) 
        {
            return false;
        }

        packets.add(abstractClass);
        
        return true;
    }

    
    // Encodes the packet.
    
    @Override
    protected void encode(ChannelHandlerContext context, AbstractPacket message, List <Object> output) throws Exception 
    {
        ByteBuf buffer = Unpooled.buffer();
        
        Class <? extends AbstractPacket> abstractClass = message.getClass();
        
        if (!packets.contains(message.getClass()))
        {
            throw new NullPointerException("No packet in registry for: " + message.getClass().getCanonicalName());
        }

        byte discriminator = (byte) packets.indexOf(abstractClass);
        
        buffer.writeByte(discriminator);
        message.encodeInto(context, buffer);
        
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), context.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        output.add(proxyPacket);
    }

    
    // Decodes the packet.
    
    @Override
    protected void decode(ChannelHandlerContext context, FMLProxyPacket message, List <Object> output) throws Exception
    {
        ByteBuf payLoad = message.payload();
        
        byte discriminator = payLoad.readByte();
        
        Class <? extends AbstractPacket> abstractClass = packets.get(discriminator);
        
        if(abstractClass == null) 
        {
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        }

        AbstractPacket packet = abstractClass.newInstance();
        packet.decodeInto(context, payLoad.slice());

        EntityPlayer player;
       
        switch (FMLCommonHandler.instance().getEffectiveSide()) 
        {
        	case CLIENT: player = getClientPlayer();
			             packet.handleClientSide(player);
			             
			             break;

          
            case SERVER: INetHandler netHandler = context.channel().attr(NetworkRegistry.NET_HANDLER).get();
		                 player = ((NetHandlerPlayServer) netHandler).playerEntity;
		                 packet.handleServerSide(player);
		               
		                 break;

            			 default: break;
        }

        output.add(packet);
    }

    
    /**
     * initialize Registers all packets.
     * 
     * @return {void}
     */
    public void initialize() 
    {
        channels = NetworkRegistry.INSTANCE.newChannel(Reference.modId, this);
        
        registerPacket(PacketMobRadarId.class);
        registerPacket(PacketMobRadarSignal.class);
        registerPacket(PacketMobRadarRadius.class);
    }

    
    /**
     * getClientPlayer Gets the current player.
     * 
     * @return {void}
     */
    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer() 
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    
    /**
     * sendToAll Sends a packet to the server and all clients.
     * 
     * @param  {AbstractPacket} packet The packet that should be send.
     * 
     * @return {void}
     */
    public void sendToAll(AbstractPacket packet) 
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    
    /**
     * sendToAll Sends a packet to a specific client.
     * 
     * @param  {AbstractPacket} packet The packet that should be send.
     * @param  {EntityPlayerMP} player The player that should receive the packet.
     * 
     * @return {void}
     */
    public void sendTo(AbstractPacket packet, EntityPlayerMP player) 
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    
    /**
     * sendToAllAround Sends a packet to nearby players.
     * 
     * @param  {AbstractPacket} packet The packet that should be send.
     * @param  {TargetPoint} point     The location the packet should be send to.
     * 
     * @return {void}
     */
    public void sendToAllAround(AbstractPacket packet, TargetPoint point)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    
    /**
     * sendToDimension Sends a packet to everyone in a certain dimension.
     * 
     * @param  {AbstractPacket} packet The packet that should be send.
     * @param  {int} dimensionId       The dimension Id.
     * 
     * @return {void}
     */
    public void sendToDimension(AbstractPacket packet, int dimensionId) 
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    
    /**
     * sendToServer Sends a packet to the server.
     * 
     * @param  {AbstractPacket} packet The packet that should be send.
     * 
     * @return {void}
     */
    public void sendToServer(AbstractPacket packet) 
    {
        channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        channels.get(Side.CLIENT).writeAndFlush(packet);
    }
}
