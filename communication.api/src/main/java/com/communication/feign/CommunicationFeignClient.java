package com.communication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@FeignClient(value = "communication")
public interface CommunicationFeignClient {
    @PostMapping("/api/communication/getFileSize")
    void getFileSize(@RequestBody HashMap param);


    @PostMapping("/api/communication/tets/{a}")
    void tets(@PathVariable String a);
}
