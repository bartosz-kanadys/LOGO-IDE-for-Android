package com.example.logointerpreterbeta.interpreter;// Generated from logo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link logoVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class logoBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements logoVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitProg(logoParser.ProgContext ctx) { return visitChildren(ctx); }

	@Override public T visitLine(logoParser.LineContext ctx) { return visitChildren(ctx); }

	@Override public T visitCmd(logoParser.CmdContext ctx) { return visitChildren(ctx); }

	@Override public T visitProcedureInvocation(logoParser.ProcedureInvocationContext ctx) { return visitChildren(ctx); }

	@Override public T visitProcedureDeclaration(logoParser.ProcedureDeclarationContext ctx) { return visitChildren(ctx); }

	@Override public T visitParameterDeclarations(logoParser.ParameterDeclarationsContext ctx) { return visitChildren(ctx); }

	@Override public T visitFunc_(logoParser.Func_Context ctx) { return visitChildren(ctx); }

	@Override public T visitRepeat_(logoParser.Repeat_Context ctx) { return visitChildren(ctx); }

	@Override public T visitBlock(logoParser.BlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitIfe(logoParser.IfeContext ctx) { return visitChildren(ctx); }

	@Override public T visitComparison(logoParser.ComparisonContext ctx) { return visitChildren(ctx); }

	@Override public T visitComparisonOperator(logoParser.ComparisonOperatorContext ctx) { return visitChildren(ctx); }

	@Override public T visitMake(logoParser.MakeContext ctx) { return visitChildren(ctx); }

	@Override public T visitPrint_(logoParser.Print_Context ctx) { return visitChildren(ctx); }

	@Override public T visitQuotedstring(logoParser.QuotedstringContext ctx) { return visitChildren(ctx); }

	@Override public T visitName(logoParser.NameContext ctx) { return visitChildren(ctx); }

	@Override public T visitValue(logoParser.ValueContext ctx) { return visitChildren(ctx); }

	@Override public T visitSignExpression(logoParser.SignExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitMultiplyingExpression(logoParser.MultiplyingExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpression(logoParser.ExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitDeref(logoParser.DerefContext ctx) { return visitChildren(ctx); }

	@Override public T visitFd(logoParser.FdContext ctx) { return visitChildren(ctx); }

	@Override public T visitBk(logoParser.BkContext ctx) { return visitChildren(ctx); }

	@Override public T visitRt(logoParser.RtContext ctx) { return visitChildren(ctx); }

	@Override public T visitLt(logoParser.LtContext ctx) { return visitChildren(ctx); }

	@Override public T visitCs(logoParser.CsContext ctx) { return visitChildren(ctx); }

	@Override public T visitPu(logoParser.PuContext ctx) { return visitChildren(ctx); }

	@Override public T visitPd(logoParser.PdContext ctx) { return visitChildren(ctx); }

	@Override public T visitHt(logoParser.HtContext ctx) { return visitChildren(ctx); }

	@Override public T visitSt(logoParser.StContext ctx) { return visitChildren(ctx); }

	@Override public T visitHome(logoParser.HomeContext ctx) { return visitChildren(ctx); }

	@Override public T visitStop(logoParser.StopContext ctx) { return visitChildren(ctx); }

	@Override public T visitLabel(logoParser.LabelContext ctx) { return visitChildren(ctx); }

	@Override public T visitSetxy(logoParser.SetxyContext ctx) { return visitChildren(ctx); }

	@Override public T visitRandom(logoParser.RandomContext ctx) { return visitChildren(ctx); }

	@Override public T visitFore(logoParser.ForeContext ctx) { return visitChildren(ctx); }

	@Override public T visitNumber(logoParser.NumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitComment(logoParser.CommentContext ctx) { return visitChildren(ctx); }
}