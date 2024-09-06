package br.com.isiCompilator.ast;

public class CommandEscritaNaLinhaComTexto extends CommandEscritaComTexto {
    public CommandEscritaNaLinhaComTexto(String content) {
        super(content);
    }

    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
        return "System.out.print("+content+");";
    }
}
