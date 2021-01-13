package lexi.languages.kotlin.ast

case class KtConjunction(
  var equalities: Vector[KtEquality] = Vector.empty
) extends ASTNode
