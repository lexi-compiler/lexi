package lexi.frontends.scala.phases

import lexi.frontends.scala.ir.ScalaFileVisitor
import lexi.ir.IrTree
import lexi.{Context, Phase, Source}

import java.io.File
import scala.meta._

class LanguageAnalysis extends Phase {
  def run(context: Context): Unit = {
    context.compilationUnits.foreach { unit =>
//      unit.ast = unit.source.text.parse[scala.meta.Source].get
//      unit.ir = ScalaFileVisitor.visit(unit.ast)
    }
  }
}
