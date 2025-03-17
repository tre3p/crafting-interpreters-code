package scanner

class Scanner(
    private val source: String
) {
    private val startPos = 0
    private var currentPos = 0
    private var currentLine = 1

    tailrec fun scanTokens(
        accumulated: List<Token> = emptyList<>()
    ): List<Token> {
        if (isAtEnd()) return accumulated

        val tokens = accumulated + when (next()) {
            '(' -> computeToken(TokenType.LEFT_PAREN)
            ')' -> computeToken(TokenType.RIGHT_PAREN)
            '{' -> computeToken(TokenType.LEFT_BRACE)
            '}' -> computeToken(TokenType.RIGHT_BRACE)
            ',' -> computeToken(TokenType.COMMA)
            '.' -> computeToken(TokenType.DOT)
            '-' -> computeToken(TokenType.MINUS)
            '+' -> computeToken(TokenType.PLUS)
            ';' -> computeToken(TokenType.SEMICOLON)
            '*' -> computeToken(TokenType.STAR)
            else -> computeToken(TokenType.EOF) // todo throw error or smthn
        }

        return scanTokens(tokens)
    }

    private fun computeToken(type: TokenType, literal: Any? = null) =
        Token(
            type = type,
            lexeme = source.substring(startPos, currentPos),
            literal = literal,
            line = currentLine
        )

    private fun next(): Char = source[currentPos++]
    private fun isAtEnd() = currentPos >= source.length
}