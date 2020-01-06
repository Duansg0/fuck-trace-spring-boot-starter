package com.talkee.trace.model;

import lombok.Data;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-06 20:22:11
 */
@Data
public class InterceptorInitInfoModel {
    /**
     *
     */
    private String execution;
    /**
     *
     */
    private String appName;

    public InterceptorInitInfoModel(Builder builder){
        this.appName = builder.appName;
        this.execution = builder.execution;
    }

    public static class Builder{

        private String execution;
        private String appName;

        public Builder buildAll(String execution , String appName) {
            this.execution = execution;
            this.appName = appName;
            return this;
        }

        public InterceptorInitInfoModel build() {
            return new InterceptorInitInfoModel(this);
        }
    }
}
