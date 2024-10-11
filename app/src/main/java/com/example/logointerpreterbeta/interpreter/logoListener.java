package com.example.logointerpreterbeta.interpreter;// Generated from logo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link logoParser}.
 */
public interface logoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link logoParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(logoParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(logoParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(logoParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(logoParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(logoParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(logoParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#procedureInvocation}.
	 * @param ctx the parse tree
	 */
	void enterProcedureInvocation(logoParser.ProcedureInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#procedureInvocation}.
	 * @param ctx the parse tree
	 */
	void exitProcedureInvocation(logoParser.ProcedureInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#parameterDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarations(logoParser.ParameterDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#parameterDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarations(logoParser.ParameterDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#func_}.
	 * @param ctx the parse tree
	 */
	void enterFunc_(logoParser.Func_Context ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#func_}.
	 * @param ctx the parse tree
	 */
	void exitFunc_(logoParser.Func_Context ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#repeat_}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_(logoParser.Repeat_Context ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#repeat_}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_(logoParser.Repeat_Context ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(logoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(logoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#ife}.
	 * @param ctx the parse tree
	 */
	void enterIfe(logoParser.IfeContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#ife}.
	 * @param ctx the parse tree
	 */
	void exitIfe(logoParser.IfeContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(logoParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(logoParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(logoParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(logoParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#make}.
	 * @param ctx the parse tree
	 */
	void enterMake(logoParser.MakeContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#make}.
	 * @param ctx the parse tree
	 */
	void exitMake(logoParser.MakeContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#print_}.
	 * @param ctx the parse tree
	 */
	void enterPrint_(logoParser.Print_Context ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#print_}.
	 * @param ctx the parse tree
	 */
	void exitPrint_(logoParser.Print_Context ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#quotedstring}.
	 * @param ctx the parse tree
	 */
	void enterQuotedstring(logoParser.QuotedstringContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#quotedstring}.
	 * @param ctx the parse tree
	 */
	void exitQuotedstring(logoParser.QuotedstringContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(logoParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(logoParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(logoParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(logoParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#signExpression}.
	 * @param ctx the parse tree
	 */
	void enterSignExpression(logoParser.SignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#signExpression}.
	 * @param ctx the parse tree
	 */
	void exitSignExpression(logoParser.SignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(logoParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(logoParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#deref}.
	 * @param ctx the parse tree
	 */
	void enterDeref(logoParser.DerefContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#deref}.
	 * @param ctx the parse tree
	 */
	void exitDeref(logoParser.DerefContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#fd}.
	 * @param ctx the parse tree
	 */
	void enterFd(logoParser.FdContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#fd}.
	 * @param ctx the parse tree
	 */
	void exitFd(logoParser.FdContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#bk}.
	 * @param ctx the parse tree
	 */
	void enterBk(logoParser.BkContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#bk}.
	 * @param ctx the parse tree
	 */
	void exitBk(logoParser.BkContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#rt}.
	 * @param ctx the parse tree
	 */
	void enterRt(logoParser.RtContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#rt}.
	 * @param ctx the parse tree
	 */
	void exitRt(logoParser.RtContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(logoParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(logoParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#cs}.
	 * @param ctx the parse tree
	 */
	void enterCs(logoParser.CsContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#cs}.
	 * @param ctx the parse tree
	 */
	void exitCs(logoParser.CsContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#pu}.
	 * @param ctx the parse tree
	 */
	void enterPu(logoParser.PuContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#pu}.
	 * @param ctx the parse tree
	 */
	void exitPu(logoParser.PuContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#pd}.
	 * @param ctx the parse tree
	 */
	void enterPd(logoParser.PdContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#pd}.
	 * @param ctx the parse tree
	 */
	void exitPd(logoParser.PdContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#ht}.
	 * @param ctx the parse tree
	 */
	void enterHt(logoParser.HtContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#ht}.
	 * @param ctx the parse tree
	 */
	void exitHt(logoParser.HtContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#st}.
	 * @param ctx the parse tree
	 */
	void enterSt(logoParser.StContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#st}.
	 * @param ctx the parse tree
	 */
	void exitSt(logoParser.StContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#home}.
	 * @param ctx the parse tree
	 */
	void enterHome(logoParser.HomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#home}.
	 * @param ctx the parse tree
	 */
	void exitHome(logoParser.HomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#stop}.
	 * @param ctx the parse tree
	 */
	void enterStop(logoParser.StopContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#stop}.
	 * @param ctx the parse tree
	 */
	void exitStop(logoParser.StopContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(logoParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(logoParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#setxy}.
	 * @param ctx the parse tree
	 */
	void enterSetxy(logoParser.SetxyContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#setxy}.
	 * @param ctx the parse tree
	 */
	void exitSetxy(logoParser.SetxyContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#random}.
	 * @param ctx the parse tree
	 */
	void enterRandom(logoParser.RandomContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#random}.
	 * @param ctx the parse tree
	 */
	void exitRandom(logoParser.RandomContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#fore}.
	 * @param ctx the parse tree
	 */
	void enterFore(logoParser.ForeContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#fore}.
	 * @param ctx the parse tree
	 */
	void exitFore(logoParser.ForeContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(logoParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(logoParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link logoParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(logoParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link logoParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(logoParser.CommentContext ctx);
}