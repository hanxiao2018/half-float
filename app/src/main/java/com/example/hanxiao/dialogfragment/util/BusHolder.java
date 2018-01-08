package com.example.hanxiao.dialogfragment.util;

import com.squareup.otto.Bus;

/**
 * Created by wenzhi on 17/4/19.
 */

public class BusHolder {

    private BusHolder() {
    }

    public static final Bus getBus() {
        return BusInstance.sBus;
    }

    private static class BusInstance {
        private static Bus sBus = new Bus();
    }


}
