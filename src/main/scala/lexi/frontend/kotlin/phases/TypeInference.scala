package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser.{FunctionDeclarationContext, PropertyDeclarationContext}
import lexi.frontend.kotlin.ast.{ASTNode, KtFile, KtFunction, KtProperty}

object TypeInference {
  private val IntPattern = "\\d+".r
  private val StringPattern = """^".*"$""".r

  def apply(ast: ASTNode): ASTNode = ast match
    case file: KtFile => this.file(file)
    case property: KtProperty => this.property(property)
    case function: KtFunction => this.function(function)

  def file(file: KtFile): KtFile =
    file

  def property(property: KtProperty): KtProperty = {
    val textValue = property.context
      .asInstanceOf[PropertyDeclarationContext]
      .expression
      .getText
    val inferredType = textValue match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    property.copy(dataType = Option(inferredType))
  }

  def function(function: KtFunction): KtFunction = {
    val textValue = function.context
      .asInstanceOf[FunctionDeclarationContext]
      .`type`()
      .getText
    val inferredType = textValue match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    function.copy(`type` = Option(inferredType))
    function
  }
}
