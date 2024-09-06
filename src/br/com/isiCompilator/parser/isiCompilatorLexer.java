// Generated from isiCompilator.g4 by ANTLR 4.13.0
package br.com.isiCompilator.parser;

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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class isiCompilatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, PF=15, OP=16, ATTR=17, TEXT=18, 
		ID=19, VIR=20, OPREL=21, ACH=22, FCH=23, NUMBER=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "AP", "FP", "PF", "OP", "ATTR", "TEXT", "ID", 
			"VIR", "OPREL", "ACH", "FCH", "NUMBER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'numero'", "'texto'", 
			"'se'", "'senao'", "'leia'", "'escreva'", "'escrevaNaLinha'", "'enquanto'", 
			"'faca'", "'('", "')'", "'.'", null, "':='", null, null, "','", null, 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "PF", "OP", "ATTR", "TEXT", "ID", "VIR", "OPREL", "ACH", 
			"FCH", "NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public isiCompilatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "isiCompilator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u00ca\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0005\u0011\u009b\b\u0011\n\u0011\f\u0011"+
		"\u009e\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u00a4\b\u0012\n\u0012\f\u0012\u00a7\t\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00b4\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0004\u0017\u00bb\b\u0017\u000b"+
		"\u0017\f\u0017\u00bc\u0001\u0017\u0001\u0017\u0004\u0017\u00c1\b\u0017"+
		"\u000b\u0017\f\u0017\u00c2\u0003\u0017\u00c5\b\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u009c\u0000\u0019\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001"+
		"\u0000\u0006\u0003\u0000*+--//\u0001\u0000az\u0003\u000009AZaz\u0002\u0000"+
		"<<>>\u0001\u000009\u0003\u0000\t\n\r\r  \u00d2\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00013\u0001\u0000\u0000\u0000\u0003<\u0001"+
		"\u0000\u0000\u0000\u0005E\u0001\u0000\u0000\u0000\u0007M\u0001\u0000\u0000"+
		"\u0000\tT\u0001\u0000\u0000\u0000\u000bZ\u0001\u0000\u0000\u0000\r]\u0001"+
		"\u0000\u0000\u0000\u000fc\u0001\u0000\u0000\u0000\u0011h\u0001\u0000\u0000"+
		"\u0000\u0013p\u0001\u0000\u0000\u0000\u0015\u007f\u0001\u0000\u0000\u0000"+
		"\u0017\u0088\u0001\u0000\u0000\u0000\u0019\u008d\u0001\u0000\u0000\u0000"+
		"\u001b\u008f\u0001\u0000\u0000\u0000\u001d\u0091\u0001\u0000\u0000\u0000"+
		"\u001f\u0093\u0001\u0000\u0000\u0000!\u0095\u0001\u0000\u0000\u0000#\u0098"+
		"\u0001\u0000\u0000\u0000%\u00a1\u0001\u0000\u0000\u0000\'\u00a8\u0001"+
		"\u0000\u0000\u0000)\u00b3\u0001\u0000\u0000\u0000+\u00b5\u0001\u0000\u0000"+
		"\u0000-\u00b7\u0001\u0000\u0000\u0000/\u00ba\u0001\u0000\u0000\u00001"+
		"\u00c6\u0001\u0000\u0000\u000034\u0005p\u0000\u000045\u0005r\u0000\u0000"+
		"56\u0005o\u0000\u000067\u0005g\u0000\u000078\u0005r\u0000\u000089\u0005"+
		"a\u0000\u00009:\u0005m\u0000\u0000:;\u0005a\u0000\u0000;\u0002\u0001\u0000"+
		"\u0000\u0000<=\u0005f\u0000\u0000=>\u0005i\u0000\u0000>?\u0005m\u0000"+
		"\u0000?@\u0005p\u0000\u0000@A\u0005r\u0000\u0000AB\u0005o\u0000\u0000"+
		"BC\u0005g\u0000\u0000CD\u0005.\u0000\u0000D\u0004\u0001\u0000\u0000\u0000"+
		"EF\u0005d\u0000\u0000FG\u0005e\u0000\u0000GH\u0005c\u0000\u0000HI\u0005"+
		"l\u0000\u0000IJ\u0005a\u0000\u0000JK\u0005r\u0000\u0000KL\u0005e\u0000"+
		"\u0000L\u0006\u0001\u0000\u0000\u0000MN\u0005n\u0000\u0000NO\u0005u\u0000"+
		"\u0000OP\u0005m\u0000\u0000PQ\u0005e\u0000\u0000QR\u0005r\u0000\u0000"+
		"RS\u0005o\u0000\u0000S\b\u0001\u0000\u0000\u0000TU\u0005t\u0000\u0000"+
		"UV\u0005e\u0000\u0000VW\u0005x\u0000\u0000WX\u0005t\u0000\u0000XY\u0005"+
		"o\u0000\u0000Y\n\u0001\u0000\u0000\u0000Z[\u0005s\u0000\u0000[\\\u0005"+
		"e\u0000\u0000\\\f\u0001\u0000\u0000\u0000]^\u0005s\u0000\u0000^_\u0005"+
		"e\u0000\u0000_`\u0005n\u0000\u0000`a\u0005a\u0000\u0000ab\u0005o\u0000"+
		"\u0000b\u000e\u0001\u0000\u0000\u0000cd\u0005l\u0000\u0000de\u0005e\u0000"+
		"\u0000ef\u0005i\u0000\u0000fg\u0005a\u0000\u0000g\u0010\u0001\u0000\u0000"+
		"\u0000hi\u0005e\u0000\u0000ij\u0005s\u0000\u0000jk\u0005c\u0000\u0000"+
		"kl\u0005r\u0000\u0000lm\u0005e\u0000\u0000mn\u0005v\u0000\u0000no\u0005"+
		"a\u0000\u0000o\u0012\u0001\u0000\u0000\u0000pq\u0005e\u0000\u0000qr\u0005"+
		"s\u0000\u0000rs\u0005c\u0000\u0000st\u0005r\u0000\u0000tu\u0005e\u0000"+
		"\u0000uv\u0005v\u0000\u0000vw\u0005a\u0000\u0000wx\u0005N\u0000\u0000"+
		"xy\u0005a\u0000\u0000yz\u0005L\u0000\u0000z{\u0005i\u0000\u0000{|\u0005"+
		"n\u0000\u0000|}\u0005h\u0000\u0000}~\u0005a\u0000\u0000~\u0014\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0005e\u0000\u0000\u0080\u0081\u0005n\u0000\u0000"+
		"\u0081\u0082\u0005q\u0000\u0000\u0082\u0083\u0005u\u0000\u0000\u0083\u0084"+
		"\u0005a\u0000\u0000\u0084\u0085\u0005n\u0000\u0000\u0085\u0086\u0005t"+
		"\u0000\u0000\u0086\u0087\u0005o\u0000\u0000\u0087\u0016\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005f\u0000\u0000\u0089\u008a\u0005a\u0000\u0000\u008a"+
		"\u008b\u0005c\u0000\u0000\u008b\u008c\u0005a\u0000\u0000\u008c\u0018\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0005(\u0000\u0000\u008e\u001a\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0005)\u0000\u0000\u0090\u001c\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005.\u0000\u0000\u0092\u001e\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0007\u0000\u0000\u0000\u0094 \u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0005:\u0000\u0000\u0096\u0097\u0005=\u0000\u0000\u0097\"\u0001"+
		"\u0000\u0000\u0000\u0098\u009c\u0005\"\u0000\u0000\u0099\u009b\t\u0000"+
		"\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000"+
		"\u0000\u0000\u009f\u00a0\u0005\"\u0000\u0000\u00a0$\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a5\u0007\u0001\u0000\u0000\u00a2\u00a4\u0007\u0002\u0000"+
		"\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a6&\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005,\u0000\u0000\u00a9(\u0001\u0000\u0000\u0000\u00aa\u00b4"+
		"\u0007\u0003\u0000\u0000\u00ab\u00ac\u0005>\u0000\u0000\u00ac\u00b4\u0005"+
		"=\u0000\u0000\u00ad\u00ae\u0005<\u0000\u0000\u00ae\u00b4\u0005=\u0000"+
		"\u0000\u00af\u00b0\u0005=\u0000\u0000\u00b0\u00b4\u0005=\u0000\u0000\u00b1"+
		"\u00b2\u0005!\u0000\u0000\u00b2\u00b4\u0005=\u0000\u0000\u00b3\u00aa\u0001"+
		"\u0000\u0000\u0000\u00b3\u00ab\u0001\u0000\u0000\u0000\u00b3\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b3\u00af\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b4*\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005{\u0000"+
		"\u0000\u00b6,\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005}\u0000\u0000\u00b8"+
		".\u0001\u0000\u0000\u0000\u00b9\u00bb\u0007\u0004\u0000\u0000\u00ba\u00b9"+
		"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00ba"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00c4"+
		"\u0001\u0000\u0000\u0000\u00be\u00c0\u0005.\u0000\u0000\u00bf\u00c1\u0007"+
		"\u0004\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000\u00c4\u00be\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c50\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0007\u0005\u0000\u0000\u00c7\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0006\u0018\u0000\u0000\u00c92\u0001\u0000\u0000"+
		"\u0000\b\u0000\u009c\u00a3\u00a5\u00b3\u00bc\u00c2\u00c4\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}