package com.gmail.tequlia2pop.java.showcases.exceptions.rethrow;

import static com.gmail.tequlia2pop.utils.Print.println;

// 支持动态添加字段的类。
// 演示异常链。

class DynamicFieldsException extends Exception {
}

public class DynamicFields {
	public static void main(String[] args) {
		DynamicFields df = new DynamicFields(3);
		println(df);
		try {
			df.setField("d", "A value for d");
			df.setField("number", 47);
			df.setField("number2", 48);
			println(df);
			df.setField("d", "A new value for d");
			df.setField("number3", 11);
			println("df: " + df);
			println("df.getField(\"d\") : " + df.getField("d"));
			Object field = df.setField("d", null); // Exception
		} catch (NoSuchFieldException e) {
			e.printStackTrace(System.out);
		} catch (DynamicFieldsException e) {
			e.printStackTrace(System.out);
		}
	}

	/**
	 * 一个数组，其元素是“成对的对象”（Object[size][2]）。
	 * 第一个对象表示字符标识符（一个字符串），第二个表示字段值，值的类型可以是除基本类型外的任意类型。
	 */
	private Object[][] fields;

	public DynamicFields(int initialSize) {
		fields = new Object[initialSize][2];
		for (int i = 0; i < initialSize; i++)
			fields[i] = new Object[] { null, null };
	}

	/**
	 * 通过标识获取字段值。
	 * 
	 * 如果客户端程序员调用 getField()，那么他就有责任处理这个可能抛出的 NoSuchFieldException。
	 * 
	 * @param id
	 * @return
	 * @throws NoSuchFieldException 如果指定标识的字段不存在
	 */
	public Object getField(String id) throws NoSuchFieldException {
		return fields[getFieldNumber(id)][1];
	}

	/**
	 * 通过标识设置字段值。
	 * 如果该字段不存在，就新建一个字段，并放入值。
	 * 如果空间不够了，将建立一个更长的数组，并把原来数组的元素复制进去。
	 * 
	 * @param id
	 * @param value
	 * @return 旧的字段值
	 * @throws DynamicFieldsException 如果要添加的字段值为 null
	 */
	public Object setField(String id, Object value) throws DynamicFieldsException {
		if (value == null) {
			// 大多数异常对象没有 "cause" 构造函数，在这些情况下，你必须使用 initCause()。在所有的Throwable子类中都可用。
			// 所有的 Throwable 的子类在构造器中都可以接受一个  "cause" 对象作为参数。
			DynamicFieldsException dfe = new DynamicFieldsException();
			dfe.initCause(new NullPointerException());
			throw dfe;
		}

		int fieldNumber = hasField(id);
		if (fieldNumber == -1)
			fieldNumber = makeField(id);

		Object result = null;
		try {
			result = getField(id); // Get old value
		}
		// 如果客户端程序员调用 setField()，
		// 然后 setField() 在转调 getField() 时抛出了 NoSuchFieldException，
		// 这种情况将被视为编程错误，
		// 应该将 NoSuchFieldException 转换为 RuntimeException 并抛出。
		catch (NoSuchFieldException e) {
			// Use constructor that takes "cause":
			throw new RuntimeException(e);
		}
		fields[fieldNumber][1] = value;
		return result;
	}

	private int hasField(String id) {
		for (int i = 0; i < fields.length; i++)
			if (id.equals(fields[i][0]))
				return i;
		return -1;
	}

	private int getFieldNumber(String id) throws NoSuchFieldException {
		int fieldNum = hasField(id);
		if (fieldNum == -1)
			throw new NoSuchFieldException();
		return fieldNum;
	}

	private int makeField(String id) {
		for (int i = 0; i < fields.length; i++)
			if (fields[i][0] == null) {
				fields[i][0] = id;
				return i;
			}

		// No empty fields. Add one:
		Object[][] tmp = new Object[fields.length + 1][2];
		for (int i = 0; i < fields.length; i++)
			tmp[i] = fields[i];
		for (int i = fields.length; i < tmp.length; i++)
			tmp[i] = new Object[] { null, null };
		fields = tmp;

		// Recursive call with expanded fields:
		return makeField(id);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Object[] obj : fields) {
			result.append(obj[0]);
			result.append(": ");
			result.append(obj[1]);
			result.append("\n");
		}
		return result.toString();
	}
}