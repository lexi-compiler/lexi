package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtEquality}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtEqualityVisitor extends KotlinParserBaseVisitor[Option[AST] => KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
    new KtEquality {
      parent = parentNode
      context = Some(ctx)
      comparison = Try(
        ctx.comparison.asScala.toVector
          .map(KtComparisonVisitor.visit(_)(Some(this)))
      ).toOption
    }
}
