package inter;

import inter.expr.Expr;
import lexer.Tag;
import lexer.Token;

public class Or extends Expr {
    protected Expr expr1;
    protected Expr expr2;
    public Or( Expr e1, Expr e2) {
        super(new Token(Tag.OR,"|"), Tag.BOOL);

        this.expr1 = e1;
        this.expr2 = e2;

        addChild(expr1);
        addChild(expr2);
    }
}
