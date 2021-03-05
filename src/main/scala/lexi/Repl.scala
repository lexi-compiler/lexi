package lexi

import lexi.backends.transformations.Interpreter
import lexi.frontends.kotlin.phases.LanguageAnalysis

object Repl {
  def apply(language: Language): Unit = {
    print("@ ")
    Iterator
      .continually(io.StdIn.readLine)
      .takeWhile(command => command != "quit" && command != null)
      .foreach { line =>
        val result = Interpreter(line, language)
        print(
          if (result.isEmpty) "@ "
          else s"$result\n@ "
        )
      }
  }
}
