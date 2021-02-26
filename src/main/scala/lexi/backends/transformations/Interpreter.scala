package lexi.backends.transformations

import lexi.frontends.kotlin.phases.LanguageAnalysis
import lexi.{CompilationUnit, Context, Frontend, Language, Source}
import lexi.ir.{IrFile, IrProperty, IrTree}

object Interpreter {
  def apply(line: String, language: Language): String = {
    val context = new Context {
      phases = Vector(
        Frontend(language)
      )
      compilationUnits = Vector(
        new CompilationUnit(
          source = Source.fromString(line.trim, Language.Kotlin)
        )
      )
    }
    line match {
      case "" => ""
      case expr if expr.trim.startsWith("val") =>
        Frontend(language).run(context)
        context.compilationUnits.head.ir match {
          case Some(ir) => ir.toString
          case None => s"Expression failed to parse: ${expr}"
        }
      case expr => s"Expression failed to parse: ${expr}"
    }
  }

  def apply(ir: IrTree): String = {
    ir match {
      case file: IrFile =>
        apply(file.topLevelObjects.get.head.declaration.get.propertyDeclaration.get)
      case expr: IrProperty => s"Found expression: ${expr.toString}"
    }
  }
}
