package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtEquality(
  var comparison: Option[Vector[KtComparison]] = None
) extends ASTNode

object KtEquality extends KotlinParserBaseVisitor[Option[ASTNode] => KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
    new KtEquality {
      parent = parentNode
      context = Some(ctx)
      comparison = Try(
        ctx.comparison.asScala.map(
          KtComparison.visit(_)(Some(this.asInstanceOf[KtEquality]))
        ).toVector
      ).toOption
    }
}
