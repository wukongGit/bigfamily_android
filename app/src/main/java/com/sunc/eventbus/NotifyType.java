package com.sunc.eventbus;

/**
 * Description:
 * Date: 2018-08-29 15:18
 * Author: suncheng
 */

public class NotifyType {
    public static final int TYPE_NETWORK_CHANGED = 101;//网络状态变化

    private int mType;
    private Object mExtra;

    public NotifyType(int type) {
        this(type, null);
    }

    public NotifyType(int type, Object extra) {
        this.mType = type;
        this.mExtra = extra;
    }

    public int getType() {
        return mType;
    }

    public Object getExtra() {
        return mExtra;
    }

    public interface INotify {
        void onNotify(NotifyType type);
    }
}
