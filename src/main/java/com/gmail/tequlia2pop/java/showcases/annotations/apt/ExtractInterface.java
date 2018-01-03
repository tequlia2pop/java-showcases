package com.gmail.tequlia2pop.java.showcases.annotations.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 基于 APT 的注解处理。

@Target(ElementType.TYPE)
// 当我们从一个使用了该注解的类中抽取出接口之后，没有必要再保留这些注解信息。
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
	/**
	 * 将要生成的接口的名字。
	 * @return
	 */
	public String value();
}
