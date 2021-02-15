package lexi

object CLI {
  def main(args: Array[String]): Unit = {
    val compiler = new Compiler
    val sources = sourcesFromFiles(args.toVector)
    val result = compiler.run(sources)
    println(result)
  }

  private def sourcesFromFiles(args: Vector[String]): Vector[Source] =
    args.map { arg =>
      Source.fromFile(arg) match {
        case Right(source)   => source
        case Left(exception) => throw exception
      }
    }
}
