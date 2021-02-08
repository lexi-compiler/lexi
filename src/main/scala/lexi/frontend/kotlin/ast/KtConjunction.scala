package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtConjunction(
  var equalities: Option[Vector[KtEquality]] = None
) extends ASTNode

object KtConjunction extends KotlinParserBaseVisitor[KtConjunction] {
  override def visitConjunction(
    ctx: KotlinParser.ConjunctionContext
  ): KtConjunction =
    new KtConjunction {
      context = Some(ctx)
      equalities = Try(ctx.equality.asScala.toVector.map(KtEquality.visit(_))).toOption
    }

}
