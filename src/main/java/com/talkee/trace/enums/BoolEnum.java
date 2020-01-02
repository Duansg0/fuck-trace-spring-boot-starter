package com.talkee.trace.enums;

import lombok.Getter;
import java.util.Arrays;

/**
 * @author Duansg
 * @desc
 * @date 2019-12-31 18:13:22
 */
@Getter
public enum BoolEnum {

    TRUE("S", "success"),
    FALSE("F", "false");

    BoolEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private String value;

    private String desc;

    public static BoolEnum get(String value){
        return Arrays.stream(BoolEnum.values()).filter(x -> x.getValue().equals(value)).findAny().orElse(BoolEnum.FALSE);
    }

    public static String get(boolean isSuccess) {
       return isSuccess ? BoolEnum.TRUE.getValue() : BoolEnum.FALSE.getValue();
    }
}
