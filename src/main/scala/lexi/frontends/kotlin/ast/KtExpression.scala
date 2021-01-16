package lexi.frontends.kotlin.ast

case class KtExpression(
  var disjunction: KtDisjunction = null
) extends ASTNode
