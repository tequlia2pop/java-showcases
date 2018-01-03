package com.gmail.tequlia2pop.java.showcases.annotations.database2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// 下面是一个注解处理器的例子，它将读取一个类文件，检查其上的数据库注解，并生成用来创建数据库的 SQL 命令。
public class TableCreator {
	public static void main(String[] args) throws Exception {
		args = new String[1];
		args[0] = "com.gmail.tequlia2pop.java.showcases.annotations.database2.Member";

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

				if (anns[0] instanceof Id) {
					Id id = (Id) anns[0];
					// 默认映射到与其同名的主键列。
					columnName = field.getName().toUpperCase();
					columnDefs.add(columnName + " " + getSQLType(field.getType(), -1) + " PRIMARY KEY");
				}

				if (anns[0] instanceof Column) {
					Column col = (Column) anns[0];
					// 如果未指定名称，则使用字段的大写名称。
					if (col.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = col.name();
					columnDefs.add(columnName + " " + getSQLType(field.getType(), col.length()) + getConstraints(col));
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

	private static String getSQLType(Class<?> clazz, int length) {
		String sqlType = "";
		if (clazz == int.class || clazz == Integer.class) {
			sqlType = "INTEGER";
		} else if (clazz == long.class || clazz == Long.class) {
			sqlType = "BIGINT";
		} else if (clazz == String.class) {
			sqlType = "VARCHAR(" + length + ")";
		}
		return sqlType;
	}

	private static String getConstraints(Column col) {
		String constraints = "";
		if (!col.nullable())
			constraints += " NOT NULL";
		if (col.unique())
			constraints += " UNIQUE";
		return constraints;
	}
}
