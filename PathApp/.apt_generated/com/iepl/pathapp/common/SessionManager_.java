//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.iepl.pathapp.common;

import android.content.Context;

public final class SessionManager_
    extends SessionManager
{

    private Context context_;

    private SessionManager_(Context context) {
        super(context);
        context_ = context;
        init_();
    }

    public static SessionManager_ getInstance_(Context context) {
        return new SessionManager_(context);
    }

    private void init_() {
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

}
