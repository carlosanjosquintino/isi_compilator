package br.com.isiCompilator.ast;

public class CommandEscritaComTexto extends AbstractCommand {
    protected String content;

    public CommandEscritaComTexto(String content) {
        this.content = content;
    }

    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
        return "System.out.println("+content+");";
    }
    @Override
    public String toString() {
        return "CommandEscrita [id=" + content + "]";
    }
}