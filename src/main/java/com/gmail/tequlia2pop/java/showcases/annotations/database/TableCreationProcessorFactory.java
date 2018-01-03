package com.gmail.tequlia2pop.java.showcases.annotations.database;

import static com.sun.mirror.util.DeclarationVisitors.NO_OP;
import static com.sun.mirror.util.DeclarationVisitors.getDeclarationScanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

// 使用访问者模式的数据库示例。
// SQL 表生成器的例子。这里使用访问者模式来创建工厂和注解处理器。
//{Exec: apt -factory annotations.database.TableCreationProcessorFactory database/Member.java -s database}
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {

	// 返回注解处理器。
	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return new TableCreationProcessor(env);
	}

	// 检查是否 apt 工具发现的所有注解都有相应的处理器。
	// 如果在返回的 String 集合中没有你的注解的完整类名，apt 就会抱怨没有找到相应的处理器，
	// 从而发出警告信息，然后什么也不做就退出。
	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Arrays.asList("com.gmail.tequlia2pop.java.showcases.annotations.database.Table",
				"com.gmail.tequlia2pop.java.showcases.annotations.database.Constraints",
				"com.gmail.tequlia2pop.java.showcases.annotations.database.SQLString",
				"com.gmail.tequlia2pop.java.showcases.annotations.database.SQLInteger",
				"com.gmail.tequlia2pop.java.showcases.annotations.database.SQLInteger");
	}

	// 检查是否所有控制台输入的参数都是你提供支持的选项。
	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}

	private static class TableCreationProcessor implements AnnotationProcessor {
		private final AnnotationProcessorEnvironment env;

		private String sql = "";

		public TableCreationProcessor(AnnotationProcessorEnvironment env) {
			this.env = env;
		}

		// process() 做的仅仅是添加了一个访问者类，并初始化了 SQL 字符串。
		// getDeclarationScanner() 的两个参数都是访问者：第一个是在访问每个声明前使用，
		// 第二个则是在访问之后使用。由于这个处理器只需要在访问前使用访问者，所以第二个参数给的是 NO_OP。
		// NO_OP 是 DeclarationVisitor 接口中的 static 域，是一个什么也不做的 Declaration.Visitor。
		@Override
		public void process() {
			for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
				typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
				sql = sql.substring(0, sql.length() - 1) + ");";
				System.out.println("creation SQL is :\n" + sql);
				sql = "";
			}
		}

		// SimpleDeclarationVisitor 是一个适配器，实现了 DeclarationVisitorf 接口中的所有方法。
		// 因此，程序员只需将注意力放在自己需要的那些方法上。
		private class TableCreationVisitor extends SimpleDeclarationVisitor {
			// 检查 ClassDeclarationVisitor 对象是否带有 Table 注解，如果存在的话，将初始化 SQL 语句的第一部分。
			@Override
			public void visitClassDeclaration(ClassDeclaration d) {
				Table dbTable = d.getAnnotation(Table.class);
				if (dbTable != null) {
					sql += "CREATE TABLE ";
					sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
					sql += " (";
				}
			}

			// 检查域声明上的注解。
			@Override
			public void visitFieldDeclaration(FieldDeclaration d) {
				String columnName = "";
				if (d.getAnnotation(SQLInteger.class) != null) {
					SQLInteger sInt = d.getAnnotation(SQLInteger.class);
					// 如果未指定名称，则使用字段的名称。
					if (sInt.name().length() < 1)
						columnName = d.getSimpleName().toUpperCase();
					else
						columnName = sInt.name();
					sql += "\n    " + columnName + " INT" + getConstraints(sInt.constraints()) + ",";
				}
				if (d.getAnnotation(SQLString.class) != null) {
					SQLString sString = d.getAnnotation(SQLString.class);
					// 如果未指定名称，则使用字段的名称。
					if (sString.name().length() < 1)
						columnName = d.getSimpleName().toUpperCase();
					else
						columnName = sString.name();
					sql += "\n    " + columnName + " VARCHAR(" + sString.length() + ")"
							+ getConstraints(sString.constraints()) + ",";
				}
			}

			private String getConstraints(Constraints con) {
				String constraints = "";
				if (!con.nullable())
					constraints += " NOT NULL";
				if (con.primaryKey())
					constraints += " PRIMARY KEY";
				if (con.unique())
					constraints += " UNIQUE";
				return constraints;
			}
		}
	}
}