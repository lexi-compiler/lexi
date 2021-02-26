package lexi.frontends.kotlin.phases

import lexi.ir.IrTree
import lexi.{CompilationUnit, Context, Phase, Source}

class LanguageAnalysis extends Phase {
  def phases: List[Phase] = List(
    new SyntaxAnalysis,
    new SemanticAnalysis,
    new Ir
  )

  def run(context: Context): Unit =
    phases.foreach { phase =>
      phase.run(context)
    }
}
