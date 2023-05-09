package inter.stmt;
import inter.expr.Expr;
import lexer.Tag;
public class If extends Stmt {
    private Expr expr;
    private Stmt stmt;

    public If(Expr e, Stmt s) {
        expr = e;
        stmt = s;
        if(!e.type().isBool()){
            error("No comando 'se' é esperado que é seja do tipo booleano");
        }
        addChild(expr);
        addChild(s);
    }

    @Override
    public String toString() {
        return Tag.IF.toString();
    }
}
