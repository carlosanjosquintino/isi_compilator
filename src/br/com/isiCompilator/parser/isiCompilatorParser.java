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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class isiCompilatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, PF=15, OP=16, ATTR=17, TEXT=18, 
		ID=19, VIR=20, OPREL=21, ACH=22, FCH=23, NUMBER=24, WS=25;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdselecao = 6, RULE_cmdleitura = 7, RULE_cmdescrita = 8, 
		RULE_cmdescritaNaLinha = 9, RULE_cmdattrib = 10, RULE_cmdEnquanto = 11, 
		RULE_cmdFacaEnquanto = 12, RULE_expr = 13, RULE_termo = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdselecao", "cmdleitura", 
			"cmdescrita", "cmdescritaNaLinha", "cmdattrib", "cmdEnquanto", "cmdFacaEnquanto", 
			"expr", "termo"
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

	@Override
	public String getGrammarFileName() { return "isiCompilator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public isiCompilatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
			decl();
			setState(32);
			bloco();
			setState(33);
			match(T__1);

			            program.setVarTable(symbolTable);
			            program.setComandos(stack.pop());
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				declaravar();
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(isiCompilatorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(isiCompilatorParser.ID, i);
		}
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public List<TerminalNode> VIR() { return getTokens(isiCompilatorParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(isiCompilatorParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__2);
			setState(42);
			tipo();
			setState(43);
			match(ID);
			 handleInsercaoSimboloNaTabela(_input.LT(-1).getText()); 
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(45);
				match(VIR);
				setState(46);
				match(ID);
				 handleInsercaoSimboloNaTabela(_input.LT(-1).getText()); 
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				match(T__3);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__4);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>();
			              stack.push(curThread);
			            
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				cmd();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 532288L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdFacaEnquantoContext cmdFacaEnquanto() {
			return getRuleContext(CmdFacaEnquantoContext.class,0);
		}
		public CmdescritaNaLinhaContext cmdescritaNaLinha() {
			return getRuleContext(CmdescritaNaLinhaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				cmdleitura();
				 System.out.println("Reconheci leitura"); 
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				cmdescrita();
				 System.out.println("Reconheci escrita"); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				cmdattrib();
				 System.out.println("Reconheci atribuição"); 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(76);
				cmdselecao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				cmdEnquanto();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 6);
				{
				setState(78);
				cmdFacaEnquanto();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 7);
				{
				setState(79);
				cmdescritaNaLinha();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(isiCompilatorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(isiCompilatorParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(isiCompilatorParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(isiCompilatorParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(isiCompilatorParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(isiCompilatorParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(isiCompilatorParser.FCH, i);
		}
		public TerminalNode NUMBER() { return getToken(isiCompilatorParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__5);
			setState(83);
			match(AP);
			setState(84);
			match(ID);

			                     verificaId(_input.LT(-1).getText());
			                     verificaSeVariavelTemValor(_input.LT(-1).getText());
			                     _exprDecision = _input.LT(-1).getText();
			                     
			setState(86);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(88);
				match(ID);

				                     verificaId(_input.LT(-1).getText());
				                     verificaSeVariavelTemValor(_input.LT(-1).getText());
				                     
				}
				break;
			case NUMBER:
				{
				setState(90);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprDecision += _input.LT(-1).getText(); 
			setState(94);
			match(FP);
			setState(95);
			match(ACH);

			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                     
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				cmd();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 532288L) != 0) );
			setState(102);
			match(FCH);

			                        listaTrue = stack.pop();
			                     
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(104);
				match(T__6);
				setState(105);
				match(ACH);

				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                    
				{
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(107);
					cmd();
					}
					}
					setState(110); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 532288L) != 0) );
				}
				setState(112);
				match(FCH);

				                        listaFalse = stack.pop();
				                        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                        stack.peek().add(cmd);
				                    
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public TerminalNode ID() { return getToken(isiCompilatorParser.ID, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__7);
			setState(118);
			match(AP);
			setState(119);
			match(ID);
			 verificaId(_input.LT(-1).getText());
			                          _readId = _input.LT(-1).getText();
			                        
			setState(121);
			match(FP);
			setState(122);
			match(PF);

			                IsiVariable var = (IsiVariable) symbolTable.get(_readId);

			                var.setValue("");

			                CommandLeitura cmd = new CommandLeitura(_readId, var);
			                stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public TerminalNode TEXT() { return getToken(isiCompilatorParser.TEXT, 0); }
		public TerminalNode ID() { return getToken(isiCompilatorParser.ID, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__8);
			setState(126);
			match(AP);
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FP:
				{
				}
				break;
			case TEXT:
				{
				setState(128);
				match(TEXT);

				                            CommandEscritaComTexto cmd = new CommandEscritaComTexto(_input.LT(-1).getText());
				                            stack.peek().add(cmd);
				                        
				}
				break;
			case ID:
				{
				setState(130);
				match(ID);
				 verificaId(_input.LT(-1).getText());
				                               verificaSeVariavelTemValor(_input.LT(-1).getText());
				                             _writeId = _input.LT(-1).getText();

				                             CommandEscrita cmd = new CommandEscrita(_writeId);
				                             stack.peek().add(cmd);
				                        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(134);
			match(FP);
			setState(135);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdescritaNaLinhaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public TerminalNode TEXT() { return getToken(isiCompilatorParser.TEXT, 0); }
		public TerminalNode ID() { return getToken(isiCompilatorParser.ID, 0); }
		public CmdescritaNaLinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescritaNaLinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdescritaNaLinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdescritaNaLinha(this);
		}
	}

	public final CmdescritaNaLinhaContext cmdescritaNaLinha() throws RecognitionException {
		CmdescritaNaLinhaContext _localctx = new CmdescritaNaLinhaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdescritaNaLinha);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__9);
			setState(138);
			match(AP);
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FP:
				{
				}
				break;
			case TEXT:
				{
				setState(140);
				match(TEXT);

				                            CommandEscritaNaLinhaComTexto cmd = new CommandEscritaNaLinhaComTexto(_input.LT(-1).getText());
				                            stack.peek().add(cmd);
				                        
				}
				break;
			case ID:
				{
				setState(142);
				match(ID);
				 verificaId(_input.LT(-1).getText());
				                               verificaSeVariavelTemValor(_input.LT(-1).getText());
				                             _writeId = _input.LT(-1).getText();

				                             CommandEscritaNaLinha cmd = new CommandEscritaNaLinha(_writeId);
				                             stack.peek().add(cmd);
				                        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(146);
			match(FP);
			setState(147);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(isiCompilatorParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(isiCompilatorParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(ID);
			 verificaId(_input.LT(-1).getText());
			                   _exprId = _input.LT(-1).getText();
			                 
			setState(151);
			match(ATTR);
			 _exprContent = ""; 
			setState(153);
			expr();
			setState(154);
			match(PF);

			                CommandAtribuicao cmd = new CommandAtribuicao(_exprId, _exprContent);

			                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);
			                variable.setValue(_exprContent);

			                stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(isiCompilatorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(isiCompilatorParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(isiCompilatorParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public TerminalNode ACH() { return getToken(isiCompilatorParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(isiCompilatorParser.FCH, 0); }
		public TerminalNode NUMBER() { return getToken(isiCompilatorParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__10);
			setState(158);
			match(AP);
			setState(159);
			match(ID);

			                 verificaId(_input.LT(-1).getText());
			                 verificaSeVariavelTemValor(_input.LT(-1).getText());
			                 _exprDecision = _input.LT(-1).getText(); 
			setState(161);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(163);
				match(ID);
				 verificaId(_input.LT(-1).getText());
				                       verificaSeVariavelTemValor(_input.LT(-1).getText()); 
				}
				break;
			case NUMBER:
				{
				setState(165);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprDecision += _input.LT(-1).getText(); 
			setState(169);
			match(FP);
			setState(170);
			match(ACH);

			                   curThread = new ArrayList<AbstractCommand>();
			                   ArrayList<AbstractCommand> lista = new ArrayList<AbstractCommand>();
			                   stack.push(curThread);
			                 
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(172);
				cmd();
				}
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 532288L) != 0) );
			setState(177);
			match(FCH);

			                   lista = stack.pop();
			                   CommandEnquanto cmd = new CommandEnquanto(_exprDecision, lista);
			                   stack.peek().add(cmd);
			                 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdFacaEnquantoContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(isiCompilatorParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(isiCompilatorParser.FCH, 0); }
		public TerminalNode AP() { return getToken(isiCompilatorParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(isiCompilatorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(isiCompilatorParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(isiCompilatorParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(isiCompilatorParser.FP, 0); }
		public TerminalNode PF() { return getToken(isiCompilatorParser.PF, 0); }
		public TerminalNode NUMBER() { return getToken(isiCompilatorParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdFacaEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFacaEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterCmdFacaEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitCmdFacaEnquanto(this);
		}
	}

	public final CmdFacaEnquantoContext cmdFacaEnquanto() throws RecognitionException {
		CmdFacaEnquantoContext _localctx = new CmdFacaEnquantoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdFacaEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__11);
			setState(181);
			match(ACH);

			                   curThread = new ArrayList<AbstractCommand>();
			                   ArrayList<AbstractCommand> lista = new ArrayList<AbstractCommand>();
			                   stack.push(curThread);
			                 
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183);
				cmd();
				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 532288L) != 0) );
			setState(188);
			match(FCH);
			setState(189);
			match(T__10);
			setState(190);
			match(AP);
			setState(191);
			match(ID);

			                 verificaId(_input.LT(-1).getText());
			                 verificaSeVariavelTemValor(_input.LT(-1).getText());
			                 _exprDecision = _input.LT(-1).getText(); 
			setState(193);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(195);
				match(ID);

				                 verificaId(_input.LT(-1).getText());
				                 verificaSeVariavelTemValor(_input.LT(-1).getText());
				                 
				}
				break;
			case NUMBER:
				{
				setState(197);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprDecision += _input.LT(-1).getText(); 
			setState(201);
			match(FP);

			                   lista = stack.pop();
			                   CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprDecision, lista);
			                   stack.peek().add(cmd);
			                 
			setState(203);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(isiCompilatorParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(isiCompilatorParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			termo();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(206);
				match(OP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(208);
				termo();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(isiCompilatorParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(isiCompilatorParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(isiCompilatorParser.TEXT, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof isiCompilatorListener ) ((isiCompilatorListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(ID);
				  verificaId(_input.LT(-1).getText());
				                    verificaSeVariavelTemValor(_input.LT(-1).getText());
				                    _exprContent += _input.LT(-1).getText();
				                
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(NUMBER);


				                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);

				                if (variable.getType() != IsiVariable.NUMBER){
				                    throw new IsiSemanticException(_exprId + " não é um número");
				                }

				                _exprContent += _input.LT(-1).getText();
				            
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				match(TEXT);

				                IsiVariable variable = (IsiVariable) symbolTable.get(_exprId);

				                if (variable.getType() != IsiVariable.TEXT){
				                    throw new IsiSemanticException(_exprId + " não é um texto");
				                }

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0019\u00df\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004"+
		"\u0001&\b\u0001\u000b\u0001\f\u0001\'\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u00021\b\u0002"+
		"\n\u0002\f\u00024\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003<\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0004\u0004@\b\u0004\u000b\u0004\f\u0004A\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005Q\b"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\\\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006c\b"+
		"\u0006\u000b\u0006\f\u0006d\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0004\u0006m\b\u0006\u000b\u0006\f\u0006"+
		"n\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006t\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0085\b\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u0091\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00a7\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0004\u000b\u00ae\b\u000b\u000b\u000b\f\u000b"+
		"\u00af\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0004\f\u00b9\b\f\u000b\f\f\f\u00ba\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00c7\b\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u00d2\b\r\n\r\f\r\u00d5\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00dd\b\u000e\u0001\u000e\u0000"+
		"\u0000\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u0000\u0000\u00e8\u0000\u001e\u0001\u0000\u0000\u0000"+
		"\u0002%\u0001\u0000\u0000\u0000\u0004)\u0001\u0000\u0000\u0000\u0006;"+
		"\u0001\u0000\u0000\u0000\b=\u0001\u0000\u0000\u0000\nP\u0001\u0000\u0000"+
		"\u0000\fR\u0001\u0000\u0000\u0000\u000eu\u0001\u0000\u0000\u0000\u0010"+
		"}\u0001\u0000\u0000\u0000\u0012\u0089\u0001\u0000\u0000\u0000\u0014\u0095"+
		"\u0001\u0000\u0000\u0000\u0016\u009d\u0001\u0000\u0000\u0000\u0018\u00b4"+
		"\u0001\u0000\u0000\u0000\u001a\u00cd\u0001\u0000\u0000\u0000\u001c\u00dc"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0001\u0000\u0000\u001f \u0003"+
		"\u0002\u0001\u0000 !\u0003\b\u0004\u0000!\"\u0005\u0002\u0000\u0000\""+
		"#\u0006\u0000\uffff\uffff\u0000#\u0001\u0001\u0000\u0000\u0000$&\u0003"+
		"\u0004\u0002\u0000%$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(\u0003\u0001\u0000"+
		"\u0000\u0000)*\u0005\u0003\u0000\u0000*+\u0003\u0006\u0003\u0000+,\u0005"+
		"\u0013\u0000\u0000,2\u0006\u0002\uffff\uffff\u0000-.\u0005\u0014\u0000"+
		"\u0000./\u0005\u0013\u0000\u0000/1\u0006\u0002\uffff\uffff\u00000-\u0001"+
		"\u0000\u0000\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u000035\u0001\u0000\u0000\u000042\u0001\u0000\u0000"+
		"\u000056\u0005\u000f\u0000\u00006\u0005\u0001\u0000\u0000\u000078\u0005"+
		"\u0004\u0000\u00008<\u0006\u0003\uffff\uffff\u00009:\u0005\u0005\u0000"+
		"\u0000:<\u0006\u0003\uffff\uffff\u0000;7\u0001\u0000\u0000\u0000;9\u0001"+
		"\u0000\u0000\u0000<\u0007\u0001\u0000\u0000\u0000=?\u0006\u0004\uffff"+
		"\uffff\u0000>@\u0003\n\u0005\u0000?>\u0001\u0000\u0000\u0000@A\u0001\u0000"+
		"\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\t\u0001"+
		"\u0000\u0000\u0000CD\u0003\u000e\u0007\u0000DE\u0006\u0005\uffff\uffff"+
		"\u0000EQ\u0001\u0000\u0000\u0000FG\u0003\u0010\b\u0000GH\u0006\u0005\uffff"+
		"\uffff\u0000HQ\u0001\u0000\u0000\u0000IJ\u0003\u0014\n\u0000JK\u0006\u0005"+
		"\uffff\uffff\u0000KQ\u0001\u0000\u0000\u0000LQ\u0003\f\u0006\u0000MQ\u0003"+
		"\u0016\u000b\u0000NQ\u0003\u0018\f\u0000OQ\u0003\u0012\t\u0000PC\u0001"+
		"\u0000\u0000\u0000PF\u0001\u0000\u0000\u0000PI\u0001\u0000\u0000\u0000"+
		"PL\u0001\u0000\u0000\u0000PM\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000"+
		"\u0000PO\u0001\u0000\u0000\u0000Q\u000b\u0001\u0000\u0000\u0000RS\u0005"+
		"\u0006\u0000\u0000ST\u0005\r\u0000\u0000TU\u0005\u0013\u0000\u0000UV\u0006"+
		"\u0006\uffff\uffff\u0000VW\u0005\u0015\u0000\u0000W[\u0006\u0006\uffff"+
		"\uffff\u0000XY\u0005\u0013\u0000\u0000Y\\\u0006\u0006\uffff\uffff\u0000"+
		"Z\\\u0005\u0018\u0000\u0000[X\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000]^\u0006\u0006\uffff\uffff\u0000^_\u0005"+
		"\u000e\u0000\u0000_`\u0005\u0016\u0000\u0000`b\u0006\u0006\uffff\uffff"+
		"\u0000ac\u0003\n\u0005\u0000ba\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000"+
		"\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0001\u0000"+
		"\u0000\u0000fg\u0005\u0017\u0000\u0000gs\u0006\u0006\uffff\uffff\u0000"+
		"hi\u0005\u0007\u0000\u0000ij\u0005\u0016\u0000\u0000jl\u0006\u0006\uffff"+
		"\uffff\u0000km\u0003\n\u0005\u0000lk\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000op\u0001"+
		"\u0000\u0000\u0000pq\u0005\u0017\u0000\u0000qr\u0006\u0006\uffff\uffff"+
		"\u0000rt\u0001\u0000\u0000\u0000sh\u0001\u0000\u0000\u0000st\u0001\u0000"+
		"\u0000\u0000t\r\u0001\u0000\u0000\u0000uv\u0005\b\u0000\u0000vw\u0005"+
		"\r\u0000\u0000wx\u0005\u0013\u0000\u0000xy\u0006\u0007\uffff\uffff\u0000"+
		"yz\u0005\u000e\u0000\u0000z{\u0005\u000f\u0000\u0000{|\u0006\u0007\uffff"+
		"\uffff\u0000|\u000f\u0001\u0000\u0000\u0000}~\u0005\t\u0000\u0000~\u0084"+
		"\u0005\r\u0000\u0000\u007f\u0085\u0001\u0000\u0000\u0000\u0080\u0081\u0005"+
		"\u0012\u0000\u0000\u0081\u0085\u0006\b\uffff\uffff\u0000\u0082\u0083\u0005"+
		"\u0013\u0000\u0000\u0083\u0085\u0006\b\uffff\uffff\u0000\u0084\u007f\u0001"+
		"\u0000\u0000\u0000\u0084\u0080\u0001\u0000\u0000\u0000\u0084\u0082\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0005"+
		"\u000e\u0000\u0000\u0087\u0088\u0005\u000f\u0000\u0000\u0088\u0011\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0005\n\u0000\u0000\u008a\u0090\u0005\r"+
		"\u0000\u0000\u008b\u0091\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0012"+
		"\u0000\u0000\u008d\u0091\u0006\t\uffff\uffff\u0000\u008e\u008f\u0005\u0013"+
		"\u0000\u0000\u008f\u0091\u0006\t\uffff\uffff\u0000\u0090\u008b\u0001\u0000"+
		"\u0000\u0000\u0090\u008c\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u000e"+
		"\u0000\u0000\u0093\u0094\u0005\u000f\u0000\u0000\u0094\u0013\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0005\u0013\u0000\u0000\u0096\u0097\u0006\n\uffff"+
		"\uffff\u0000\u0097\u0098\u0005\u0011\u0000\u0000\u0098\u0099\u0006\n\uffff"+
		"\uffff\u0000\u0099\u009a\u0003\u001a\r\u0000\u009a\u009b\u0005\u000f\u0000"+
		"\u0000\u009b\u009c\u0006\n\uffff\uffff\u0000\u009c\u0015\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0005\u000b\u0000\u0000\u009e\u009f\u0005\r\u0000\u0000"+
		"\u009f\u00a0\u0005\u0013\u0000\u0000\u00a0\u00a1\u0006\u000b\uffff\uffff"+
		"\u0000\u00a1\u00a2\u0005\u0015\u0000\u0000\u00a2\u00a6\u0006\u000b\uffff"+
		"\uffff\u0000\u00a3\u00a4\u0005\u0013\u0000\u0000\u00a4\u00a7\u0006\u000b"+
		"\uffff\uffff\u0000\u00a5\u00a7\u0005\u0018\u0000\u0000\u00a6\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0006\u000b\uffff\uffff\u0000\u00a9\u00aa"+
		"\u0005\u000e\u0000\u0000\u00aa\u00ab\u0005\u0016\u0000\u0000\u00ab\u00ad"+
		"\u0006\u000b\uffff\uffff\u0000\u00ac\u00ae\u0003\n\u0005\u0000\u00ad\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00ad"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000\u0000\u00b2\u00b3"+
		"\u0006\u000b\uffff\uffff\u0000\u00b3\u0017\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005\f\u0000\u0000\u00b5\u00b6\u0005\u0016\u0000\u0000\u00b6\u00b8"+
		"\u0006\f\uffff\uffff\u0000\u00b7\u00b9\u0003\n\u0005\u0000\u00b8\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00b8"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u0017\u0000\u0000\u00bd\u00be"+
		"\u0005\u000b\u0000\u0000\u00be\u00bf\u0005\r\u0000\u0000\u00bf\u00c0\u0005"+
		"\u0013\u0000\u0000\u00c0\u00c1\u0006\f\uffff\uffff\u0000\u00c1\u00c2\u0005"+
		"\u0015\u0000\u0000\u00c2\u00c6\u0006\f\uffff\uffff\u0000\u00c3\u00c4\u0005"+
		"\u0013\u0000\u0000\u00c4\u00c7\u0006\f\uffff\uffff\u0000\u00c5\u00c7\u0005"+
		"\u0018\u0000\u0000\u00c6\u00c3\u0001\u0000\u0000\u0000\u00c6\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0006"+
		"\f\uffff\uffff\u0000\u00c9\u00ca\u0005\u000e\u0000\u0000\u00ca\u00cb\u0006"+
		"\f\uffff\uffff\u0000\u00cb\u00cc\u0005\u000f\u0000\u0000\u00cc\u0019\u0001"+
		"\u0000\u0000\u0000\u00cd\u00d3\u0003\u001c\u000e\u0000\u00ce\u00cf\u0005"+
		"\u0010\u0000\u0000\u00cf\u00d0\u0006\r\uffff\uffff\u0000\u00d0\u00d2\u0003"+
		"\u001c\u000e\u0000\u00d1\u00ce\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u001b\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\u0005\u0013\u0000\u0000\u00d7\u00dd\u0006"+
		"\u000e\uffff\uffff\u0000\u00d8\u00d9\u0005\u0018\u0000\u0000\u00d9\u00dd"+
		"\u0006\u000e\uffff\uffff\u0000\u00da\u00db\u0005\u0012\u0000\u0000\u00db"+
		"\u00dd\u0006\u000e\uffff\uffff\u0000\u00dc\u00d6\u0001\u0000\u0000\u0000"+
		"\u00dc\u00d8\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000"+
		"\u00dd\u001d\u0001\u0000\u0000\u0000\u0011\'2;AP[dns\u0084\u0090\u00a6"+
		"\u00af\u00ba\u00c6\u00d3\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}