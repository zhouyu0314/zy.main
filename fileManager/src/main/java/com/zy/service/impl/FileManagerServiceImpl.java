package com.zy.service.impl;

import com.zy.async.FileManagerAsync;
import com.zy.dto.Dto;
import com.zy.feignClient.CommunicationFeignClient;
import com.zy.service.FileManagerService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileManagerServiceImpl implements FileManagerService {
    private static Map<String, String> typeList = new HashMap<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private FileManagerAsync fileManagerAsync;

    @Autowired(required = false)
    private CommunicationFeignClient communicationFeignClient;


    static {
        //图片类型格式为2
        typeList.put("jpg", "img");
        typeList.put("jpeg", "img");
        typeList.put("png", "img");
        typeList.put("gif", "img");
        typeList.put("tif", "img");
        typeList.put("bmp", "img");
        //网页文件格式为3
        typeList.put("html", "html");
        //压缩文件格式为4
        typeList.put("zip", "rar");
        typeList.put("rar", "rar");
        typeList.put("gz", "rar");
        typeList.put("rpm", "rar");
        typeList.put("iso", "rar");
        typeList.put("deb", "rar");
        typeList.put("7z", "rar");
        //文档格式
        typeList.put("txt", "txt");
        typeList.put("sql", "txt");
        //word
        typeList.put("wps", "doc");
        typeList.put("doc", "doc");
        typeList.put("docx", "doc");
        //xls
        typeList.put("xls", "xls");
        typeList.put("et", "xls");
        typeList.put("ett", "xls");
        typeList.put("xlsx", "xls");
        //ppt
        typeList.put("ppt", "ppt");
        typeList.put("ppts", "ppt");
        typeList.put("dps", "ppt");
        typeList.put("dpt", "ppt");
        //music
        typeList.put("MPEG", "music");
        typeList.put("MP3", "music");
        typeList.put("MPEG-4", "music");
        typeList.put("MIDI", "music");
        typeList.put("WMA", "music");
        typeList.put("APE", "music");
        typeList.put("FLAC", "music");
        //movie
        typeList.put("AVI", "movie");
        typeList.put("mov", "movie");
        typeList.put("rmvb", "movie");
        typeList.put("rm", "movie");
        typeList.put("FLV", "movie");
        typeList.put("mp4", "movie");
        typeList.put("3GP", "movie");
        //执行文件
        typeList.put("exe", "exe");
        typeList.put("msi", "exe");
        //js
        typeList.put("js", "js");
        //vue
        typeList.put("vue", "vue");
        //java
        typeList.put("java", "java");
    }

    @Override
    public List<Map<String, Object>> showAllFile(HashMap param) {
        List<Map<String, Object>> files = new ArrayList<>();
        Map<String, Object> params = null;
        File parentFile = new File(param.get("path").toString());
        for (File file : parentFile.listFiles()) {
            params = new HashMap<>();
            String name = file.getName();
            params.put("name", name);
            params.put("path", file.getAbsolutePath());
            if (file.isDirectory()) {
                params.put("isDirectory", "directory");
                //params.put("size",null);
            } else if (file.isFile()) {
                //需要判断是什么类型的文件
                String type = name.substring(name.lastIndexOf(".") + 1);
                if (typeList.get(type) != null) {
                    params.put("isDirectory", typeList.get(type));
                } else {
                    params.put("isDirectory", "blank");
                }
                //暂时不获取文件大小，如果太大会超时
                //params.put("size", FileUtils.sizeOf(file));
            }
            files.add(params);
        }


        return files;
    }

    @Override
    public Map<String, Object> showCapacity(HashMap param) {
        DecimalFormat df = new DecimalFormat("0.0000");
        Map<String, Object> params = new HashMap<>();
        File parentFile = new File(param.get("path").toString());
        params.put("allCapacity", FileUtils.byteCountToDisplaySize(parentFile.getTotalSpace()));
        params.put("useCapacity", FileUtils.byteCountToDisplaySize(parentFile.getTotalSpace() - parentFile.getUsableSpace()));
        long data = parentFile.getTotalSpace() - parentFile.getUsableSpace();
        String percentage = df.format((float) data / parentFile.getTotalSpace());
        params.put("percentage", Double.valueOf(percentage) * 100);


        return params;

    }

    @Override
    public Map<String, Object> showFileInfo(HashMap param) {

        Map<String, Object> params = null;
        try {
            File file = new File(param.get("path").toString());
            params = new HashMap<>();
            //调用异步方法
            String channelId = param.get("channelId").toString();
            HashMap data = new HashMap();
            data.put("file", file);
            data.put("channelId", channelId);
            fileManagerAsync.getSize(data);
            params.put("mtime", sdf.format(file.lastModified()));
            Path path = Paths.get(param.get("path").toString());
            BasicFileAttributes attr = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).readAttributes();
            params.put("ctime", sdf.format(attr.creationTime().toMillis()));
            //需要判断是什么类型的文件

            String name = file.getName();
            String type = name.substring(name.lastIndexOf(".") + 1);
            params.put("name", name);
            if (!file.isDirectory()) {
                if (typeList.get(type) != null) {
                    params.put("type", typeList.get(type));
                } else {
                    params.put("type", "unknow");
                }
            } else {
                params.put("type", "directory");
            }
        } catch (Exception e) {

        }
        params.put("size", 0);
        return params;
    }


    @Override
    public Dto showTest(HashMap param) {
        Dto asd = communicationFeignClient.tets2(param);
        return asd;
    }

    @Override
    public void downLoad(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File downloadFile = new File(fileName);
        int fileLength = Integer.parseInt(Long.toString(downloadFile.length()));
        String range = null;
        ServletOutputStream outputStream = null;
        try {
            // 特殊头处理
            if (null != request.getHeader("RANGE")) {// 断点续传的头
                range = request.getHeader("RANGE");
            }
            if (null != request.getHeader("Range")) {
                range = request.getHeader("Range");
            }
            response.setContentType("application/x-msdownload");
            outputStream = response.getOutputStream();
            response.setContentLength(fileLength);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(downloadFile.getName().getBytes("utf-8"), "ISO8859-1"));// 处理默认文件名的中文问题
            response.setContentType("application/msexcel;charset-UTF-8");
            int startPos = 0;
            if (null != range) {// 断点续传
                startPos = Integer.parseInt(range.replaceAll("bytes=", "").replaceAll("-$|-\\d+$", ""));
            }
            if (startPos == 0) {
                FileCopyUtils.copy(new FileInputStream(downloadFile), response.getOutputStream());
            } else {// 断点续传
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                if (startPos != 0) {
                    /** 设置Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小] **/
                    StringBuffer sb = new StringBuffer("bytes ");
                    sb.append(Long.toString(startPos));
                    sb.append("-");
                    sb.append(Long.toString(fileLength - 1));
                    sb.append("/");
                    sb.append(Long.toString(fileLength));
                    response.setHeader("Content-Range", sb.toString());
                }
                if (startPos < fileLength) {
                    fileLength = fileLength - startPos;
                    outputStream.write(FileUtils.readFileToByteArray(downloadFile), (int) startPos, (int) fileLength);
                }
            }

            outputStream.flush();

        } catch (Exception e) {
            outputStream.flush();
        }finally {
            outputStream.close();
        }

    }


}
