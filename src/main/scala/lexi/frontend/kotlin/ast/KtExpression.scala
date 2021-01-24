package lexi.frontend.kotlin.ast

case class KtExpression(
  var disjunction: KtDisjunction = null
) extends ASTNode
