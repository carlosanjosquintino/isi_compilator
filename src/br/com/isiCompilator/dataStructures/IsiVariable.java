package br.com.isiCompilator.dataStructures;

public class IsiVariable extends IsiSymbol {

    public static final int NUMBER=0;
    public static final int TEXT = 1;

    private int type;
    private String value;

    private int vezesUsada = 0;

    public IsiVariable(String name, int type, String value) {
        super(name);
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String generateJavaCode() {
        String str;
        if (type == NUMBER) {
            str = "double ";
        }
        else {
            str = "String ";
        }
        return str + " "+super.name+";";
    }

    @Override
    public String toString(){
        return "Variavel: " + name + ", tipo = " + this.type + ", valor = " + this.value ;
    }

    public int getVezesUsada() {
        return vezesUsada;
    }

    public void incrementarVezesUsada() {
        this.vezesUsada = this.vezesUsada + 1;
    }
}
