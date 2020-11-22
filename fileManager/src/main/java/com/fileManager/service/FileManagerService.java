package com.fileManager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FileManagerService {
    /**
     * 查看文件
     */
    List<Map<String,Object>> showAllFile(HashMap param);

    /**
     * 获取容量信息
     */
    Map<String,Object> showCapacity(HashMap param);
}
