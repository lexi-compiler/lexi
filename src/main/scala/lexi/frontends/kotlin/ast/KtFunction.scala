package lexi.frontends.kotlin.ast

case class KtFunction(
  var name: String,
  var `type`: String,
  var functionBody: KtFunctionBody
) extends ASTNode
