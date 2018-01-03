package com.gmail.tequlia2pop.java.showcases.annotations.database2;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD) // 仅适用于字段
@Retention(RUNTIME)
public @interface Column {
	/**
	 * （可选）列的名称。 
	 * 缺省为字段名称。
	 * @return
	 */
	String name() default "";
	
	/**
	 * （可选）列是否可以为空。
	 * @return
	 */
	boolean nullable() default true;
	
	/**
	 * （可选）列是否是唯一的键。
	 * @return
	 */
	boolean unique() default false;
	
	/**
	 * （可选）列的长度。
	 * @return
	 */
	int length() default 255;
}
