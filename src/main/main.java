import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr.*;
/*
*Me and Khalil did collaborate on this project because
* we were in the same group for computer architecture.
* Therefore we had to use our time efficently between
* this project and our other project. This led
* to us on creating our grammar together as well as a few other pieces of code
*
 */
public class main {
    public static void main(String[] args)
    {
        //Grabs User Input
        Scanner UserInput = new Scanner(System.in);
        String expression = null;
        boolean RPNContinue = false;

        //creates while loop which loops until user types 'end'
        while(!RPNContinue) {
            System.out.print("Please enter an expression or type 'end' to exit program:");
            expression = UserInput.nextLine();
            System.out.println();
            if(expression.equals("end")){
                System.out.println("Program has exited! Have a good day!");
                RPNContinue = true;
                continue;
            }
            try {
                //Generates charStream,lexer,tokens, and parser for given expression
                CharStream charStream = CharStreams.fromString(expression);
                ReverseGrammarLexer lexer = new ReverseGrammarLexer(charStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ReverseGrammarParser parser = new ReverseGrammarParser(tokens);

                //removes any errors that maybe caused by inputting characters that are not within the grammar
                parser.removeErrorListeners();
                parser.addErrorListener(new BaseErrorListener() {
                    @Override
                    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                        throw new IllegalArgumentException("Invalid expression at line " + line + " position " + charPositionInLine + ": " + msg, e);
                    }
                });

                // Parse the expression once and store the result
                ParseTree tree = parser.expression();

                // Evaluate the expression
                ReverseGrammarVisitor visitor = new ReverseGrammarVisitor();
                Double value = visitor.visit(tree);
                System.out.println("Infix expression: " + expression);

                ReverseGrammarRPN Rpns = new ReverseGrammarRPN();
                Rpns.visit(tree);

                // Print the RPN expression
                System.out.print("Reverse Polish Notation: ");
                while (!Rpns.RPNStack.isEmpty()) {
                    System.out.print(Rpns.RPNStack.pop() + " ");
                }
                System.out.println();
                double threshold = 1e-5; // your threshold
                //prints either whole number or decimal depending on if the
                //number is below the threshold
                if (Math.abs(value - Math.round(value)) < threshold) {
                    System.out.println("Computation Result: " + Math.round(value));
                }else {
                    System.out.print("Computation Result: ");
                    System.out.format("%.5f",value);
                    System.out.println();
                }
                //catches exceptions and tells the user to input a valid expression
            }catch(Exception e){
                System.out.println("Please input a valid expression");
            }
        }

    }
}
