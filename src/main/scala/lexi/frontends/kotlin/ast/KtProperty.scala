package lexi.frontends.kotlin.ast

case class KtProperty(
  var name: String,
  var expression: String,
  var dataType: String
) extends ASTNode {}
