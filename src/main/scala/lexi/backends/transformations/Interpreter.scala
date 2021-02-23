package lexi.backends.transformations

import lexi.frontends.kotlin.phases.LanguageAnalysis
import lexi.{Language, Source}
import lexi.ir.{IrFile, IrProperty, IrTree}

object Interpreter {
  def apply(source: String): String = source match {
    case "" => ""
    case expr if expr.trim.startsWith("val") =>
      s"${apply(LanguageAnalysis(Source.fromString(expr.trim, Language.Kotlin)))}"
    case expr => s"Expression failed to parse: ${expr}"
  }

  def apply(ir: IrTree): String = {
    ir match {
      case file: IrFile =>
        apply(file.topLevelObjects.get.head.declaration.get.propertyDeclaration.get)
      case expr: IrProperty => s"Found expression: ${expr.toString}"
    }
  }
}
