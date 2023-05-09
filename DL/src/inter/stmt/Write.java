package inter.stmt;

import inter.expr.Id;
import lexer.Tag;

public class Write extends Stmt {
    private Id id;

    public Write(Id i) {
        id = i;
        if(!i.type().isNum()){
            error("Escrever só pode imprimir o valor de variáveis numéricas");
        }
        addChild(id);
    }

    @Override
    public String toString() {
        return Tag.WRITE.toString();
    }
}
