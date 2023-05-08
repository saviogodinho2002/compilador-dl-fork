package inter.expr;

import inter.Node;
import lexer.Tag;
import lexer.Token;

public abstract class Expr extends Node {
    protected Token op;
    protected Tag type;

    public Expr(Token op,Tag type){
        this.op = op;
        this.type = type;
    }
    public Token getOp(){
        return op;
    }
    public  Tag getType(){
        return type;
    }

    @Override
    public String toString() {
        return op.tag().toString();
    }
}
