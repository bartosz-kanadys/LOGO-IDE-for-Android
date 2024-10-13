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

	void exitProg(logoParser.ProgContext ctx);

	void enterLine(logoParser.LineContext ctx);

	void exitLine(logoParser.LineContext ctx);

	void enterCmd(logoParser.CmdContext ctx);

	void exitCmd(logoParser.CmdContext ctx);

	void enterProcedureInvocation(logoParser.ProcedureInvocationContext ctx);

	void exitProcedureInvocation(logoParser.ProcedureInvocationContext ctx);

	void enterProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx);

	void exitProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx);

	void enterParameterDeclarations(logoParser.ParameterDeclarationsContext ctx);

	void exitParameterDeclarations(logoParser.ParameterDeclarationsContext ctx);

	void enterFunc_(logoParser.Func_Context ctx);

	void exitFunc_(logoParser.Func_Context ctx);

	void enterRepeat_(logoParser.Repeat_Context ctx);

	void exitRepeat_(logoParser.Repeat_Context ctx);

	void enterBlock(logoParser.BlockContext ctx);

	void exitBlock(logoParser.BlockContext ctx);

	void enterIfe(logoParser.IfeContext ctx);

	void exitIfe(logoParser.IfeContext ctx);

	void enterComparison(logoParser.ComparisonContext ctx);

	void exitComparison(logoParser.ComparisonContext ctx);

	void enterComparisonOperator(logoParser.ComparisonOperatorContext ctx);

	void exitComparisonOperator(logoParser.ComparisonOperatorContext ctx);

	void enterMake(logoParser.MakeContext ctx);

	void exitMake(logoParser.MakeContext ctx);

	void enterPrint_(logoParser.Print_Context ctx);

	void exitPrint_(logoParser.Print_Context ctx);

	void enterQuotedstring(logoParser.QuotedstringContext ctx);

	void exitQuotedstring(logoParser.QuotedstringContext ctx);

	void enterName(logoParser.NameContext ctx);

	void exitName(logoParser.NameContext ctx);

	void enterValue(logoParser.ValueContext ctx);

	void exitValue(logoParser.ValueContext ctx);

	void enterSignExpression(logoParser.SignExpressionContext ctx);

	void exitSignExpression(logoParser.SignExpressionContext ctx);

	void enterMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx);

	void exitMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx);

	void enterExpression(logoParser.ExpressionContext ctx);

	void exitExpression(logoParser.ExpressionContext ctx);

	void enterDeref(logoParser.DerefContext ctx);

	void exitDeref(logoParser.DerefContext ctx);

	void enterFd(logoParser.FdContext ctx);

	void exitFd(logoParser.FdContext ctx);

	void enterBk(logoParser.BkContext ctx);

	void exitBk(logoParser.BkContext ctx);

	void enterRt(logoParser.RtContext ctx);

	void exitRt(logoParser.RtContext ctx);

	void enterLt(logoParser.LtContext ctx);

	void exitLt(logoParser.LtContext ctx);

	void enterCs(logoParser.CsContext ctx);

	void exitCs(logoParser.CsContext ctx);

	void enterPu(logoParser.PuContext ctx);

	void exitPu(logoParser.PuContext ctx);

	void enterPd(logoParser.PdContext ctx);

	void exitPd(logoParser.PdContext ctx);

	void enterHt(logoParser.HtContext ctx);

	void exitHt(logoParser.HtContext ctx);

	void enterSt(logoParser.StContext ctx);

	void exitSt(logoParser.StContext ctx);

	void enterHome(logoParser.HomeContext ctx);

	void exitHome(logoParser.HomeContext ctx);

	void enterStop(logoParser.StopContext ctx);

	void exitStop(logoParser.StopContext ctx);

	void enterLabel(logoParser.LabelContext ctx);

	void exitLabel(logoParser.LabelContext ctx);

	void enterSetxy(logoParser.SetxyContext ctx);

	void exitSetxy(logoParser.SetxyContext ctx);

	void enterRandom(logoParser.RandomContext ctx);

	void exitRandom(logoParser.RandomContext ctx);

	void enterFore(logoParser.ForeContext ctx);

	void exitFore(logoParser.ForeContext ctx);

	void enterNumber(logoParser.NumberContext ctx);

	void exitNumber(logoParser.NumberContext ctx);

	void enterComment(logoParser.CommentContext ctx);

	void exitComment(logoParser.CommentContext ctx);
}