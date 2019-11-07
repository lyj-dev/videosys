package com.lyj.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static Map<String, Object> pubPage(long total, List<?> list) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", list); // 获取到的分页数据
        return map;
    }
}
