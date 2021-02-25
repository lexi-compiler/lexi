package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.{CompilationUnit, Context, Phase}

class SemanticAnalysis extends Phase {
  def run(context: Context): Unit = {
    val typeAnalysis = new TypeAnalysis
    typeAnalysis.run(context)
  }
}
