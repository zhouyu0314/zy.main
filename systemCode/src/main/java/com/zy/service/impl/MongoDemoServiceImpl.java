package com.zy.service.impl;

import com.zy.service.MongoDemoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class MongoDemoServiceImpl implements MongoDemoService {


    @Cacheable(value = "cache", key = "#param")
    @Override
    public String test(String param) {
        System.out.println("没有使用缓存");
        if ("A".equals(param)) {
            return "GOOD";
        } else if ("B".equals(param)) {
            return "NORMAL";
        } else {
            return "BAD";
        }

    }
}
