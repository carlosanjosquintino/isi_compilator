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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link isiCompilatorParser}.
 */
public interface isiCompilatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(isiCompilatorParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(isiCompilatorParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(isiCompilatorParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(isiCompilatorParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(isiCompilatorParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(isiCompilatorParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(isiCompilatorParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(isiCompilatorParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(isiCompilatorParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(isiCompilatorParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(isiCompilatorParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(isiCompilatorParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void enterCmdselecao(isiCompilatorParser.CmdselecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void exitCmdselecao(isiCompilatorParser.CmdselecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(isiCompilatorParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(isiCompilatorParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(isiCompilatorParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(isiCompilatorParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdescritaNaLinha}.
	 * @param ctx the parse tree
	 */
	void enterCmdescritaNaLinha(isiCompilatorParser.CmdescritaNaLinhaContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdescritaNaLinha}.
	 * @param ctx the parse tree
	 */
	void exitCmdescritaNaLinha(isiCompilatorParser.CmdescritaNaLinhaContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(isiCompilatorParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(isiCompilatorParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(isiCompilatorParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(isiCompilatorParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdFacaEnquanto(isiCompilatorParser.CmdFacaEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdFacaEnquanto(isiCompilatorParser.CmdFacaEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(isiCompilatorParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(isiCompilatorParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link isiCompilatorParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(isiCompilatorParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link isiCompilatorParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(isiCompilatorParser.TermoContext ctx);
}