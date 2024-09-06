grammar isiCompilator;

@header{
    import br.com.isiCompilator.dataStructures.IsiSymbol;
    import br.com.isiCompilator.dataStructures.IsiVariable;
    import br.com.isiCompilator.dataStructures.IsiSymbolTable;
    import br.com.isiCompilator.exceptions.IsiSemanticException;
    import br.com.isiCompilator.ast.IsiProgram;
    import br.com.isiCompilator.ast.AbstractCommand;
    import br.com.isiCompilator.ast.CommandLeitura;
    import br.com.isiCompilator.ast.CommandEscrita;
    import br.com.isiCompilator.ast.CommandEscritaNaLinha;
    import br.com.isiCompilator.ast.CommandEscritaNaLinhaComTexto;
    import br.com.isiCompilator.ast.CommandEscritaComTexto;
    import br.com.isiCompilator.ast.CommandAtribuicao;
    import br.com.isiCompilator.ast.CommandDecisao;
    import br.com.isiCompilator.ast.CommandEnquanto;
    import br.com.isiCompilator.ast.CommandFacaEnquanto;

    import java.util.ArrayList;
    import java.util.Stack;
}

@members {
    private int _tipo;
    private String _varName;
    private String _varValue;
    private IsiSymbolTable symbolTable = new IsiSymbolTable();
    private IsiSymbol symbol;
    private IsiProgram program = new IsiProgram();
    private ArrayList<AbstractCommand> curThread;
    private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();

    private String _readId;
    private String _writeId;
    private String _exprId;
    private String _exprContent;
    private String _exprDecision;

    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;

    private int _tipoTermo;

    public void handleInsercaoSimboloNaTabela(String varName)
    {
        _varName = varName;
        _varValue = null;
        symbol = new IsiVariable(_varName, _tipo, _varValue);

        if(!symbolTable.exists(_varName))
        {
            symbolTable.add(symbol);
            System.out.println("Simbolo adicionado " + symbol);
        }
        else
        {
            throw new IsiSemanticException("Simbolo "+_varName+" ja foi declarado");
        }
    }

    public void verificaId(String id){
        if (!symbolTable.exists(id)){
            throw new IsiSemanticException("Simbolo "+id+" não foi declarado");
        }

        IsiVariable variable = (IsiVariable) symbolTable.get(id);
        variable.incrementarVezesUsada();
    }

    public void verificaSeVariavelTemValor(String id){
        IsiVariable var = (IsiVariable) symbolTable.get(id);

        if (var.getValue() == null){
            throw new IsiSemanticException("A variável " + var.getName() + " deve possuir um valor antes de ser usada");
        }
    }

    public void gerarWarnings(){

        ArrayList<IsiSymbol> symbols = symbolTable.getAll();

        for(IsiSymbol symbol: symbols)
        {
            IsiVariable variable = (IsiVariable)symbol;

            if(variable.getVezesUsada() == 0)
            {
                System.out.println("WARNING : A variável " + variable.getName() + " foi declarada mas nunca foi usada.");
            }

            if (variable.getValue() == null){
                System.out.println("WARNING : A variável " + variable.getName() + " não possui valor definido.");
            }
        }
    }

    public void exibeComandos(){
        for (AbstractCommand c: program.getComandos()){
            System.out.println(c);
        }
    }

    public void generateCode(){
        program.generateTarget();
    }
}

prog	: 'programa' decl bloco 'fimprog.'
           {
            program.setVarTable(symbolTable);
            program.setComandos(stack.pop());
           }
		;

decl    :  (declaravar)+
        ;


declaravar :  'declare' tipo ID { handleInsercaoSimboloNaTabela(_input.LT(-1).getText()); }
                        (VIR ID { handleInsercaoSimboloNaTabela(_input.LT(-1).getText()); })*
		                 PF
           ;

tipo       :    'numero'  { _tipo = IsiVariable.NUMBER;  }
           |    'texto'   { _tipo = IsiVariable.TEXT;  }
           ;

bloco	:   { curThread = new ArrayList<AbstractCommand>();
              stack.push(curThread);
            }
            (cmd)+
		;

cmd		:  cmdleitura { System.out.println("Reconheci leitura"); }
        |  cmdescrita { System.out.println("Reconheci escrita"); }
        |  cmdattrib  { System.out.println("Reconheci atribuição"); }
        |  cmdselecao
        |  cmdEnquanto
        |  cmdFacaEnquanto
        |  cmdescritaNaLinha
		;

cmdselecao  :   'se' AP
                     ID  {
                     verificaId(_input.LT(-1).getText());
                     verificaSeVariavelTemValor(_input.LT(-1).getText());
                     _exprDecision = _input.LT(-1).getText();
                     }
                     OPREL { _exprDecision += _input.LT(-1).getText(); }
                     (ID {
                     verificaId(_input.LT(-1).getText());
                     verificaSeVariavelTemValor(_input.LT(-1).getText());
                     } | NUMBER) { _exprDecision += _input.LT(-1).getText(); }
                     FP
                     ACH
                     {
                        curThread = new ArrayList<AbstractCommand>();
                        stack.push(curThread);
                     }
                     (cmd)+
                     FCH {
                        listaTrue = stack.pop();
                     }
                    ('senao'
                    ACH {
                        curThread = new ArrayList<AbstractCommand>();
                        stack.push(curThread);
                    }
                    (cmd+)
                    FCH {
                        listaFalse = stack.pop();
                        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                        stack.peek().add(cmd);
                    }
                    )?
            ;

