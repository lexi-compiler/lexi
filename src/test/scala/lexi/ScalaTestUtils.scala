package lexi

import lexi.frontends.AST
import lexi.ir.IrTree

object ScalaTestUtils {
  object TestCompiler {
    def runSingleSource(code: String): Context = {
      val source = sourceFromString(code)
      val compiler = new Compiler
      val context = new Context {
        phases = compiler.frontendPhases
        compilationUnits = List(CompilationUnit(source = source))
      }
      compiler.run(context)
      context
    }

    def ast(code: String): AST =
      runSingleSource(code).compilationUnits.head.ast.get

    def ir(code: String): IrTree =
      runSingleSource(code).compilationUnits.head.ir.get
  }

  def sourceFromString(code: String): Source =
    Source.fromString(code, Language.Scala)
}
