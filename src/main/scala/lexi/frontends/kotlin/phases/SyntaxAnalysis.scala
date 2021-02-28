package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontends.kotlin.transformations.AST._
import lexi.{CompilationUnit, Context, Phase, Source, Tree}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

class SyntaxAnalysis extends Phase {
  def run(context: Context): Unit = {
    context.compilationUnits.foreach { unit =>
      val lexer = new KotlinLexer(CharStreams.fromString(unit.source.text))
      val parser = new KotlinParser(new CommonTokenStream(lexer))
      unit.ast = KotlinFileContext.visit(parser.kotlinFile)
    }
  }
}
