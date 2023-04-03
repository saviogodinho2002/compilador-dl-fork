package lexer;

public enum Tag {
    // Palavras reservadas
    PROGRAM("PROGRAM"),
    BEGIN("BEGIN"), END("END"),
    

    ASSIGN("ASSIGN"),
    SUM("SUM"),
    SUB("SUB"),
    MUL("MUL"),
    DIV("DIV"),

    LOR("LOR"),
    LAND("LAND"),
    LNOT("LNOT"),

    LT("LT"),
    LE("LE"),
    GT("GT"),
    GE("GE"),
    NE("NE"),
    EQUALS("EQUALS"),

    COMMA("COMMA"),
    SEMI("SEMI"),
    
    EOF("EOF"),
    LPAREN("LPAREN"),
    RPAREN("RPAREN"),

    LIT_INT("LIT_INT"),
    LIT_REAL("LIT_REAL"),
    INT("INT"),
    REAL("REAL"),
    BOOL("BOOL"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    READ("READ"),
    WRITE("WRITE"),
    
    ID("ID"),
    UNK("UNK");

    private String name;

    private Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}