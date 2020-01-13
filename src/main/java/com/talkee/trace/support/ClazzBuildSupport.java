package com.talkee.trace.support;

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
    public static List<GobalConfigContext> newInstanceList(String clazzNamesStr) throws Exception{
        AssertSupport.isNotBlank(clazzNamesStr,"clazzName not blank.");
        String[] clazzNames = clazzNamesStr.split(",");
        List<GobalConfigContext> gobalConfigContexts = new ArrayList<>();
        for (String clazzName : clazzNames) {
            Class<?> clazz = Class.forName(clazzName);
            gobalConfigContexts.add((GobalConfigContext) clazz.newInstance());
        }
        return gobalConfigContexts;
    }
}
