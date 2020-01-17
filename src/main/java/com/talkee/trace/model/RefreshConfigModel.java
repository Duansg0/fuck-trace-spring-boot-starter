package com.talkee.trace.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-15 20:35:22
 */
public class RefreshConfigModel extends ApplicationEvent {

    /**
     * @desc dao/pv开关
     * @desc key:spring.boot.trace.traceSwitch
     */
    @Getter
    @Setter
    private boolean traceSwitch;
    /**
     * @desc feign开关
     * @desc key:spring.boot.trace.traceSwitch.Feign
     */
    @Getter
    @Setter
    private boolean traceSwitchFeign;
    /**
     * @desc dubbo开关
     * @desc key:spring.boot.trace.traceSwitch.Dubbo
     */
    @Getter
    @Setter
    private boolean traceSwitchDubbo;
    /**
     * @desc 全局开关
     */
    @Getter
    @Setter
    private boolean gobalSwitch;

    /**
     * @param source
     */
    private RefreshConfigModel(Object source) {
        super(source);
    }

    /**
     * @param builder
     * @param source
     */
    public RefreshConfigModel(Builder builder, Object source){
        super(source);
        this.gobalSwitch = builder.gobalSwitch;
        this.traceSwitch = builder.traceSwitch;
        this.traceSwitchDubbo = builder.traceSwitchDubbo;
        this.traceSwitchFeign = builder.traceSwitchFeign;
    }

    /**
     * @author Duansg
     * @desc
     * @date
     */
    public static class Builder{

        private boolean traceSwitch = false;
        private boolean traceSwitchFeign = false;
        private boolean traceSwitchDubbo = false;
        private boolean gobalSwitch = false;

        public Builder buildTraceSwitch(boolean traceSwitch) {
            this.traceSwitch = traceSwitch;
            return this;
        }
        public Builder buildTraceSwitchFeign(boolean traceSwitchFeign) {
            this.traceSwitchFeign = traceSwitchFeign;
            return this;
        }
        public Builder buildTraceSwitchDubbo(boolean traceSwitchDubbo) {
            this.traceSwitchDubbo = traceSwitchDubbo;
            return this;
        }
        public Builder buildGobalSwitch(boolean gobalSwitch) {
            this.gobalSwitch = gobalSwitch;
            return this;
        }
        public RefreshConfigModel build(Object source) {
            return new RefreshConfigModel(this,source);
        }
    }

}
