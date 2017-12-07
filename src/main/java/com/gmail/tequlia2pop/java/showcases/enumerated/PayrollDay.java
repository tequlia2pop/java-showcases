package com.gmail.tequlia2pop.java.showcases.enumerated;

// 策略枚举模式（strategy enum pattern）。
// 为了简洁起见，这个示例中的代码使用了 double，但是注意 double 并不是适合薪资应用程序的数据类型。

enum PayrollDay {
	MONDAY(PayType.WEEKDAY), //
	TUESDAY(PayType.WEEKDAY), //
	WEDNESDAY(PayType.WEEKDAY), //
	THURSDAY(PayType.WEEKDAY), //
	FRIDAY(PayType.WEEKDAY), //
	SATURDAY(PayType.WEEKEND), //
	SUNDAY(PayType.WEEKEND);

	private final PayType payType;

	PayrollDay(PayType payType) {
		this.payType = payType;
	}

	double pay(double hoursWorked, double payRate) {
		return payType.pay(hoursWorked, payRate);
	}

	// 策略枚举类型
	private enum PayType {
		WEEKDAY {
			double overtimePay(double hours, double payRate) {
				return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			double overtimePay(double hours, double payRate) {
				return hours * payRate / 2;
			}
		};

		private static final int HOURS_PER_SHIFT = 8;

		/**
		 * 计算加班工资。
		 * 
		 * @param hrs
		 * @param payRate
		 * @return
		 */
		abstract double overtimePay(double hrs, double payRate);

		double pay(double hoursWorked, double payRate) {
			double basePay = hoursWorked * payRate;
			return basePay + overtimePay(hoursWorked, payRate);
		}
	}
}
