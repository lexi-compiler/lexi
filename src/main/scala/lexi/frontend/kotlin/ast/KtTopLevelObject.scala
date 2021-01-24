package lexi.frontend.kotlin.ast

case class KtTopLevelObject(
  var declaration: KtDeclaration = null
) extends ASTNode
