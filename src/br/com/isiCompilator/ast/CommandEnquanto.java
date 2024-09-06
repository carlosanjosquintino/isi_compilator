package br.com.isiCompilator.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {

    private String condition;
    private ArrayList<AbstractCommand> lista;

    public CommandEnquanto(String condition, ArrayList<AbstractCommand> lista) {
        this.condition = condition;
        this.lista = lista;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nwhile(").append(condition).append("){\n");

        for(AbstractCommand v: this.lista)
        {
            sb.append(v.generateJavaCode());
        }

        sb.append("\n}");
        return sb.toString();
    }
}
