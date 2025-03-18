package com.tre3p.scanner.model;

public record Token(
        TokenType type,
        String lexeme,
        Object literal,
        int line
){}