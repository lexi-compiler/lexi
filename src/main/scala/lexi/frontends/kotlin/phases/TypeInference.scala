package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.antlr.KotlinParser.{FunctionDeclarationContext, PropertyDeclarationContext}
import lexi.frontends.kotlin.{AST, KtFile, KtNamedFunction, KtProperty, KtType}
import lexi.{Context, Phase, Tree}

class TypeInference extends Phase {
  private val IntPattern = "\\d+".r
  private val StringPattern = """^".*"$""".r

  def run(context: Context): Unit = {
    context.compilationUnits.foreach { unit =>
      unit.ast = unit.ast.flatMap {
        case file: KtFile              => Some(this.file(file))
        case property: KtProperty      => Some(this.property(property))
        case function: KtNamedFunction => Some(this.function(function))
        case _                         => unit.ast
      }
    }
  }

  def file(file: KtFile): KtFile =
    file

  def property(property: KtProperty): KtProperty = {
    property.context.map { ctx =>
      ctx.asInstanceOf[PropertyDeclarationContext].expression.getText match {
        case IntPattern()    => property.children = property.children :+ KtType("Int")
        case StringPattern() => property.children = property.children :+ KtType("String")
      }
    }
    property
  }

  def function(function: KtNamedFunction): KtNamedFunction = {
    val inferredType = function.context.flatMap { ctx =>
      ctx.asInstanceOf[FunctionDeclarationContext].`type`.getText match {
        case IntPattern()    => Some("Int")
        case StringPattern() => Some("String")
        case _               => None
      }
    }
    function.copy(`type` = inferredType)
  }
}
