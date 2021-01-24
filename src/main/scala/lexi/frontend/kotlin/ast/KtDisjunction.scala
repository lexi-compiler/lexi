package lexi.frontend.kotlin.ast

case class KtDisjunction(
  var conjunctions: Vector[KtConjunction] = Vector.empty
) extends ASTNode
