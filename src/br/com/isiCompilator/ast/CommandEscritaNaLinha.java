package br.com.isiCompilator.ast;

public class CommandEscritaNaLinha extends CommandEscrita {
    public CommandEscritaNaLinha(String id) {
        super(id);
    }

    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
        return "System.out.print("+super.id+");";
    }
}
