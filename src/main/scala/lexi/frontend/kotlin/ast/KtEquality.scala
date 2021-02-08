package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtEquality(
  var comparison: Option[Vector[KtComparison]] = None
) extends ASTNode

object KtEquality extends KotlinParserBaseVisitor[KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext): KtEquality =
    new KtEquality {
      context = Some(ctx)
      comparison = Try {
        ctx.comparison.asScala.map(KtComparison.visit(_)).toVector
      }.toOption
    }
}
