package com.iepl.pathapp.event;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import com.squareup.otto.BasicBus;

@EBean(scope = Scope.Singleton)
public class BusProvider extends BasicBus {
	
}
