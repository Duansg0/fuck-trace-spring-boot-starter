package com.talkee.trace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicPropertyUtil {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(DynamicPropertyUtil.class);

    /**
     * 获取String类型属性
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue){
        try {
            return System.getProperty(key, defaultValue);
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "获取String动态配置异常,key={0},defaultValue={1}", key, defaultValue);
            return defaultValue;
        }
    }

    /**
     * 设置String类型属性
     *
     * @param key
     * @param value
     */
    public static void setProperty(String key, String value) {
        try {
            System.setProperty(key, value);
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "设置String动态配置异常,key={0},value={1}", key, value);
        }
    }

    /**
     * 获取Long类型动态配置
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getProperty(String key, long defaultValue){
        try {
            String value = System.getProperty(key, String.valueOf(defaultValue));
            return Long.parseLong(value);
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "获取long动态配置异常,key={0},defaultValue={1}", key, defaultValue);
            return defaultValue;
        }
    }

    /**
     * 设置long类型属性
     *
     * @param key
     * @param value
     */
    public static void setProperty(String key, long value) {
        try {
            System.setProperty(key, String.valueOf(value));
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "设置long动态配置异常,key={0},value={1}", key, value);
        }
    }

    /**
     * 获取boolean类型动态配置
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getProperty(String key, boolean defaultValue){
        try {
            String value = System.getProperty(key, String.valueOf(defaultValue));
            return Boolean.valueOf(value);
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "获取boolean动态配置异常,key={0},defaultValue={1}", key, defaultValue);
            return defaultValue;
        }
    }

    /**
     * 设置boolean类型属性
     *
     * @param key
     * @param value
     */
    public static void setProperty(String key, boolean value) {
        try {
            System.setProperty(key, String.valueOf(value));
        } catch (Throwable t) {
            LoggerFormatUtil.warn(logger, "设置boolean动态配置异常,key={0},value={1}", key, value);
        }
    }
}
