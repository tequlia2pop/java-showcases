package com.gmail.tequlia2pop.java.showcases.annotations.database2;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD) // 仅适用于字段
@Retention(RUNTIME)
public @interface Id {
}
