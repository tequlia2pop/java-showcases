package com.gmail.tequlia2pop.java.showcases.annotations.database;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD) // 仅适用于字段
@Retention(RUNTIME)
public @interface SQLLong {
	/**
	 * （可选）列的名称。 
	 * 缺省为字段名称。
	 * @return
	 */
	String name() default "";

	/**
	 * （可选）列的约束。
	 * 缺省为一个所有元素都为默认值的 @Constraints 注解。
	 * @return
	 */
	Constraints constraints() default @Constraints;
}
