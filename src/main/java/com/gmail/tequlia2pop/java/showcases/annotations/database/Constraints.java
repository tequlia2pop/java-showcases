package com.gmail.tequlia2pop.java.showcases.annotations.database;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// 对于数据库表所能提供的所有约束而言，@Constraints 只表示了它的很小的子集，
// 不过它所要表达的思想已经很清楚了。
@Target(FIELD) // 仅适用于字段
@Retention(RUNTIME)
public @interface Constraints {
	/**
	 * （可选）列是否是主键。
	 * @return
	 */
	boolean primaryKey() default false;

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
}
