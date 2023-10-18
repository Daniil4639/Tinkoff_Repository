package edu.hw2;

public class Task1 {

    public sealed interface Expr permits Constant, Negate, Exponent, Addition, Multiplication {
        double evaluate();
    }

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr value1, Expr value2) implements Expr {

        Exponent(Expr value1, double value2) {
            this(value1, new Constant(value2));
        }

        @Override
        public double evaluate() {
            return Math.pow(value1.evaluate(), value2.evaluate());
        }
    }

    public record Addition(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() + value2.evaluate();
        }
    }

    public record Multiplication(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() * value2.evaluate();
        }
    }
}
