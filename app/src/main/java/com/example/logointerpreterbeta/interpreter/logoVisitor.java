package com.example.logointerpreterbeta.interpreter;// Generated from logo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link logoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface logoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link logoParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(logoParser.ProgContext ctx);
	
	T visitLine(logoParser.LineContext ctx);

	T visitCmd(logoParser.CmdContext ctx);

	T visitProcedureInvocation(logoParser.ProcedureInvocationContext ctx);

	T visitProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx);

	T visitParameterDeclarations(logoParser.ParameterDeclarationsContext ctx);

	T visitFunc_(logoParser.Func_Context ctx);

	T visitRepeat_(logoParser.Repeat_Context ctx);

	T visitBlock(logoParser.BlockContext ctx);

	T visitIfe(logoParser.IfeContext ctx);

	T visitComparison(logoParser.ComparisonContext ctx);

	T visitComparisonOperator(logoParser.ComparisonOperatorContext ctx);

	T visitMake(logoParser.MakeContext ctx);

	T visitPrint_(logoParser.Print_Context ctx);

	T visitQuotedstring(logoParser.QuotedstringContext ctx);

	T visitName(logoParser.NameContext ctx);

	T visitValue(logoParser.ValueContext ctx);

	T visitSignExpression(logoParser.SignExpressionContext ctx);

	T visitMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx);

	T visitExpression(logoParser.ExpressionContext ctx);

	T visitDeref(logoParser.DerefContext ctx);

	T visitFd(logoParser.FdContext ctx);

	T visitBk(logoParser.BkContext ctx);

	T visitRt(logoParser.RtContext ctx);

	T visitLt(logoParser.LtContext ctx);

	T visitCs(logoParser.CsContext ctx);

	T visitPu(logoParser.PuContext ctx);

	T visitPd(logoParser.PdContext ctx);

	T visitHt(logoParser.HtContext ctx);

	T visitSt(logoParser.StContext ctx);

	T visitHome(logoParser.HomeContext ctx);

	T visitStop(logoParser.StopContext ctx);

	T visitLabel(logoParser.LabelContext ctx);

	T visitSetxy(logoParser.SetxyContext ctx);

	T visitRandom(logoParser.RandomContext ctx);

	T visitFore(logoParser.ForeContext ctx);

	T visitNumber(logoParser.NumberContext ctx);

	T visitComment(logoParser.CommentContext ctx);
}