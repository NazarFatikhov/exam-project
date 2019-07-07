package ru.nazarfatichov.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class ExamMemberParserImpl implements ExamMemberParser {

    @Override
    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("YYYY-mm-dd");
        Date date = simpleFormatter.parse(dateString);
        return date;
    }

    @Override
    public Date parseDateWithHours(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date = format.parse(dateString);
        return date;
    }

    @Override
    public String getString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        String dateStr = format.format(date);
        return dateStr;
    }
}
