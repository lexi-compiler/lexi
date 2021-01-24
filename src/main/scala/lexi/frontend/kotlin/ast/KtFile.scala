package lexi.frontend.kotlin.ast

case class KtFile(
  var topLevelObjects: Vector[KtTopLevelObject] = Vector.empty
) extends ASTNode
