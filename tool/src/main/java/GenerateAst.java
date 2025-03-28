import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenerateAst {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output_directory>");
            System.exit(64);
        }

        String outDir = args[0];
        defineAst(outDir, "Expr", List.of(
                "Binary   : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal  : Object value",
                "Unary    : Token operator, Expr right",
                "Variable : Token name",
                "Assign : Token name, Expr value"
        ));

        defineAst(outDir, "Stmt", List.of(
                "Expression : Expr expression",
                "Print : Expr expression",
                "Var : Token name, Expr initializer"
        ));
    }

    private static void defineAst(
            String outDir,
            String baseName,
            List<String> types
    ) throws IOException {
        String path = outDir + "/" + baseName + ".java";

        try(PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8)) {
            writer.println("package com.tre3p.lox;");
            writer.println("");
            writer.println("import java.util.List;");
            writer.println();
            writer.println("public abstract class " + baseName + " {");

            defineVisitor(writer, baseName, types);

            for (String type : types) {
                String className = type.split(":")[0].trim();
                String fields = type.split(":")[1].trim();
                defineType(writer, baseName, className, fields);
            }

            writer.println();
            writer.println("  public abstract <R> R accept(Visitor<R> visitor);");

            writer.println("}");
        }
    }

    private static void defineVisitor(
            PrintWriter writer, String baseName, List<String> types
    )  {
        writer.println("  public interface Visitor<R> {");
        types.forEach(type -> {
            String typeName = type.split(":")[0].trim();
            writer.println("    public R visit" + typeName + baseName + "(" +
                    typeName + " " + baseName.toLowerCase() + ");");
        });

        writer.println("}");
    }

    private static void defineType(
            PrintWriter writer, String baseName,
            String className, String fieldList
    ) {
        writer.println("  public static class " + className + " extends " + baseName + " {");
        writer.println("    public " + className + "(" + fieldList + ") {");

        String[] fields = fieldList.split(", ");
        for (String field: fields) {
            String name = field.split(" ")[1];
            writer.println("      this." + name + " = " + name + ";");
        }
        writer.println("    }");

        writer.println();
        writer.println("    @Override");
        writer.println("    public <R> R accept(Visitor<R> visitor) {");
        writer.println("      return visitor.visit" +
                className + baseName + "(this);");
        writer.println("    }");

        writer.println();

        for (String field: fields) {
            writer.println("    public final " + field + ";");
        }

        writer.println("  }");
    }
}
