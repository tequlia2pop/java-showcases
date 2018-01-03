package com.gmail.tequlia2pop.java.showcases.annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// 下面是一个注解处理器的例子，它将读取一个类文件，检查其上的数据库注解，并生成用来创建数据库的 SQL 命令。
public class TableCreator {
	public static void main(String[] args) throws Exception {
		args = new String[1];
		args[0] = "com.gmail.tequlia2pop.java.showcases.annotations.database.Member";

		if (args.length < 1) {
			System.out.println("arguments: annotated classes");
			System.exit(0);
		}

		for (String className : args) {
			Class<?> cl = Class.forName(className);
			Table dbTable = cl.getAnnotation(Table.class);
			if (dbTable == null) {
				System.out.println("No Table annotations in class " + className);
				continue;
			}

			String tableName = dbTable.name();
			// 如果名称为空白，则使用类的大写名称：
			if (tableName.length() < 1)
				tableName = cl.getName().toUpperCase();

			List<String> columnDefs = new ArrayList<>();
			for (Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if (anns.length < 1)
					continue; // 不是一个数据库表列

				if (anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger) anns[0];
					// 如果未指定名称，则使用字段的大写名称。
					if (sInt.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sInt.name();
					columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
				}
				
				if (anns[0] instanceof SQLLong) {
					SQLLong sLong = (SQLLong) anns[0];
					// 如果未指定名称，则使用字段的大写名称。
					if (sLong.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sLong.name();
					columnDefs.add(columnName + " BIGINT" + getConstraints(sLong.constraints()));
				}

				if (anns[0] instanceof SQLString) {
					SQLString sString = (SQLString) anns[0];
					// 如果未指定名称，则使用字段的大写名称。
					if (sString.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sString.name();
					columnDefs.add(
							columnName + " VARCHAR(" + sString.length() + ")" + getConstraints(sString.constraints()));
				}
			}

			StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
			for (String columnDef : columnDefs)
				createCommand.append("\n    " + columnDef + ",");
			// 移除尾部的逗号
			String tableCreate = createCommand.substring(0, createCommand.length() - 1) + "\n);";
			System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);
		}
	}

	private static String getConstraints(Constraints con) {
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
