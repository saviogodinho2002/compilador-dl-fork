package dl;

import java.io.File;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;
import parser.Parser;

public class DL {
    public static void main(String[] args) {
        Lexer l =  new Lexer(new File("prog.dl"));
		Parser p = new Parser(l);
		p.parse();
		System.out.println("FINALIZADO");
		System.out.println( p.parserTree());
    }
}
