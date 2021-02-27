package lexi

import lexi.frontends.kotlin.phases.LanguageAnalysis

object CLI {
  def main(args: Array[String]): Unit = {
    if (args.head == "-lang") {
      Language.withLangParam(args(args.indexOf("-lang") + 1)) match {
        case Right(lang) => Repl(lang)
        case Left(e)     => throw Exception(s"Invalid language: ${args.head}")
      }
    } else {
      val context = new Context {
        compilationUnits = compilationUnitsFromFiles(args.toVector)
      }
      val compiler = new Compiler
      compiler.run(context)
      println(context.compilationUnits)
    }
  }

  private def compilationUnitsFromFiles(args: Vector[String]): Vector[CompilationUnit] =
    args.map { arg =>
      new CompilationUnit(
        source = Source.fromFile(arg) match {
          case Right(source)   => source
          case Left(exception) => throw Exception(s"Unable to load source file: ${arg}.")
        }
      )
    }
}
