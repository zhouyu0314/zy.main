package com.zy.feignClient;

import com.zy.dto.Dto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@FeignClient(value = "com.zy.communication")
public interface CommunicationFeignClient {
    @PostMapping("/api/communication/getFileSize")
    void getFileSize(@RequestBody HashMap param);


    @PostMapping("/api/communication/tets")
     Dto tets(@RequestBody Map<String,Object> param);

    @PostMapping("/api/communication/tets2")
    Dto tets2(@RequestBody Map<String,Object> param);
}
