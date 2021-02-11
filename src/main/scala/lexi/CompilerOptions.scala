package lexi

import scala.util.Try

case class CompilerOptions(
  val language: Option[Language] = None
)

object CompilerOptions {
  def fromCLI(args: Array[String]) =
    new CompilerOptions(
      language = Try(Language.valueOf(args(args.toVector.indexOf("-lang") + 1).capitalize)).toOption
    )
}
