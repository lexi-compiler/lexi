package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.{CompilationUnit, Context, Phase}

class TypeAnalysis extends Phase {
  def run(context: Context): Unit = {
    val typeInference = new TypeInference
    typeInference.run(context)
  }
}
