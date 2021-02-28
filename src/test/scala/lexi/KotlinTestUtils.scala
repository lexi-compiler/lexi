package lexi

import lexi.frontends.AST
import lexi.ir.IrTree

object KotlinTestUtils {
  object TestCompiler {
    def runSingleSource(code: String, useAdaptiveLL: Boolean = false): Context = {
      val source = sourceFromString(code)
      val compiler = new Compiler
      val context = new Context {
        phases = compiler.frontendPhases
        compilationUnits = Vector(CompilationUnit(source = source))
        adaptiveLL = useAdaptiveLL
      }
      compiler.run(context)
      context
    }

    def ast(code: String, useAdaptiveLL: Boolean = false): AST =
      runSingleSource(code, useAdaptiveLL = useAdaptiveLL).compilationUnits.head.ast.get

    def ir(code: String, useAdaptiveLL: Boolean = false): IrTree =
      runSingleSource(code, useAdaptiveLL = useAdaptiveLL).compilationUnits.head.ir.get
  }

  def sourceFromString(code: String): Source =
    Source.fromString(code, Language.Kotlin)
}
