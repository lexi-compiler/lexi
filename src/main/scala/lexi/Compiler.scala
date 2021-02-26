package lexi

import lexi.frontends.kotlin.phases.LanguageAnalysis
import lexi.ir.IrTree
import lexi.ir.phases.IrAnalysis

class Compiler {
  def phases: Vector[Phase] =
    frontendPhases

  def frontendPhases: Vector[Phase] =
    Vector(new LanguageAnalysis)

  def run(context: Context): Unit = {
    phases.foreach { phase =>
      phase.run(context)
    }
  }
}
