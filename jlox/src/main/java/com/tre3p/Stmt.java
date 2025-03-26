package com.tre3p;

public abstract class Stmt {
  public interface Visitor<R> {
    public R visitExpressionStmt(Expression stmt);
    public R visitPrintStmt(Print stmt);
}
  public static class Expression extends Stmt {
    public Expression(Expr expression) {
      this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStmt(this);
    }

    public final Expr expression;
  }
  public static class Print extends Stmt {
    public Print(Expr expression) {
      this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStmt(this);
    }

    public final Expr expression;
  }

  public abstract <R> R accept(Visitor<R> visitor);
}
