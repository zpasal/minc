// Generated from MinC.g4 by ANTLR 4.5

package ba.compiler.minc.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MinCParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(MinCParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#declarationItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationItem(MinCParser.DeclarationItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MinCParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#varInnerDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInnerDeclaration(MinCParser.VarInnerDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MinCParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#dimensions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensions(MinCParser.DimensionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#funcDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclaration(MinCParser.FuncDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#paramDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDefinitions(MinCParser.ParamDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#functionBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBlock(MinCParser.FunctionBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MinCParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MinCParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(MinCParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#lValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValue(MinCParser.LValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#functionCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallStatement(MinCParser.FunctionCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MinCParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MinCParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MinCParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MinCParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpression(MinCParser.BoolExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(MinCParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(MinCParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpression(MinCParser.IntegerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(MinCParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryMinusExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpression(MinCParser.UnaryMinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpression(MinCParser.EqExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(MinCParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionExpression(MinCParser.ExpressionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(MinCParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpression(MinCParser.CompExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(MinCParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpression(MinCParser.MultExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealExpression(MinCParser.RealExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MinCParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinCParser#indexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexes(MinCParser.IndexesContext ctx);
}