package com.talkee.trace.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Duansg
 * @desc
 * @date
 */
@Getter
public enum  InvokeSideTypeEnum {
    /** 消费者 */
    CONSUMER("client", "消费者端"),

    /** 提供者 */
    PROVIDER("server", "消费者端");

    /** 名称 */
    private String name;

    /** 描述 */
    private String desc;

    /**
     * @param name
     * @param desc
     */
    InvokeSideTypeEnum(String name, String desc){
        this.name = name;
        this.desc = desc;
    }
}
