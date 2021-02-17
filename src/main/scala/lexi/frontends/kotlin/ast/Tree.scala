package lexi.frontends.kotlin.ast

import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable.ListBuffer

trait Tree {
  var parent: Option[Tree] = None
  var context: Option[ParserRuleContext] = None
}

trait ASTVisitor {
  def visit(ast: Tree): Tree
}

case class KtFile(
  var name: Option[String] = None,
  var topLevelObjects: Option[Vector[KtTopLevelObject]] = None
) extends Tree

case class KtTopLevelObject(
  var declaration: Option[KtDeclaration] = None
) extends Tree

case class KtClass(
  var name: Option[String] = None,
  var primaryConstructor: Option[KtPrimaryConstructor] = None,
  var classBody: Option[KtClassBody] = None
) extends Tree

case class KtClassBody(
  var classMemberDeclarations: Option[Vector[KtDeclaration]] = None
) extends Tree

case class KtClassParameter(
  var name: Option[String] = None,
  var `type`: Option[String] = None
) extends Tree

case class KtPrimaryConstructor(
  var classParameters: Option[Vector[KtClassParameter]] = None
) extends Tree

case class KtProperty(
  var name: Option[String] = None,
  var expression: Option[String] = None,
  var dataType: Option[String] = None
) extends Tree

case class KtAdditiveExpression(
  var multiplicativeExpressionContext: Option[
    Vector[KtMultiplicativeExpression]
  ] = None
) extends Tree

case class KtAsExpression(
  var prefixUnaryExpression: Option[KtPrefixUnaryExpression] = None
) extends Tree

case class KtBlock(
  var statements: Option[Vector[KtStatement]] = None
) extends Tree

case class KtCall(
  var function: Option[KtFunction] = None
) extends Tree

case class KtComparison(
  var genericCallLikeComparisonContext: Option[
    Vector[KtGenericCallLikeComparison]
  ] = None
) extends Tree

case class KtConjunction(
  var equalities: Option[Vector[KtEquality]] = None
) extends Tree

case class KtDisjunction(
  var conjunctions: Option[Vector[KtConjunction]] = None
) extends Tree

case class KtDeclaration(
  var classDeclaration: Option[KtClass] = None,
  var propertyDeclaration: Option[KtProperty] = None,
  var functionDeclaration: Option[KtFunction] = None
) extends Tree

case class KtElvisExpression(
  var infixFunctionCalls: Option[Vector[KtInfixFunctionCall]] = None
) extends Tree

case class KtEquality(
  var comparison: Option[Vector[KtComparison]] = None
) extends Tree

case class KtExpressionContext(
  var disjunction: Option[KtDisjunction] = None
) extends Tree

case class KtFunction(
  var name: Option[String] = None,
  var `type`: Option[String] = None,
  var functionBody: Option[KtFunctionBody] = None
) extends Tree

case class KtFunctionBody(
  var block: Option[KtBlock] = None,
  var expression: Option[KtExpressionContext] = None
) extends Tree

case class KtGenericCallLikeComparison(
  var infixOperation: Option[KtInfixOperation] = None
) extends Tree

case class KtInfixFunctionCall(
  var rangeExpressions: Option[Vector[KtRangeExpression]] = None
) extends Tree

case class KtInfixOperation(
  var elvisExpression: Option[Vector[KtElvisExpression]] = None
) extends Tree

case class KtLineStringContent(
  var lineStrText: Option[String] = None
) extends Tree

case class KtLineStringLiteral(
  var lineStringContent: Option[Vector[KtLineStringContent]] = None
) extends Tree

case class KtMultiplicativeExpression(
  var asExpression: Option[Vector[KtAsExpression]] = None
) extends Tree

case class KtPostfixUnaryExpression() extends Tree

case class KtPrefixUnaryExpression(
  var postfixUnaryExpression: Option[KtPostfixUnaryExpression] = None
) extends Tree

case class KtPrimaryExpression(
  var stringLiteral: Option[KtStringLiteral] = None
) extends Tree

case class KtRangeExpression(
  var additiveExpressions: Option[Vector[KtAdditiveExpression]] = None
) extends Tree

case class KtStatement(
  var expression: Option[KtExpressionContext] = None
) extends Tree

case class KtStringLiteral(
  var lineStringLiteral: Option[KtLineStringLiteral] = None
) extends Tree
