package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.frontends.kotlin.transformations.AST._
import lexi.{Phase, Source}
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SyntaxAnalysis extends Phase {
  def apply(source: Source): AST = {
    val lexer = new KotlinLexer(CharStreams.fromString(source.text))
    val parser = new KotlinParser(new CommonTokenStream(lexer))
    KotlinFileContext.visit(parser.kotlinFile).copy(name = Some(source.file))
  }
}
