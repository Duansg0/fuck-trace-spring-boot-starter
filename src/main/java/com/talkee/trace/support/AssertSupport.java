package com.talkee.trace.support;

import com.talkee.trace.TraceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author Duansg
 * @desc AssertSupport
 * @date 2019-12-25 10:33:21
 */
public class AssertSupport {

    /**
     * @desc Cannot be null, otherwise throw exception
     * @param object
     * @param message
     * @throws TraceException
     */
    public static void isNotEmpty(Object object, String message) {
        if (ObjectUtils.isEmpty(object)){
            throw new TraceException(message);
        }
    }

    /**
     * @desc Cannot be null, otherwise throw exception
     * @param object
     * @param message
     * @throws TraceException
     */
    public static void isNotEmpty(Integer errorCode ,Object object, String message) {
        if (ObjectUtils.isEmpty(object)){
            throw new TraceException(errorCode,message);
        }
    }

    /**
     * @desc Cannot be null, otherwise throw exception
     * @param str
     * @param message
     * @throws TraceException
     */
    public static void isNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)){
            throw new TraceException(message);
        }
    }

    /**
     * @desc Null, otherwise throw exception
     * @param object
     * @param message
     */
    public static void isEmpty(Object object, String message) {
        if (!ObjectUtils.isEmpty(object)){
            throw new TraceException(message);
        }
    }

    /**
     * @desc All params cannot be null
     * @param message
     * @param objects
     */
    public static void isNotEmptyAll(String message, Object ... objects) {
        if (!ObjectUtils.isEmpty(objects)){
            for (int i = 0; i < objects.length; i++) {
                if (ObjectUtils.isEmpty(objects[i])){
                    throw new TraceException(message);
                }
            }
        }
    }

    /**
     * @desc All params cannot be null
     * @param message
     * @param objects
     */
    public static void isNotBlankAll(Integer errorCode, String message, Object ... objects) {
        if (!ObjectUtils.isEmpty(objects)){
            for (int i = 0; i < objects.length; i++) {
                if (ObjectUtils.isEmpty(objects[i])){
                    throw new TraceException(errorCode,message);
                }
            }
        }
    }

    /**
     * @desc There must be one
     * @param message
     * @param objects
     */
    public static void isNotEmptyMustOne(String message, Object ... objects) {
        if (!ObjectUtils.isEmpty(objects)){
            int count = 0 ;
            for (int i = 0; i < objects.length; i++) {
                if (ObjectUtils.isEmpty(objects[i])){
                    count++;
                }
            }
            if (count == objects.length){
                throw new TraceException(message);
            }
        }
    }

    /**
     * @desc
     * @param message
     * @param objects
     */
    public static void isEmptyMustOne(String message, Object ... objects) {
        if (!ObjectUtils.isEmpty(objects)){
            int count = 0 ;
            for (int i = 0; i < objects.length; i++) {
                if (! ObjectUtils.isEmpty(objects[i])){
                    count++;
                }
            }
            if (count == objects.length){
                throw new TraceException(message);
            }
        }
    }

    /**
     * @param bo
     * @param message
     */
    public static void isTrue(boolean bo, String message) {
        if (!bo){
            throw new TraceException(message);
        }
    }
}
