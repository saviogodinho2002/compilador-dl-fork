package inter;

import inter.expr.Expr;
import lexer.Tag;
import lexer.Token;

public class Rel extends Expr {


    protected Expr expr1;
    protected Expr expr2;
    public Rel(Token op, Expr e1, Expr e2) {
        super(op, Tag.BOOL);

        this.expr1 = e1;
        this.expr2 = e2;

        addChild(expr1);
        addChild(expr2);
    }

}
