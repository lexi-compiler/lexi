package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtConjunction(
  var equalities: Option[Vector[KtEquality]] = None
) extends ASTNode

object KtConjunction extends KotlinParserBaseVisitor[Option[ASTNode] => KtConjunction] {
  override def visitConjunction(ctx: KotlinParser.ConjunctionContext) = parentNode =>
    new KtConjunction {
      parent = parentNode
      context = Some(ctx)
      equalities = Try(
        ctx.equality.asScala.toVector.map(
          KtEquality.visit(_)(Some(this.asInstanceOf[KtConjunction]))
        )
      ).toOption
    }
}
