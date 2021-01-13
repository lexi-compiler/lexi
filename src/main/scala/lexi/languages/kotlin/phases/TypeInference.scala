package lexi.languages.kotlin.phases

import lexi.languages.kotlin.antlr.KotlinParser.PropertyDeclarationContext
import lexi.languages.kotlin.ast.{KtFunction, KtProperty}

object TypeInference extends Phase {
  def apply(property: KtProperty): KtProperty =
    inferPropertyType(property)

  def apply(function: KtFunction): KtFunction =
    inferFunctionType(function)

  private def inferPropertyType(ktProperty: KtProperty): KtProperty = {
    val IntPattern = "\\d+".r
    val StringPattern = """^".*"$""".r
    val inferredType = ktProperty.context
      .asInstanceOf[PropertyDeclarationContext]
      .expression
      .getText match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    ktProperty.copy(dataType = inferredType)
  }

  private def inferFunctionType(ktFunction: KtFunction): KtFunction = {
    val IntPattern = "\\d+".r
    val StringPattern = """^".*"$""".r
    val inferredType = ktFunction.context
      .asInstanceOf[PropertyDeclarationContext]
      .expression
      .getText match {
      case IntPattern()    => "Int"
      case StringPattern() => "String"
    }
    ktFunction.copy(`type` = inferredType)
  }
}
