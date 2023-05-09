package inter.stmt;

import inter.expr.Expr;
import inter.expr.Id;
import lexer.Tag;

public class Assign extends Stmt {
    protected Id id;
    protected Expr expr;

    public Assign(Id i, Expr expr) {
        id = i;
        this.expr = expr;
        if(i.type() != this.expr.type()){
            error("Em uma atribuição é esperado que o tipo da variável seja igual ao valor que ele irá receber");
        }
        addChild(id);
        addChild(expr);
    }

    @Override
    public String toString() {
        return Tag.ASSIGN.toString();
    };

}
