package inter.stmt;

import inter.Id;
import inter.expr.Expr;
import lexer.Tag;

public class Assign extends Stmt {
    protected Id id;
    protected Expr expr;

    public Assign(Id i, Expr expr) {
        id = i;
        this.expr = expr;
        addChild(id);
        addChild(expr);
    }

    @Override
    public String toString() {
        return Tag.ASSIGN.toString();
    };

}
