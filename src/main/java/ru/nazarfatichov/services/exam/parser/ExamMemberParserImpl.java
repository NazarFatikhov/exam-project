package ru.nazarfatichov.services.exam.parser;

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
}
