package com.gmail.tequlia2pop.java.showcases.annotations.apt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

// 基于 APT 的注解处理。
// 该处理器可以从标记了 @ExtractInterface 的类中提取其接口方法，并生成相应的 interface java 文件。
//{Exec: apt -factory package.InterfaceExtractorProcessorFactory Multiplier.java -s ../annotations}

public class InterfaceExtractorProcessor implements AnnotationProcessor {
	
	private final AnnotationProcessorEnvironment env;
	
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();

	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	@Override
	public void process() {
		for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
			ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
			if (annot == null)
				break;
			
			// 找到 public 方法（不包括 static 那些）。
			for (MethodDeclaration m : typeDecl.getMethods())
				if (m.getModifiers().contains(Modifier.PUBLIC) && !(m.getModifiers().contains(Modifier.STATIC)))
					interfaceMethods.add(m);
			
			// 在一个 .java 文件中，创建新的接口中的方法定义。
			if (interfaceMethods.size() > 0) {
				try {
					PrintWriter writer = env.getFiler().createSourceFile(annot.value());
					writer.println("package " + typeDecl.getPackage().getQualifiedName() + ";");
					writer.println("public interface " + annot.value() + " {");
					for (MethodDeclaration m : interfaceMethods) {
						writer.print("  public ");
						writer.print(m.getReturnType() + " ");
						writer.print(m.getSimpleName() + " (");
						int i = 0;
						for (ParameterDeclaration parm : m.getParameters()) {
							writer.print(parm.getType() + " " + parm.getSimpleName());
							if (++i < m.getParameters().size())
								writer.print(", ");
						}
						writer.println(");");
					}
					writer.println("}");
					writer.close();
				} catch (IOException ioe) {
					throw new RuntimeException(ioe);
				}
			}
		}
	}
}
