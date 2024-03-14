import antlr.ReverseGrammarBaseVisitor;
import antlr.ReverseGrammarParser;
import java.lang.Math;
import java.util.Stack;

/*
* ReverseGrammarRPN
* Description:Same logic as ReverseGrammarVisitor
* but we are adding each visit into a stack which is printed
* int the main to give us RPN
*
 */
public class ReverseGrammarRPN  extends ReverseGrammarBaseVisitor<String>{

    Stack<String> RPNStack = new Stack<String>();
    public String visitFunc(ReverseGrammarParser.FuncContext ctx)
    {
        String func = ctx.op.getText();
        RPNStack.push(func);
        String arg = visit(ctx.expression());

        return null;
    }

    public String visitAddSub(ReverseGrammarParser.AddSubContext ctx){
        String op = ctx.op.getText();
        RPNStack.push(op);
        String left = visit(ctx.expression(1));
        String right = visit(ctx.expression(0));
        return null;
    }
    public String visitMulDiv(ReverseGrammarParser.MulDivContext ctx){
        String op = ctx.op.getText();
        RPNStack.push(op);
        String left = visit(ctx.expression(1));
        String right = visit(ctx.expression(0));
        return null;
    }

    public String visitPow(ReverseGrammarParser.PowContext ctx){
        RPNStack.push("**");
        String left = visit(ctx.expression(1));
        String right = visit(ctx.expression(0));
        return null;
    }
    public String visitScientific(ReverseGrammarParser.ScientificContext ctx){
        RPNStack.push(String.valueOf(visit(ctx.SCIENTIFIC_NUMBER())));
        return null;
    }

    public String visitNum(ReverseGrammarParser.NumContext ctx){
        String num = ctx.getText();
            RPNStack.push(num);
        return num;
    }



    public String visitFact(ReverseGrammarParser.FactContext ctx){
        RPNStack.push("!");
        visit(ctx.expression());

        return null;
    }

}
