package com.talkee.trace.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Duansg
 * @desc
 * @date
 */
public abstract class RefreshConfigPublish {

    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param message
     */
    public abstract void publish(String message);
}
