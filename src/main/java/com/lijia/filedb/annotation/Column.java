package com.lijia.filedb.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	Type type() default Type.auto;
	boolean id() default false;
	int length() default -1;
}
