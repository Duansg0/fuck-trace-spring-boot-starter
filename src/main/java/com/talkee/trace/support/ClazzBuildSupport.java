package com.talkee.trace.support;

import com.talkee.trace.TraceException;
import com.talkee.trace.annotation.TraceCustomInterceptor;
import com.talkee.trace.base.GobalConfigContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-13 22:43:23
 */
public class ClazzBuildSupport {
    /**
     * @param clazzNamesStr
     * @return
     * @throws Exception
     */
    public static List<?> newInstanceList(String clazzNamesStr){
        AssertSupport.isNotBlank(clazzNamesStr,"clazzName is blank.");
        String[] clazzNames = clazzNamesStr.split(",");
        List<Object> gobalConfigContexts = new ArrayList<>();
        try{
            for (String clazzName : clazzNames) {
                Class<?> clazz = Class.forName(clazzName);
                if (!clazz.isAnnotationPresent(TraceCustomInterceptor.class)){
                    throw new TraceException("The TraceCustomInterceptor annotation does not exist.");
                }
                gobalConfigContexts.add(clazz.newInstance());
            }
        }catch (Exception e){
            throw new TraceException("NewInstance throws an exception.");
        }
        return gobalConfigContexts;
    }

}
