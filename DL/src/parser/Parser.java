package parser;

import java.util.Hashtable;

import inter.*;
import inter.expr.Bin;
import inter.expr.Expr;
import inter.expr.Id;
import inter.expr.Literal;
import inter.expr.Logical;
import inter.expr.Rel;
import inter.stmt.*;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Parser {
    private Lexer lexer;
    private Token look;
    private Node root;
    private Hashtable<String, Id> table;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        table = new Hashtable<>();
        move();
    }

    public void printTable() {
        for (var key : this.table.keySet()) {
            System.out.println("Id : " + key
                    + "\t\t Tipo : "
                    + table.get(key).type().toString());
        }
    }

    private Token move() {
        Token save = this.look;
        look = lexer.nextToken();
        return save;
    }

    private void error(String s) {
        System.err.println("linha " + Lexer.line() + ": " + s);
        System.exit(1);
    }

    private Token match(Tag t) {
        if (look.tag() == t) {
            return move();
        }
        error("Simbolo inesperado: " + look.lexeme());
        return null;
    }

    public void parse() {
        root = program();
    }

    private Program program() {
        match(Tag.PROGRAM);
        Token tokId = match(Tag.ID);
        Stmt b = block();
        match(Tag.DOT);
        match(Tag.EOF);
        return new Program(tokId, (Block) b);
    }

    private Stmt block() {
        Block b = new Block();
        match(Tag.BEGIN);
        while (look.tag() != Tag.END) {
            b.addStmt(stmt());
            match(Tag.SEMI);
        }
        match(Tag.END);
        return b;
    }

    private Stmt stmt() {
        switch (look.tag()) {
            case BEGIN:
                return block();
            case INT:
            case REAL:
            case BOOL:
                return decl();
            case ID:
                return assign();
            case IF:
                return ifStmt();
            case WRITE:
                return writeStmt();
            case WHERE:
                return whereStmt();
            default:
                error("Comando inválido");
        }
        return null;
    }

    private Stmt decl() {
        Token type = move();
        Token tokId = match(Tag.ID);
        if (table.get(tokId.lexeme()) == null) {
            Id id = new Id(tokId, type.tag());
            table.put(tokId.lexeme(), id);
            return new Decl(id);
        }
        error("Variável '" + tokId.lexeme() + "' já foi declarada");
        return null;
    }

    private Stmt assign() {
        Id id = findId(match(Tag.ID));
        match(Tag.ASSIGN);
        Expr e = expr();
        return new Assign(id, e);
    }

    private Expr expr() {
        Expr e = __rel();
        while (look.tag() == Tag.OR || look.tag() == Tag.AND) {
            Token op = move();
            e = new Logical(op, e, __rel());
        }
        return e;
    }

    /*
     * private void rel() {
     * arith();
     * rel_();
     * }
     * 
     * private void rel_() {
     * switch (look.tag()) {
     * case LT:
     * case LE:
     * case GT:
     * move();
     * arith();
     * rel_();
     * break;
     * default:
     * }
     * }
     */
    private Expr __rel() {
        Expr e = arith();
        while (look.tag() == Tag.LT ||
                look.tag() == Tag.LE ||
                look.tag() == Tag.GT ||
                look.tag() == Tag.NEQUALS ||
                look.tag() == Tag.EQUALS) {
            Token op = move();
            e = new Rel(op, e, arith());
        }
        return e;
    }

    private Expr arith() {
        Expr e = term();
        while (look.tag() == Tag.SUM || look.tag() == Tag.SUB) {
            Token op = move();
            e = new Bin(op, e, term());
        }
        return e;
    }

    private Expr term() {
        Expr e = factor();
        while (look.tag() == Tag.MUL || look.tag() == Tag.DIV) {
            Token op = move();
            e = new Bin(op, e, factor());
        }
        return e;
    }

    private Expr factor() {
        Expr e = null;
        switch (look.tag()) {
            case LPAREN:
                move();
                e = expr();
                match(Tag.RPAREN);
                break;
            case LIT_INT:
                e = new Literal(move(), Tag.INT);
                break;
            case LIT_REAL:
                e = new Literal(move(), Tag.REAL);
                break;
            case TRUE:
            case FALSE:
                e = new Literal(move(), Tag.BOOL);
                break;
            case ID:
                e = findId(match(Tag.ID));
                break;
            default:
                error("Expressão inválida");
        }
        return e;
    }

    private Stmt ifStmt() {
        match(Tag.IF);
        match(Tag.LPAREN);
        Expr e = expr();
        match(Tag.RPAREN);
        Stmt s1 = stmt();
        return new If(e, s1);
    }

    private Stmt whereStmt() {
        match(Tag.WHERE);
        match(Tag.LPAREN);
        Expr e = expr();
        match(Tag.RPAREN);
        Stmt s1 = stmt();
        return new Where(e, s1);
    }

    private Stmt writeStmt() {
        move();
        match(Tag.LPAREN);
        var id = findId(match(Tag.ID));
        match(Tag.RPAREN);
        return new Write(id);
    }

    public String parserTree() {
        return root.str();
    }

    private Id findId(Token tokId) {
        Id id = table.get(tokId.lexeme());
        if (id == null) {
            error("Variável '" + tokId.lexeme() + "' não foi declarada");
        }
        return id;
    }
}