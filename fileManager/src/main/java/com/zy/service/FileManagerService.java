package com.zy.service;

import com.zy.dto.Dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 显示文件属性
     * @param param
     * @return
     */
    Map<String,Object> showFileInfo(HashMap param);

    void downLoad(String fileName, HttpServletRequest request, HttpServletResponse response)throws Exception;



}
