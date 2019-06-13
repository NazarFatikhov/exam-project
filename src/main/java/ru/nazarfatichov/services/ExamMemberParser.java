package ru.nazarfatichov.services;

import ru.nazarfatichov.forms.ExamForm;

import java.text.ParseException;
import java.util.Date;

public interface ExamMemberParser {

    Date parseDate(String dateString) throws ParseException;
}
