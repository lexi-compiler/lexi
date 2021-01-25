package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtEquality() extends ASTNode

object KtEquality extends KotlinParserBaseVisitor[ASTNode] {
  override def visitEquality(ctx: KotlinParser.EqualityContext): KtEquality =
    new KtEquality() {
      context = ctx
    }
}
