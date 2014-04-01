package com.lijia.filedb.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	public String name();
	public String schema();
	public String unicode() default  "utf-8";
}
