package inter.expr;

import lexer.Tag;
import lexer.Token;


public class Logical extends Expr{
    protected Expr expr1;
    protected Expr expr2;
    public Logical(Token op, Expr e1, Expr e2) {
        super(op, Tag.BOOL);

        if(!e1.type.isBool() || !e2.type.isBool() ){
            error("Operadores lógicos só podem ser usadas com expressões booleanas.");
        }
        this.expr1 = e1;
        this.expr2 = e2;

        addChild(expr1);
        addChild(expr2);
    }

    
}
