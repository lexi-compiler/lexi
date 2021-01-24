package lexi.frontend.kotlin.phases

import lexi.frontend.Language
import lexi.frontend.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.{Parser, Visitor}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Parser extends Parser[Language.Kotlin.type] {
  def apply(source: String): KotlinParser =
    new KotlinParser(
      new CommonTokenStream(
        new KotlinLexer(CharStreams.fromString(source))
      )
    )

  def parse(parser: KotlinParser): ASTNode =
    (new Visitor).visit(parser.kotlinFile)
}
