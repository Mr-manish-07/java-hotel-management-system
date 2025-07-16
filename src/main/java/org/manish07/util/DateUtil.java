package org.manish07.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static long getDaysBetween(LocalDate from, LocalDate to) {

        return ChronoUnit.DAYS.between(from, to); //This works same as below code internally

    }
}
