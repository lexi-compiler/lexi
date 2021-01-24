package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser
import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.phases.Phase._
import lexi.frontend.kotlin.{Parser, Visitor}

object Parser extends Parser {
  def apply(source: String): KotlinParser =
    (lexicalAnalysis
      andThen syntaxAnalysis)(source)

  def parse(parser: KotlinParser): ASTNode =
    (new Visitor).visit(parser.kotlinFile)
}
