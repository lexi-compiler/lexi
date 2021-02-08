package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtEquality(
  var comparison: Vector[KtComparison] = null
) extends ASTNode

object KtEquality extends KotlinParserBaseVisitor[KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext): KtEquality =
    new KtEquality(
      comparison = ctx.comparison.asScala.map(KtComparison.visit(_)).toVector
    ) {
      context = ctx
    }
}
