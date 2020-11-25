package com.fileManager.async;

import com.communication.feign.CommunicationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FileManagerAsync {
    @Autowired(required = false)
    private CommunicationFeignClient communicationFeignClient;

    @Async
    public void getSize(HashMap param){

        communicationFeignClient.getFileSize(param);

    }



}
