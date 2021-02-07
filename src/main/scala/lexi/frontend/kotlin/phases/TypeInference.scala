package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser.{FunctionDeclarationContext, PropertyDeclarationContext}
import lexi.frontend.kotlin.ast.{ASTNode, KtFile, KtFunction, KtProperty}

object TypeInference {
  private val IntPattern = "\\d+".r
  private val StringPattern = """^".*"$""".r

  def apply(file: KtFile): KtFile =
    file

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
      .asInstanceOf[FunctionDeclarationContext]
      .`type`()
      .getText
    val inferredType = textValue match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    function.copy(`type` = inferredType)
    function
  }
}
