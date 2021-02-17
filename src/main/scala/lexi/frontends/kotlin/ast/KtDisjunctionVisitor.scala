package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtDisjunctionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtDisjunction] {
  override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) = parentNode =>
    new KtDisjunction {
      parent = parentNode
      context = Some(ctx)
      conjunctions = Try(
        ctx.conjunction.asScala.toVector.map(
          KtConjunctionVisitor.visit(_)(Some(this.asInstanceOf[KtDisjunction]))
        )
      ).toOption
    }
}
