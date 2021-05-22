package com.tvd12.ezydata.example.common;

import com.tvd12.ezyfox.io.EzyDates;

import java.time.LocalDateTime;

import java.time.*;
import java.util.*;

public final class DateConverter {
	
	private DateConverter() {}

	public static Date toDate(LocalDateTime dateTime) {
		return EzyDates.toDate(dateTime);
	}
	
	public static LocalDate toLocalDate(Date date) {
	    return EzyDates.dateToDateTime(date).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return EzyDates.dateToDateTime(date);
	}
}
