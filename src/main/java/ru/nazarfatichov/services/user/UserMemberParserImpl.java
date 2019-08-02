package ru.nazarfatichov.services.user;

import org.springframework.stereotype.Service;

@Service
public class UserMemberParserImpl implements UserMemberParser {

    @Override
    public String getUserName(String string) {
        return string.substring(0, string.indexOf(" "));
    }

    @Override
    public String getUserSurname(String string) {
        return string.substring(string.indexOf(" ") + 1, string.length());
    }
}
