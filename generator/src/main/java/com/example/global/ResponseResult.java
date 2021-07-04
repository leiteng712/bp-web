package com.example.global;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;

/**
 * @author leiteng
 * @date 2019/11/5
 */
public class ResponseResult extends HashMap<String, Object> {

    public ResponseResult(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static ResponseResult obj(Object obj) {
        ResponseResult r = new ResponseResult(1, "SUCCESS");
        r.put("data", obj);
        return r;
    }

    public static ResponseResult page(IPage page) {
        ResponseResult r = new ResponseResult(1, "SUCCESS");
        r.put("data", page.getRecords());
        r.put("total", page.getTotal());
        r.put("pageIndex", page.getCurrent());
        r.put("pageSize", page.getSize());
        return r;
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(500, msg);
    }

    public static ResponseResult error(int code, String msg) {
        return new ResponseResult(code, msg);
    }
}
