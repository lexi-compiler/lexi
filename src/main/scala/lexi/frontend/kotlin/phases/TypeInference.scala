package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser.{
  FunctionDeclarationContext,
  PropertyDeclarationContext
}
import lexi.frontend.kotlin.ast.{ASTNode, KtFunction, KtProperty}

object TypeInference {
  def apply(ast: ASTNode): ASTNode = {
    val IntPattern = "\\d+".r
    val StringPattern = """^".*"$""".r
    ast match {
      case property: KtProperty =>
        val textValue = property.context
          .asInstanceOf[PropertyDeclarationContext]
          .expression
          .getText
        val inferredType = textValue match {
          case IntPattern()    => "Int"
          case StringPattern() => "String"
        }
        property.copy(dataType = inferredType)
//      case function: KtFunction =>
//        function.context.asInstanceOf[FunctionDeclarationContext].`type`
    }
  }
}
