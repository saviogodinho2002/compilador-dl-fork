package inter.stmt;

import inter.expr.Expr;
import lexer.Tag;

public class Where extends Stmt{
    private Expr expr;
    private Stmt stmt;

    public Where(Expr e, Stmt s) {
        expr = e;
        stmt = s;
        addChild(expr);
        addChild(s);
    }

    @Override
    public String toString() {
        return Tag.WHERE.toString();
    }
}
