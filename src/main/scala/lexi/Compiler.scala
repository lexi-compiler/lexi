package lexi

import lexi.frontends.kotlin.phases.Parse

object Compiler {
  def compile(source: String): Unit = {
    val ast = Parse(source)
  }
}
