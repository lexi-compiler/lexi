package lexi

object CLI {
  def main(args: Array[String]): Unit = {
    if (args.head == "-lang") {
      Language.withLangParam(args(args.indexOf("-lang") + 1)) match {
        case Right(lang) => Repl(lang)
        case Left(e) => throw Exception(s"Invalid language: ${args.head}")
      }
    } else {
      val context = new Context
      val compiler = new Compiler(context)
      val sources = sourcesFromFiles(args.toVector)
      val result = compiler.run(sources)
      println(result)
    }
  }

  private def sourcesFromFiles(args: Vector[String]): Vector[Source] =
    args.map { arg =>
      Source.fromFile(arg) match {
        case Right(source)   => source
        case Left(exception) => throw Exception(s"Unable to load source file: ${arg}.")
      }
    }
}
