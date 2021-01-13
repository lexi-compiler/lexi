package lexi.languages.kotlin.phases

import lexi.languages.kotlin.Parser
import lexi.languages.kotlin.ast.ASTNode

object Parsing extends Phase {
  def apply(source: String): ASTNode =
    Parser(source)
}
