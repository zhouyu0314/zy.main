package com.zy.controller;

import com.zy.async.SystemCodeAsync;
import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
@EnableAsync
@CrossOrigin
@RestController
@RequestMapping("/api/code")
public class SystemCodeController {
    @Autowired
    private SystemCodeAsync systemCodeAsync;

    @CrossOrigin
    @PostMapping("/getSimpleForMain")
    public Dto getSimpleForMain(@RequestBody HashMap param) {
        /*
        id codeName codeNo
         */
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> mapA = new HashMap<>();
        mapA.put("id","A10000");
        mapA.put("codeName","江苏省");
        mapA.put("codeNo","32");
        Map<String,Object> mapB = new HashMap<>();
        mapB.put("id","A10001");
        mapB.put("codeName","山东省");
        mapB.put("codeNo","37");
        list.add(mapA);
        list.add(mapB);
        return DtoUtil.returnDataSuccess(list);
    }
    @CrossOrigin
    @PostMapping("/getSimpleForSub")
    public Dto getSimpleForSub(@RequestBody HashMap param) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> mapA = null;
        Map<String,Object> mapB = null;
        if ("A10000".equals(param.get("id").toString())) {
             mapA = new HashMap<>();
            mapA.put("id","A100001");
            mapA.put("codeName","南京市");
            mapA.put("codeNo","3201");
            mapA.put("parentId","A10000");
             mapB = new HashMap<>();
            mapB.put("id","A100002");
            mapB.put("codeName","徐州市");
            mapB.put("codeNo","3203");
            mapB.put("parentId","A10000");
        }else if("A10001".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A100011");
            mapA.put("codeName","济南市");
            mapA.put("codeNo","3701");
            mapA.put("parentId","A10001");
        }else if("A100001".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A1000011");
            mapA.put("codeName","秦淮区");
            mapA.put("codeNo","320101");
            mapA.put("parentId","A100001");
            mapB = new HashMap<>();
            mapB.put("id","A1000012");
            mapB.put("codeName","白下区");
            mapB.put("codeNo","320102");
            mapB.put("parentId","A100001");
        }else if("A100002".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A1000021");
            mapA.put("codeName","泉山区");
            mapA.put("codeNo","320301");
            mapA.put("parentId","A100002");
            mapB = new HashMap<>();
            mapB.put("id","A1000022");
            mapB.put("codeName","鼓楼区");
            mapB.put("codeNo","320302");
            mapB.put("parentId","A100002");
        }else if("A100011".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A1000111");
            mapA.put("codeName","历下区");
            mapA.put("codeNo","370301");
            mapA.put("parentId","A100011");
            mapB = new HashMap<>();
            mapB.put("id","A1000112");
            mapB.put("codeName","市辖区");
            mapB.put("codeNo","370302");
            mapB.put("parentId","A100011");
        }else if("A1000011".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10000111");
            mapA.put("codeName","秦淮区网点A");
            mapA.put("codeNo","3201011");
            mapA.put("parentId","A1000011");
            mapB = new HashMap<>();
            mapB.put("id","A10000112");
            mapB.put("codeName","秦淮区网点B");
            mapB.put("codeNo","3201012");
            mapB.put("parentId","A1000011");
        }else if("A1000012".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10000111");
            mapA.put("codeName","白下区网点A");
            mapA.put("codeNo","3201021");
            mapA.put("parentId","A1000012");
            mapB = new HashMap<>();
            mapB.put("id","A10000112");
            mapB.put("codeName","白下区网点B");
            mapB.put("codeNo","3201022");
            mapB.put("parentId","A1000012");
        }else if("A1000021".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10000211");
            mapA.put("codeName","泉山区网点A");
            mapA.put("codeNo","3203011");
            mapA.put("parentId","A1000021");
            mapB = new HashMap<>();
            mapB.put("id","A10000212");
            mapB.put("codeName","泉山区网点B");
            mapB.put("codeNo","3203012");
            mapB.put("parentId","A1000021");
        }else if("A1000022".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10000221");
            mapA.put("codeName","鼓楼区网点A");
            mapA.put("codeNo","3203021");
            mapA.put("parentId","A1000022");
            mapB = new HashMap<>();
            mapB.put("id","A10000222");
            mapB.put("codeName","鼓楼区网点B");
            mapB.put("codeNo","3203022");
            mapB.put("parentId","A1000022");
        }else if("A1000111".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10001111");
            mapA.put("codeName","历下区网点A");
            mapA.put("codeNo","3703011");
            mapA.put("parentId","A1000111");
            mapB = new HashMap<>();
            mapB.put("id","A10001112");
            mapB.put("codeName","历下区网点B");
            mapB.put("codeNo","3703012");
            mapB.put("parentId","A1000111");
        }
        else if("A1000112".equals(param.get("id").toString())){
            mapA = new HashMap<>();
            mapA.put("id","A10001111");
            mapA.put("codeName","市辖区网点A");
            mapA.put("codeNo","3703021");
            mapA.put("parentId","A1000112");
            mapB = new HashMap<>();
            mapB.put("id","A10001112");
            mapB.put("codeName","市辖区网点B");
            mapB.put("codeNo","3703022");
            mapB.put("parentId","A1000112");
        }

        list.add(mapA);
        if (mapB !=null) {

            list.add(mapB);
        }
        return DtoUtil.returnDataSuccess(list);
    }


    @PostMapping("/test")
    public Dto TestAsync() throws Exception{
        Future<String> stringFuture = systemCodeAsync.AsyncMethod();
        System.out.println("方法继续执行不阻塞");
        return DtoUtil.returnSuccess(stringFuture.get());
    }



}
