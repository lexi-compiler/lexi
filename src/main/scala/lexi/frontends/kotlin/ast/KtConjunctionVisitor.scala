package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtConjunctionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtConjunction] {
  override def visitConjunction(ctx: KotlinParser.ConjunctionContext) = parentNode =>
    new KtConjunction {
      parent = parentNode
      context = Some(ctx)
      equalities = Try(
        ctx.equality.asScala.toVector.map(
          KtEqualityVisitor.visit(_)(Some(this.asInstanceOf[KtConjunction]))
        )
      ).toOption
    }
}
