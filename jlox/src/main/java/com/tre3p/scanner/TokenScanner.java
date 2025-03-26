package com.tre3p.scanner;

import com.tre3p.Lox;
import com.tre3p.scanner.model.Token;
import com.tre3p.scanner.model.TokenType;

import java.util.ArrayList;
import java.util.List;

import static com.tre3p.scanner.model.Keywords.KEYWORDS;
import static com.tre3p.scanner.model.TokenType.*;

public class TokenScanner {
    private final String input;

    private int startPos = 0;
    private int currentPos = 0;
    private int currentLine = 1;
    private final List<Token> tokens = new ArrayList<>();

    public TokenScanner(String input) {
        this.input = input;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            startPos = currentPos;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, currentLine));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            case '!': addToken(match('=') ? BANG_EQUAL : BANG); break;
            case '=': addToken(match('=') ? EQUAL_EQUAL : EQUAL); break;
            case '<': addToken(match('=') ? LESS_EQUAL : LESS); break;
            case '>': addToken(match('=') ? GREATER_EQUAL : GREATER); break;
            case '/':
                if (match('/')) {
                    // A comment goes until the end of the line.
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(SLASH);
                }
                break;

            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            case '\n':
                currentLine++;
                break;
            case '"': string(); break;

            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    Lox.error(currentLine, "Unexpected character: " + c);
                }
                break;
        }
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') currentLine++;
            advance();
        }

        if (isAtEnd()) {
            Lox.error(currentLine, "Unterminated string.");
            return;
        }

        advance();

        String value = input.substring(startPos + 1, currentPos - 1);
        addToken(STRING, value);
    }

    private void number() {
        while (isDigit(peek())) advance();

        if (peek() == '.' && isDigit(peekNext())) {
            advance();

            while (isDigit(peek())) advance();
        }

        addToken(NUMBER,
                Double.parseDouble(input.substring(startPos, currentPos)));
    }

    private char peekNext() {
        if (currentPos + 1 >= input.length()) return '\0';
        return input.charAt(currentPos + 1);
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();

        String text = input.substring(startPos, currentPos);
        TokenType type = KEYWORDS.get(text);
        if (type == null) type = IDENTIFIER;

        addToken(type);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return input.charAt(currentPos);
    }

    private boolean isAtEnd() {
        return currentPos >= input.length();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private char advance() {
        return input.charAt(currentPos++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (input.charAt(currentPos) != expected) return false;

        currentPos++;
        return true;
    }

    private void addToken(TokenType type, Object literal) {
        String text = input.substring(startPos, currentPos);
        tokens.add(new Token(type, text, literal, currentLine));
    }
}
