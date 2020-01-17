package com.talkee.trace.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-17 22:33:05
 */
public abstract class RefreshConfigPublish {

    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param message
     */
    public abstract void publish(String message);
}
