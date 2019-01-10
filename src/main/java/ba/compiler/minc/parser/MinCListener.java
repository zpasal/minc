// Generated from MinC.g4 by ANTLR 4.5

package ba.compiler.minc.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinCParser}.
 */
public interface MinCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinCParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(MinCParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(MinCParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#declarationItem}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationItem(MinCParser.DeclarationItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#declarationItem}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationItem(MinCParser.DeclarationItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MinCParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MinCParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#varInnerDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarInnerDeclaration(MinCParser.VarInnerDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#varInnerDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarInnerDeclaration(MinCParser.VarInnerDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#dimensions}.
	 * @param ctx the parse tree
	 */
	void enterDimensions(MinCParser.DimensionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#dimensions}.
	 * @param ctx the parse tree
	 */
	void exitDimensions(MinCParser.DimensionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MinCParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MinCParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclaration(MinCParser.FuncDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclaration(MinCParser.FuncDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#paramDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterParamDefinitions(MinCParser.ParamDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#paramDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitParamDefinitions(MinCParser.ParamDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBlock(MinCParser.FunctionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBlock(MinCParser.FunctionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MinCParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MinCParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MinCParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MinCParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(MinCParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(MinCParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#lValue}.
	 * @param ctx the parse tree
	 */
	void enterLValue(MinCParser.LValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#lValue}.
	 * @param ctx the parse tree
	 */
	void exitLValue(MinCParser.LValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#functionCallStatement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallStatement(MinCParser.FunctionCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#functionCallStatement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallStatement(MinCParser.FunctionCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MinCParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MinCParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MinCParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MinCParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MinCParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MinCParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MinCParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MinCParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(MinCParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(MinCParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(MinCParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(MinCParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MinCParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MinCParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerExpression(MinCParser.IntegerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerExpression(MinCParser.IntegerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MinCParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MinCParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinusExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(MinCParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinusExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(MinCParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpression(MinCParser.EqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpression(MinCParser.EqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MinCParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MinCParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionExpression(MinCParser.ExpressionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionExpression(MinCParser.ExpressionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(MinCParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(MinCParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpression(MinCParser.CompExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpression(MinCParser.CompExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MinCParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MinCParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultExpression(MinCParser.MultExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultExpression(MinCParser.MultExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRealExpression(MinCParser.RealExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realExpression}
	 * labeled alternative in {@link MinCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRealExpression(MinCParser.RealExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MinCParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MinCParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinCParser#indexes}.
	 * @param ctx the parse tree
	 */
	void enterIndexes(MinCParser.IndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinCParser#indexes}.
	 * @param ctx the parse tree
	 */
	void exitIndexes(MinCParser.IndexesContext ctx);
}