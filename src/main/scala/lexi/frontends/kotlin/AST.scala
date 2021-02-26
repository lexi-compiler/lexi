package lexi.frontends.kotlin

import org.antlr.v4.runtime.ParserRuleContext

trait AST extends lexi.frontends.AST {
  var parent: Option[AST] = None
  var children: Vector[AST] = Vector.empty
  var context: Option[ParserRuleContext] = None
}

case class KtFile(
  var name: Option[String] = None
) extends AST {
  def topLevelObjects: Vector[KtTopLevelObject] =
    children.collect { case node: KtTopLevelObject => node }
}

case class KtTopLevelObject() extends AST {
  def declaration: Option[KtDeclaration] =
    children.collectFirst { case node: KtDeclaration => node }
}

case class KtClass(
  var name: Option[String] = None,
  var primaryConstructor: Option[KtPrimaryConstructor] = None,
  var classBody: Option[KtClassBody] = None
) extends AST

case class KtClassBody(
  var declarations: Vector[KtDeclaration] = Vector.empty,
  var functions: Vector[KtNamedFunction] = Vector.empty
) extends AST

case class KtClassParameter(
  var name: Option[String] = None,
  var `type`: Option[String] = None
) extends AST

case class KtPrimaryConstructor(
  var classParameters: Vector[KtClassParameter] = Vector.empty
) extends AST

case class KtProperty(
  var name: Option[String] = None,
  var expression: Option[String] = None,
  var dataType: Option[String] = None
) extends AST

case class KtAdditiveExpression(
  var multiplicativeExpression: Vector[KtMultiplicativeExpression] = Vector.empty
) extends AST

case class KtAsExpression(
  var prefixUnaryExpression: Option[KtPrefixUnaryExpression] = None
) extends AST

case class KtCall(
  var function: Option[KtNamedFunction] = None
) extends AST

case class KtComparison(
  var genericCallLikeComparisonContext: Vector[KtGenericCallLikeComparison] = Vector.empty
) extends AST

case class KtConjunction(
  var equalities: Vector[KtEquality] = Vector.empty
) extends AST

case class KtDisjunction(
  var conjunctions: Vector[KtConjunction] = Vector.empty
) extends AST

case class KtDeclaration(
  var classDeclaration: Option[KtClass] = None,
  var propertyDeclaration: Option[KtProperty] = None,
  var functionDeclaration: Option[KtNamedFunction] = None
) extends AST

case class KtElvisExpression(
  var infixFunctionCalls: Vector[KtInfixFunctionCall] = Vector.empty
) extends AST

case class KtEquality(
  var comparison: Vector[KtComparison] = Vector.empty
) extends AST

case class KtExpression(
  var disjunction: Option[KtDisjunction] = None
) extends AST

case class KtNamedFunction(
  var name: Option[String] = None,
  var `type`: Option[String] = None,
  var bodyExpression: Option[KtExpression] = None,
  var bodyBlockExpression: Option[KtBlockExpression] = None
) extends AST

case class KtBlockExpression(
  var statements: Vector[KtExpression] = Vector.empty
) extends AST

case class KtGenericCallLikeComparison(
  var infixOperation: Option[KtInfixOperation] = None
) extends AST

case class KtInfixFunctionCall(
  var rangeExpressions: Vector[KtRangeExpression] = Vector.empty
) extends AST

case class KtInfixOperation(
  var elvisExpression: Vector[KtElvisExpression] = Vector.empty
) extends AST

case class KtLineStringContent(
  var lineStrText: Option[String] = None
) extends AST

case class KtLineStringLiteral(
  var lineStringContent: Vector[KtLineStringContent] = Vector.empty
) extends AST

case class KtMultiplicativeExpression(
  var asExpression: Vector[KtAsExpression] = Vector.empty
) extends AST

case class KtPostfixUnaryExpression(
) extends AST

case class KtPrefixUnaryExpression(
  var postfixUnaryExpression: Option[KtPostfixUnaryExpression] = None
) extends AST

case class KtPrimaryExpression(
  var stringLiteral: Option[KtStringLiteral] = None
) extends AST

case class KtRangeExpression(
  var additiveExpressions: Vector[KtAdditiveExpression] = Vector.empty
) extends AST

case class KtStatement(
  var expression: Option[KtExpression] = None
) extends AST

case class KtStringLiteral(
  var lineStringLiteral: Option[KtLineStringLiteral] = None
) extends AST
