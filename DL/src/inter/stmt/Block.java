package inter.stmt;

public class Block extends Stmt{
    public Block (){

    }
    public void addStmt(Stmt stmt){
        addChild(stmt);
    }

    public String toString(){
        return "BLOCK";
    }
}
