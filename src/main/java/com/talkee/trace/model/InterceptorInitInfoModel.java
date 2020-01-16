package com.talkee.trace.model;

import lombok.Data;

/**
 * @author Duansg
 * @desc Interceptor generator basic information.
 * @date 2020-01-06 20:22:11
 */
@Data
public class InterceptorInitInfoModel {
    /**
     * @desc Execution expression
     */
    private String execution;
    /**
     * @desc AppName
     */
    private String appName;
    /**
     * @desc Interceptor digest switch
     */
    private boolean digestLogOpen;

    public InterceptorInitInfoModel(Builder builder){
        this.appName = builder.appName;
        this.execution = builder.execution;
        this.digestLogOpen = builder.digestLogOpen;
    }

    public static class Builder{

        private String execution;
        private String appName;
        private boolean digestLogOpen;

        public Builder buildAll(String execution , String appName,boolean digestLogOpen) {
            this.execution = execution;
            this.appName = appName;
            this.digestLogOpen = digestLogOpen;
            return this;
        }
        public Builder buildExecution(String execution) {
            this.execution = execution;
            return this;
        }
        public Builder buildAppName(String appName) {
            this.appName = appName;
            return this;
        }
        public InterceptorInitInfoModel build() {
            return new InterceptorInitInfoModel(this);
        }
    }
}
