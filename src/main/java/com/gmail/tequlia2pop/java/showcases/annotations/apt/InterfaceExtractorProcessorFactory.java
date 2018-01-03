package com.gmail.tequlia2pop.java.showcases.annotations.apt;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

// 基于 APT 的注解处理。

public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {

	// 返回注解处理器。
	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return new InterfaceExtractorProcessor(env);
	}

	// 检查是否 apt 工具发现的所有注解都有相应的处理器。
	// 如果在返回的 String 集合中没有你的注解的完整类名，apt 就会抱怨没有找到相应的处理器，
	// 从而发出警告信息，然后什么也不做就退出。
	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Collections.singleton("com.gmail.tequlia2pop.java.showcases.annotations.apt.ExtractInterface");
	}

	// 检查是否所有控制台输入的参数都是你提供支持的选项。
	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}
}
