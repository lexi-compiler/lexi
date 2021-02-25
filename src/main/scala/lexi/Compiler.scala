package lexi

import lexi.frontends.kotlin.phases.LanguageAnalysis
import lexi.ir.IrTree
import lexi.ir.phases.IrAnalysis

class Compiler {
  def phases: List[Phase] =
    frontendPhases ::: Nil

  def frontendPhases: List[Phase] =
    new LanguageAnalysis :: Nil

  def run(context: Context): Unit = {
    phases.foreach { phase =>
      phase.run(context)
    }
  }
}
