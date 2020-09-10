package br.com.prss.sales.utils;

import java.time.LocalDateTime;

public class DateUtils {

	public static LocalDateTime firstSecondYear(int ano) {
		return LocalDateTime.now()
				.withYear(ano)
				.withMonth(1)
				.withDayOfMonth(1)
				.withHour(00)
				.withMinute(00)
				.withSecond(00);
	}
	
	public static LocalDateTime lastSecondYear(int ano) {
		return LocalDateTime.now()
				.withYear(ano)
				.withMonth(12)
				.withDayOfMonth(31)
				.withHour(23)
				.withMinute(59)
				.withSecond(59);
	}
}
