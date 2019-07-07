package ru.nazarfatichov.services;

import java.text.ParseException;
import java.util.Date;

public interface ExamMemberParser {

    Date parseDate(String dateString) throws ParseException;

    Date parseDateWithHours(String dateString) throws ParseException;

    String getString(Date date);
}
