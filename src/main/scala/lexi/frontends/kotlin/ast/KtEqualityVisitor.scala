package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtEqualityVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
    new KtEquality {
      parent = parentNode
      context = Some(ctx)
      comparison = Try(
        ctx.comparison.asScala.map(
          KtComparisonVisitor.visit(_)(Some(this.asInstanceOf[KtEquality]))
        ).toVector
      ).toOption
    }
}
