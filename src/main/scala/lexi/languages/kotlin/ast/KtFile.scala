package lexi.languages.kotlin.ast

case class KtFile(
    var topLevelObjects: Vector[KtTopLevelObject] = Vector.empty
) extends ASTNode
