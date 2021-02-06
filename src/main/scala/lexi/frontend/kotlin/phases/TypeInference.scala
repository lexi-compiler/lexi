package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser.{
  FunctionDeclarationContext,
  PropertyDeclarationContext
}
import lexi.frontend.kotlin.ast.{ASTNode, KtFunction, KtProperty}

object TypeInference {
  private val IntPattern = "\\d+".r
  private val StringPattern = """^".*"$""".r

  def apply(property: KtProperty): KtProperty = {
    val textValue = property.context
      .asInstanceOf[PropertyDeclarationContext]
      .expression
      .getText
    val inferredType = textValue match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    property.copy(dataType = inferredType)
  }

  def apply(function: KtFunction): KtFunction = {
    val textValue = function.context
      .asInstanceOf[PropertyDeclarationContext]
      .expression
      .getText
    val inferredType = textValue match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    function.copy(`type` = inferredType)
  }
}