cmdleitura	: 'leia' AP
                     ID { verificaId(_input.LT(-1).getText());
                          _readId = _input.LT(-1).getText();
                        }
                     FP
                     PF

              {
                IsiVariable var = (IsiVariable) symbolTable.get(_readId);

                var.setValue("");

                CommandLeitura cmd = new CommandLeitura(_readId, var);
                stack.peek().add(cmd);
              }
            ;

cmdescrita	: 'escreva' AP
                        (
                        | TEXT {
                            CommandEscritaComTexto cmd = new CommandEscritaComTexto(_input.LT(-1).getText());
                            stack.peek().add(cmd);
                        }
                        | ID { verificaId(_input.LT(-1).getText());
                               verificaSeVariavelTemValor(_input.LT(-1).getText());
                             _writeId = _input.LT(-1).getText();

                             CommandEscrita cmd = new CommandEscrita(_writeId);
                             stack.peek().add(cmd);
                        }
                        )
                        FP
                        PF
			;

cmdescritaNaLinha	: 'escrevaNaLinha' AP
                        (
                        | TEXT {
                            CommandEscritaNaLinhaComTexto cmd = new CommandEscritaNaLinhaComTexto(_input.LT(-1).getText());
                            stack.peek().add(cmd);
                        }
                        | ID { verificaId(_input.LT(-1).getText());
                               verificaSeVariavelTemValor(_input.LT(-1).getText());
                             _writeId = _input.LT(-1).getText();

                             CommandEscritaNaLinha cmd = new CommandEscritaNaLinha(_writeId);
                             stack.peek().add(cmd);
                        }
                        )
                        FP
                        PF
			;

cmdattrib   : ID { verificaId(_input.LT(-1).getText());
                   _exprId = _input.LT(-1).getText();
                 }
              ATTR { _exprContent = ""; }
              expr
              PF
              {
                CommandAtribuicao cmd = new CommandAtribuicao(_exprId, _exprContent);

                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);
                variable.setValue(_exprContent);

                stack.peek().add(cmd);
              }
            ;

cmdEnquanto  :  'enquanto'
                 AP
                 ID {
                 verificaId(_input.LT(-1).getText());
                 verificaSeVariavelTemValor(_input.LT(-1).getText());
                 _exprDecision = _input.LT(-1).getText(); }
                 OPREL { _exprDecision += _input.LT(-1).getText(); }
                 (ID { verificaId(_input.LT(-1).getText());
                       verificaSeVariavelTemValor(_input.LT(-1).getText()); } | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                 FP
                 ACH
                 {
                   curThread = new ArrayList<AbstractCommand>();
                   ArrayList<AbstractCommand> lista = new ArrayList<AbstractCommand>();
                   stack.push(curThread);
                 }
                 (cmd)+
                 FCH
                 {
                   lista = stack.pop();
                   CommandEnquanto cmd = new CommandEnquanto(_exprDecision, lista);
                   stack.peek().add(cmd);
                 }
                 ;

cmdFacaEnquanto  : 'faca'
                 ACH
                 {
                   curThread = new ArrayList<AbstractCommand>();
                   ArrayList<AbstractCommand> lista = new ArrayList<AbstractCommand>();
                   stack.push(curThread);
                 }
                 (cmd)+
                 FCH
                 'enquanto'
                 AP
                 ID {
                 verificaId(_input.LT(-1).getText());
                 verificaSeVariavelTemValor(_input.LT(-1).getText());
                 _exprDecision = _input.LT(-1).getText(); }
                 OPREL { _exprDecision += _input.LT(-1).getText(); }
                 (ID {
                 verificaId(_input.LT(-1).getText());
                 verificaSeVariavelTemValor(_input.LT(-1).getText());
                 } | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                 FP
                 {
                   lista = stack.pop();
                   CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprDecision, lista);
                   stack.peek().add(cmd);
                 }
                 PF
                  ;


expr        : termo  (
                OP { _exprContent += _input.LT(-1).getText(); }
                termo
                )*
            ;

termo       : ID {  verificaId(_input.LT(-1).getText());
                    verificaSeVariavelTemValor(_input.LT(-1).getText());
                    _exprContent += _input.LT(-1).getText();
                }

            | NUMBER {

                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);

                if (variable.getType() != IsiVariable.NUMBER){
                    throw new IsiSemanticException(_exprId + " não é um número");
                }

                _exprContent += _input.LT(-1).getText();
            }
            | TEXT
              {
                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);

                if (variable.getType() != IsiVariable.TEXT){
                    throw new IsiSemanticException(_exprId + " não é um texto");
                }

                _exprContent += _input.LT(-1).getText();
              }
            ;

AP	: '('
	;

FP	: ')'
	;

PF	: '.'
	;

OP	: '+' | '-' | '*' | '/'
	;

ATTR : ':='
	 ;

TEXT    : ('"'.*?'"')
        ;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

VIR     :  ','
        ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

ACH  : '{'
     ;

FCH  : '}'
     ;

NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;