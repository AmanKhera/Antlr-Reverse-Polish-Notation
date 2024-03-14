import antlr.ReverseGrammarBaseVisitor;
import antlr.ReverseGrammarParser;
import java.lang.Math;
public class ReverseGrammarVisitor extends ReverseGrammarBaseVisitor<Double> {

    /*
    * visitFunc
    * @param ctx: Gives us access to the trig/log functions
    * Uses Switch Statement to determine which function has been called
     */
    public Double visitFunc(ReverseGrammarParser.FuncContext ctx){
        Double value = visit(ctx.expression());
        String func = ctx.op.getText();
        switch(func){
            case "log":
                return Math.log10(value);
            case "ln":
                return Math.log(value);
            case "sin":
                return Math.sin(value);
            case "cos":
                return Math.cos(value);
            case "tan":
                return Math.tan(value);
            case "asin":
                return Math.asin(value);
            case "acos":
                return Math.acos(value);
            case "sinh":
                return Math.sinh(value);
            case "cosh":
                return Math.cosh(value);
            case "tanh":
                return Math.tanh(value);
            default:
                throw new RuntimeException("Unknown function: " + func);
        }
    }
    /*
     * visitAddSub
     * @param ctx: Gives us access to the add/sub functions
     * Grabs the left expression and right expression and either adds or subtracts
     * depending on ctx.op.getType()
     */
    public Double visitAddSub(ReverseGrammarParser.AddSubContext ctx){
        Double left = visit(ctx.expression(0));
        Double right = visit(ctx.expression(1));
        if(ctx.op.getType() == ReverseGrammarParser.PLUS) return left + right;
        return left - right;
    }

    /*
     * visitMulDiv
     * @param ctx: Gives us access to the Multiply and Divide functions
     * Grabs the left expression and right expression and either multiply or division
     * depending on ctx.op.getType()
     */
    public Double visitMulDiv(ReverseGrammarParser.MulDivContext ctx){
        Double left = visit(ctx.expression(0));
        Double right = visit(ctx.expression(1));
        if(ctx.op.getType() == ReverseGrammarParser.TIMES) return left * right;
        return left/right;
    }

    /*
     * visitPow
     * @param ctx: Gives us access to the Pow functions
     * Grabs the left expression and right expression and apply's Math.pow
     */
    public Double visitPow(ReverseGrammarParser.PowContext ctx){
        Double left = visit(ctx.expression(0));
        Double right = visit(ctx.expression(1));
        return Math.pow(left,right);
    }

    /*
     * visitScientific
     * @param ctx: Gives us access to the scientific Number
     * Grabs scientific function and uses switch statement to determine which
     * scientific number was called
     */
    public Double visitScientific(ReverseGrammarParser.ScientificContext ctx){
        String value = String.valueOf(visit(ctx.SCIENTIFIC_NUMBER()));
        switch(value){
            case "pi":
                return Math.PI;
            case "e":
                return Math.E;
            default:
                throw new RuntimeException("Unknown function: " + value);
        }


    }
    /*
     * visitNum
     * @param ctx: Gives us access to the Num functions
     * Grabs Num function and uses switch statement to determine which
     * scientific number was called
     */
    public Double visitNum(ReverseGrammarParser.NumContext ctx)
    {
        if((ctx.atom().getText().equals("e")))
        {
            return Math.exp(1);
        } else if ((ctx.atom().getText().equals("π")))
        {
            return Math.PI;
        }
        return Double.valueOf(ctx.atom().getText());
    }

    /*
     * visitParen
     * @param ctx: Gives us access to the Paren functions
     * returns expression within the parenthesis
     */
    public Double visitParen(ReverseGrammarParser.ParenContext ctx) {
        return visit(ctx.expression());
    }

    /*
     * visitFact
     * @param ctx: Gives us access to the Factorial functions
     * Grabs value and uses for loop until it reaches the value
     * returns factorial of given number
     */
    public Double visitFact(ReverseGrammarParser.FactContext ctx) {
        Double value = visit(ctx.expression());
        if (value == 0) {
            return 1.0;
        } else {
            Double result = 1.0;
            for (int i = 1; i <= value; i++) {
                result *= i;
            }
            return result;
        }
    }

    /*
     * visitNegation
     * @param ctx: Gives us access to Negation
     * Grabs value and returns its negative value
     */
    public Double visitNegation(ReverseGrammarParser.NegationContext ctx){
        Double value = visit(ctx.right);

        // Negate the value and return it
        return -value;
    }









}
