package com.communication.service.impl;

import com.communication.service.CommunicationService;
import com.communication.util.ChannelGroups;
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
        File file = (File) param.get("file");
        String channelId = param.get("channelId").toString();
        long size = FileUtils.sizeOf(file);
        ChannelGroup channelGroup = ChannelGroups.getChannelGroup();
        channelGroup.forEach(ch->{
            if (channelId.equals(ch.id().asLongText())) {
                ch.writeAndFlush(new TextWebSocketFrame(size+""));
            }
        });
    }
}
