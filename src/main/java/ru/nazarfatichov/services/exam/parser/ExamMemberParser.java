package ru.nazarfatichov.services.exam.parser;

import java.text.ParseException;
import java.util.Date;

public interface ExamMemberParser {

    Date parseDate(String dateString) throws ParseException;
}
