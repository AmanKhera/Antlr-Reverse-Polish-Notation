import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import antlr.*;
public class TestRPN {
    @Test
    public void TestInvalidCharacter(){
        String expression = "x";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);
    }
    @Test
    public void TestEmptyTrigFunction(){
        String expression = "ln";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);
    }
    @Test
    public void TestEmptyTrigFunctionButWithParen(){
        String expression = "ln()";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);
    }

    @Test
    public void TestLogNegativeNumbers(){

        String expression = "log(-8)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression:" + expression);
        System.out.print("Test Negative Numbers with log, output should not compute = ");
        System.out.format("%.5f",value);
        System.out.println();
        System.out.println();
    }
    @Test
    public void TestNegativeNumbersSubtraction(){
        String expression = "-1-2";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression:" + expression);
        System.out.println("Test Negative Numbers Subtraction, output should be (-3.0) = " + value);
        System.out.println();
    }

    @Test
    public void TestNegativeNumbersAddition(){
        String expression = "-1+(-2)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression:" + expression);
        System.out.println("Test Negative Numbers Addition, output should be (-3.0) = " + value);
        System.out.println();
    }

    @Test
    public void TestNegativeNumbersMult(){
        String expression = "-2*(-2)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression:" + expression);
        System.out.println("Test Negative Numbers multiplication, output should be (4.0) = " + value);
        System.out.println();
    }

    @Test
    public void TestNegativeNumbersDivision(){
        String expression = "-2/(-2)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression: " + expression);
        System.out.println("Test Negative Numbers Division, output should be (1.0) = " + value);
        System.out.println();
    }
    @Test
    public void TestCase1(){
        String expression = "(1+2)!/(3-4**2)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression: " + expression);
        System.out.print("Test Case 1, output should be (-0.46154) = ");
        System.out.format("%.5f",value);
        System.out.println();
        System.out.println();
    }

    @Test
    public void TestCase2(){
        String expression = "sin(π/6)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression: " + expression);
        System.out.print("Test Case 2, output should be (0.5) = ");
        System.out.format("%.5f",value);
        System.out.println();
        System.out.println();
    }
    @Test
    public void TestCase3(){
        String expression = "ln(e**3)";
        CharStream charStream = CharStreams.fromString(expression);
        ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

        ParseTree tree = parser.expression();
        ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
        Double value = visitor.visit(tree);
        System.out.println("Expression: " + expression);
        System.out.print("Test Case 3, output should be (3) = " + Math.round(value));
        System.out.println();
    }
}
