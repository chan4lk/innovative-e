//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.iepl.pathapp.event;

import android.content.Context;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BusProvider_
    extends BusProvider
{

    private Context context_;
    private static BusProvider_ instance_;

    private BusProvider_(Context context) {
        context_ = context;
    }

    public static BusProvider_ getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(null);
            instance_ = new BusProvider_(context.getApplicationContext());
            instance_.init_();
            OnViewChangedNotifier.replaceNotifier(previousNotifier);
        }
        return instance_;
    }

    private void init_() {
    }

}
