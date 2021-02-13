package lexi

object CLI {
  def main(args: Array[String]): Unit = {
    val sources = sourcesFromFiles(args.toVector)
    val config = new CompilerConfiguration(sources)
    val compiler = new Compiler(config)
    val result = compiler.run
    println(result)
  }

  private def sourcesFromFiles(args: Vector[String]): Vector[Source] =
    args.map { arg =>
      Source.fromFile(arg) match {
        case Right(source) => source
        case Left(exception) => throw exception
      }
    }
}
