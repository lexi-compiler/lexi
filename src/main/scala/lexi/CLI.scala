package lexi

import scala.util.Try

object CLI {
  def main(args: Array[String]): Unit = {
    val opts = CompilerOptions.fromCLI(args)
    val compiler = new Compiler(opts)
    val source = args.last
    val result = compiler.run(source)
    println(result)
  }
}
