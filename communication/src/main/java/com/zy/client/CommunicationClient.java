package com.zy.client;

import com.alibaba.fastjson.JSON;
import com.zy.entity.ChatMsg;
import com.zy.entity.DataContent;
import com.zy.enums.MsgActionEnum;
import com.zy.util.ChannelGroups;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class CommunicationClient extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel channel = ctx.channel();
        //返回用户的通道id
        DataContent dataContent = new DataContent();
        dataContent.setAction(MsgActionEnum.CONNECT.type);
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setMsg( channel.id().asLongText());
        dataContent.setChatMsg(chatMsg);
        String data = JSON.toJSONString(dataContent);
        ctx.writeAndFlush(new TextWebSocketFrame(data));

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //当用户上线了 注册其通道
        Channel channel = ctx.channel();
        ChannelGroups.getChannelGroup().add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ChannelGroups.getChannelGroup().remove(ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
