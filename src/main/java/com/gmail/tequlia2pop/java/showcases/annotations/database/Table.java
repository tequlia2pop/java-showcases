package com.gmail.tequlia2pop.java.showcases.annotations.database;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
	/**
	 * （可选）表的名称。	
	 *  缺省为所属类的大写的非限定名。
	 *  
	 * @return
	 */
	String name() default "";
}
