package com.zy.service.impl;

import com.alibaba.fastjson.JSON;
import com.zy.entity.ChatMsg;
import com.zy.entity.DataContent;
import com.zy.enums.MsgActionEnum;
import com.zy.service.CommunicationService;
import com.zy.util.ChannelGroups;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Override
    public void getFileSize(HashMap param) {


        File file = new File(param.get("file").toString());
        String channelId = param.get("channelId").toString();
        long size = FileUtils.sizeOf(file);

        ChannelGroup channelGroup = ChannelGroups.getChannelGroup();
        for (Channel channel : channelGroup) {
            channelId.equals(channel.id().asLongText());
            //返回用户的通道id
            DataContent dataContent = new DataContent();
            dataContent.setAction(MsgActionEnum.CHAT.type);
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setMsg(FileUtils.byteCountToDisplaySize(size));
            dataContent.setChatMsg(chatMsg);
            String data = JSON.toJSONString(dataContent);
            channel.writeAndFlush(new TextWebSocketFrame(data));
        }
    }
}
