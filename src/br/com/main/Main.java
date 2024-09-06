package br.com.main;
import br.com.isiCompilator.exceptions.IsiSemanticException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import br.com.isiCompilator.parser.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Código a ser executado
            System.out.println("Hello, World!");

            isiCompilatorLexer lexer;
            isiCompilatorParser parser;

            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\compiler_isi_language\\src\\br\\com\\main\\ErroNumeroParaTexto.isi"));
            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\compiler_isi_language\\src\\br\\com\\main\\ErroTextoParaNumero.isi"));
            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\compiler_isi_language\\src\\br\\com\\main\\ExemploDoc.isi"));
            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\compiler_isi_language\\src\\br\\com\\main\\Repeticao.isi"));
            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\compiler_isi_language\\src\\br\\com\\main\\VariavelDuplicada.isi"));
            lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\isi_compilator\\VariavelNaoUsada.isi"));
            //lexer = new isiCompilatorLexer(CharStreams.fromFileName("C:\\Users\\carlo\\OneDrive\\Desktop\\isi_compilator\\Input.isi"));


            CommonTokenStream tokenStream = new CommonTokenStream(lexer);


            parser = new isiCompilatorParser(tokenStream);

            parser.prog();

            System.out.println("Compilação bem sucedida");

            parser.exibeComandos();

            parser.generateCode();

            parser.gerarWarnings();
        } catch(IsiSemanticException ex){
            System.err.println("Semantic error - " + ex.getMessage());
        }
        catch(Exception ex) {
            ex.printStackTrace();
            System.err.println("ERROR " + ex.getMessage());
        }
    }
}