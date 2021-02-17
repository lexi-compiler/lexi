package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.antlr.KotlinParser.{FunctionDeclarationContext, PropertyDeclarationContext}
import lexi.frontends.kotlin.ast.{Tree, KtFile, KtFunction, KtProperty}

object TypeInference extends Phase {
  private val IntPattern = "\\d+".r
  private val StringPattern = """^".*"$""".r

  def apply(ast: Tree): Tree = ast match {
    case file: KtFile => this.file(file)
    case property: KtProperty => this.property(property)
    case function: KtFunction => this.function(function)
    case _ => ast
  }

  def file(file: KtFile): KtFile =
    file

  def property(property: KtProperty): KtProperty = {
    val inferredType = property.context.flatMap { ctx =>
      ctx.asInstanceOf[PropertyDeclarationContext].expression.getText match
        case IntPattern()    => Some("Int")
        case StringPattern() => Some("String")
        case _ => None
    }
    property.copy(dataType = inferredType)
  }

  def function(function: KtFunction): KtFunction = {
    val inferredType = function.context.flatMap { ctx =>
      ctx.asInstanceOf[FunctionDeclarationContext].`type`.getText match
        case IntPattern()    => Some("Int")
        case StringPattern() => Some("String")
        case _ => None
    }
    function.copy(`type` = inferredType)
  }
}
