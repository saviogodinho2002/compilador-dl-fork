package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Rel extends Expr {

    protected Expr expr1;
    protected Expr expr2;

    public Rel(Token op, Expr e1, Expr e2) {
        super(op, Tag.BOOL);
        if (op.tag() == Tag.EQUALS || op.tag() == Tag.NEQUALS) {
            if (!e1.type().isType() || !e2.type().isType()) {
                error("A igualdade só pode ser aplicada entre operadores lógicos e entre numéricos");
            }
        }
        if(!e1.type().isNum() || !e2.type().isNum()){
            error("As comparações só podem ser aplicadas entre tipos numéricos");
        }
        this.expr1 = e1;
        this.expr2 = e2;

        addChild(expr1);
        addChild(expr2);
    }

}
