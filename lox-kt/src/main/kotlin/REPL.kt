import scanner.Scanner
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

private const val USAGE_PROMPT = "Usage: ktlox [script]"
private const val PROMPT = "> "

fun main(args: Array<String>) {
    if (args.size > 1) {
        println(USAGE_PROMPT)
        exitProcess(64)
    } else if (args.size == 1) {
        runFile(args.first())
    } else {
        runPrompt()
    }
}

fun run(code: String) =
    Scanner(code)
        .scanTokens()
        .forEach { println(it) }

fun runFile(filePath: String): Unit =
    run(String(Files.readAllBytes(Paths.get(filePath))))

private tailrec fun runPrompt(
    inputReader: InputStreamReader = InputStreamReader(System.`in`),
    bufferedReader: BufferedReader = BufferedReader(inputReader)
) {
    println(PROMPT)

    bufferedReader.readLine().let { line ->
        if (line == null) return
        run(line)
    }

    runPrompt(inputReader, bufferedReader)
}