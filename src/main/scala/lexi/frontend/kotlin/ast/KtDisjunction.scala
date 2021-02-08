package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtDisjunction(
  var conjunctions: Option[Vector[KtConjunction]] = None
) extends ASTNode

object KtDisjunction extends KotlinParserBaseVisitor[KtDisjunction] {
  override def visitDisjunction(
    ctx: KotlinParser.DisjunctionContext
  ): KtDisjunction =
    new KtDisjunction {
      context = Some(ctx)
      conjunctions = Try(ctx.conjunction.asScala.toVector.map(KtConjunction.visit(_))).toOption
    }

}
