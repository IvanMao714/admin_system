package com.gsiv.springboot.utils;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.utils
 * @className: ThreadLocalUtil
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 13:28
 * @version: 1.0
 */
public class ThreadLocalUtil {

    private static ThreadLocal<String> threadLocalUserid = new ThreadLocal<>();

    public static void setUserid(String username) {
        threadLocalUserid.set(username);
    }

    public static String getUserid(){
        return threadLocalUserid.get();
    }

    public static void clearUserid(){
        threadLocalUserid.remove();
    }

}
