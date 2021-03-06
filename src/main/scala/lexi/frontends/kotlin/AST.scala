package lexi.frontends.kotlin

import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable.ListBuffer

class AST extends lexi.frontends.AST {
  var parent: Option[AST] = None
  var children: ListBuffer[AST] = ListBuffer.empty
  var context: Option[ParserRuleContext] = None
}

case class KtFile() extends AST {
  def name: Option[KtSimpleIdentifier] =
    children.collectFirst { case node: KtSimpleIdentifier => node }
  def topLevelObjects: ListBuffer[KtTopLevelObject] =
    children.collect { case node: KtTopLevelObject => node }
}

case class KtTopLevelObject() extends AST {
  def declaration: Option[KtDeclaration] =
    children.collectFirst { case node: KtDeclaration => node }
}

case class KtSimpleIdentifier(
  var name: String
) extends AST

case class KtClass() extends AST {
  def simpleIdentifier: Option[KtSimpleIdentifier] =
    children.collectFirst { case node: KtSimpleIdentifier => node }
  def primaryConstructor: Option[KtPrimaryConstructor] =
    children.collectFirst { case node: KtPrimaryConstructor => node }
  def classBody: Option[KtClassBody] =
    children.collectFirst { case node: KtClassBody => node }
}

case class KtClassBody() extends AST {
  def declarations: ListBuffer[KtDeclaration] =
    children.collect { case node: KtDeclaration => node }
  def functions: ListBuffer[KtNamedFunction] =
    children.collect { case node: KtNamedFunction => node }
}

case class KtClassParameter(
) extends AST {
  def name: Option[KtSimpleIdentifier] =
    children.collectFirst { case node: KtSimpleIdentifier => node }
  def `type`: Option[KtType] =
    children.collectFirst { case node: KtType => node }
}

case class KtPrimaryConstructor() extends AST {
  def classParameters: ListBuffer[KtClassParameter] =
    children.collect { case node: KtClassParameter => node }
}

case class KtProperty() extends AST {
  def name: Option[KtSimpleIdentifier] =
    children.collectFirst { case node: KtSimpleIdentifier => node }
  def expression: Option[KtExpression] =
    children.collectFirst { case node: KtExpression => node }
  def `type`: Option[KtType] =
    children.collectFirst { case node: KtType => node }
}

case class KtAdditiveExpression() extends AST {
  def multiplicativeExpression: ListBuffer[KtMultiplicativeExpression] =
    children.collect { case node: KtMultiplicativeExpression => node }
}

case class KtAsExpression() extends AST {
  def prefixUnaryExpression: Option[KtPrefixUnaryExpression] =
    children.collectFirst { case node: KtPrefixUnaryExpression => node }
}

case class KtCall() extends AST {
  def function: Option[KtNamedFunction] =
    children.collectFirst { case node: KtNamedFunction => node }
}

case class KtComparison() extends AST {
  def genericCallLikeComparisonContext: ListBuffer[KtGenericCallLikeComparison] =
    children.collect { case node: KtGenericCallLikeComparison => node }
}

case class KtConjunction(
) extends AST {
  def equalities: ListBuffer[KtEquality] =
    children.collect { case node: KtEquality => node }
}

case class KtDisjunction(
) extends AST {
  def conjunctions: ListBuffer[KtConjunction] =
    children.collect { case node: KtConjunction => node }
}

case class KtDeclaration() extends AST {
  def classDeclaration: Option[KtClass] =
    children.collectFirst { case node: KtClass => node }
  def propertyDeclaration: Option[KtProperty] =
    children.collectFirst { case node: KtProperty => node }
  def functionDeclaration: Option[KtNamedFunction] =
    children.collectFirst { case node: KtNamedFunction => node }
}

case class KtElvisExpression() extends AST {
  def infixFunctionCalls: ListBuffer[KtInfixFunctionCall] =
    children.collect { case node: KtInfixFunctionCall => node }
}

case class KtEquality() extends AST {
  def comparison: ListBuffer[KtComparison] =
    children.collect { case node: KtComparison => node }
}

case class KtExpression() extends AST {
  def disjunction: Option[KtDisjunction] =
    children.collectFirst { case node: KtDisjunction => node }
}

case class KtNamedFunction() extends AST {
  def name: Option[KtSimpleIdentifier] =
    children.collectFirst { case node: KtSimpleIdentifier => node }
  def `type`: Option[KtType] =
    children.collectFirst { case node: KtType => node }
  def bodyExpression: Option[KtExpression] =
    children.collectFirst { case node: KtExpression => node }
  def bodyBlockExpression: Option[KtBlockExpression] =
    children.collectFirst { case node: KtBlockExpression => node }
}

case class KtBlockExpression() extends AST {
  def statements: ListBuffer[KtExpression] = ListBuffer.empty
}

case class KtGenericCallLikeComparison() extends AST {
  def infixOperation: Option[KtInfixOperation] =
    children.collectFirst { case node: KtInfixOperation => node }
}

case class KtInfixFunctionCall() extends AST {
  def rangeExpressions: ListBuffer[KtRangeExpression] =
    children.collect { case node: KtRangeExpression => node }
}

case class KtInfixOperation(
) extends AST {
  def elvisExpression: ListBuffer[KtElvisExpression] =
    children.collect { case node: KtElvisExpression => node }
}

case class KtLineStringContent(
  var lineStrText: Option[String] = None
) extends AST

case class KtLineStringLiteral() extends AST {
  def lineStringContent: ListBuffer[KtLineStringContent] =
    children.collect { case node: KtLineStringContent => node }
}

case class KtMultiplicativeExpression() extends AST {
  def asExpression: ListBuffer[KtAsExpression] =
    children.collect { case node: KtAsExpression => node }
}

case class KtPostfixUnaryExpression() extends AST

case class KtPrefixUnaryExpression() extends AST {
  def postfixUnaryExpression: Option[KtPostfixUnaryExpression] =
    children.collectFirst { case node: KtPostfixUnaryExpression => node }
}

case class KtPrimaryExpression(
) extends AST {
  def stringLiteral: Option[KtStringLiteral] =
    children.collectFirst { case node: KtStringLiteral => node }
}

case class KtRangeExpression() extends AST {
  def additiveExpressions: ListBuffer[KtAdditiveExpression] =
    children.collect { case node: KtAdditiveExpression => node }
}

case class KtStatement() extends AST {
  def expression: Option[KtExpression] =
    children.collectFirst { case node: KtExpression => node }
}

case class KtStringLiteral() extends AST {
  def lineStringLiteral: Option[KtLineStringLiteral] =
    children.collectFirst { case node: KtLineStringLiteral => node }
}

case class KtType(
  var name: String
) extends AST
