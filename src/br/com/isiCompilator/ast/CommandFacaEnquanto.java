package br.com.isiCompilator.ast;

import java.util.ArrayList;

public class CommandFacaEnquanto extends AbstractCommand {

    private String condition;
    private ArrayList<AbstractCommand> lista;

    public CommandFacaEnquanto(String condition, ArrayList<AbstractCommand> lista) {
        this.condition = condition;
        this.lista = lista;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();

        sb.append("do {\n");

        for(AbstractCommand v: this.lista)
        {
            sb.append(v.generateJavaCode());
        }

        sb.append("\n}");

        sb.append("while(").append(condition).append(");\n");

        return sb.toString();
    }

}
