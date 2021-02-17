package lexi.frontends.kotlin.phases

import lexi.{Phase, Source}
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontends.kotlin.ast.{Tree, KtFileVisitor}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SyntaxAnalysis extends Phase:
  def apply(source: Source): Tree = {
    val lexer = new KotlinLexer(CharStreams.fromString(source.text))
    val parser = new KotlinParser(new CommonTokenStream(lexer))
    KtFileVisitor.visit(parser.kotlinFile).copy(name = Some(source.file))
  }
