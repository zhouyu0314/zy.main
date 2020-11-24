package com.fileManager.async;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileManagerAsync {

    @Async
    public Long getSize(File file){
        long size = FileUtils.sizeOf(file);
        return null;
    }

}
