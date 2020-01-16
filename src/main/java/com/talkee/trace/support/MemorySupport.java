package com.talkee.trace.support;

import com.talkee.trace.model.MemoryModel;
import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author Duansg
 * @desc MemorySupport
 * @date 2020-01-15 22:12:03
 */
public class MemorySupport {

    /**
     *
     */
    private final static MemoryModel memoryModel = new MemoryModel();
    /**
     * @desc 手动管理内存
     */
    private final static Unsafe unsafe = (Unsafe) AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            try {
                ReflectionUtils.makeAccessible(field);
            } catch (Throwable throwable) {
                return throwable;
            }
            return field.get(null);
        } catch (Exception e) {
            return e;
        }
    });

    /**
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        try{
            return System.getProperty(key);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public static boolean setProperty(String key ,String value) {
        try{
            System.setProperty(key,value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @param offset
     * @param bool
     */
    public static void putBoolean(Long offset, Boolean bool){
        unsafe.putBoolean(memoryModel, offset, bool);
    }

    /**
     * @param offset
     * @return
     */
    public static Boolean getBoolean(Long offset){
        return unsafe.getBoolean(memoryModel,offset);
    }

    /**
     * @desc 获取指定偏移量
     * @return
     */
    public static Long getBooleanOffset() throws NoSuchFieldException {
        return unsafe.objectFieldOffset(MemoryModel.class.getDeclaredField("bool"));
    }

}
