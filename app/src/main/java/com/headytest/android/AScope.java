package com.headytest.android;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by amod on 6/12/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AScope {
}
