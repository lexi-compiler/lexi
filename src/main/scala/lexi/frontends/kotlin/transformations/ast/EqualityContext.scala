package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtEquality}

import scala.jdk.CollectionConverters._
import scala.util.Try

object EqualityContext extends KotlinParserBaseVisitor[Option[AST] => KtEquality] {
  override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
    new KtEquality {
      parent = parentNode
      context = Some(ctx)
      comparison = Try(
        ctx.comparison.asScala.toVector
          .map(ComparisonContext.visit(_)(Some(this)))
      ).toOption
    }
}
