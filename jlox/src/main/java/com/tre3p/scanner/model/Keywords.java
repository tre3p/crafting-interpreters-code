package com.tre3p.scanner.model;

import java.util.HashMap;
import java.util.Map;

import static com.tre3p.scanner.model.TokenType.*;

public class Keywords {
    public static final Map<String, TokenType> KEYWORDS;

    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("and",    AND);
        KEYWORDS.put("class",  CLASS);
        KEYWORDS.put("else",   ELSE);
        KEYWORDS.put("false",  FALSE);
        KEYWORDS.put("for",    FOR);
        KEYWORDS.put("fun",    FUN);
        KEYWORDS.put("if",     IF);
        KEYWORDS.put("nil",    NIL);
        KEYWORDS.put("or",     OR);
        KEYWORDS.put("print",  PRINT);
        KEYWORDS.put("return", RETURN);
        KEYWORDS.put("super",  SUPER);
        KEYWORDS.put("this",   THIS);
        KEYWORDS.put("true",   TRUE);
        KEYWORDS.put("var",    VAR);
        KEYWORDS.put("while",  WHILE);
    }
}
