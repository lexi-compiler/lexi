package lexi

import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.phases.{Ir, Parser, TypeAnalysis}

trait Phase

object Phase {
  implicit class PhaseTransformer(f: (Phase) => Phase) {
    def phase(g: (Phase) => Phase): (Phase) => Phase = f andThen g
  }

  val parse = (source: String) => Parser.parse(Parser(source))
  val analysis = (ast: ASTNode) => TypeAnalysis(ast)
  val ir = (ast: ASTNode) => Ir(ast)
}
