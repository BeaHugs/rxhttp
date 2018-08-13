package com.mvp.demo.utils;


import org.greenrobot.eventbus.EventBus;

/**
 * @author Wang Yi Bo
 * @date 2018/6/8
 * 作用: EventBus
 */

public class EventBusUtils {

    static volatile EventBusUtils defaultInstance;

    public static EventBusUtils getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBusUtils.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBusUtils();
                }
            }
        }
        return defaultInstance;
    }

    public void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public  void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public  void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public  void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
