package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtDisjunction(
  var conjunctions: Option[Vector[KtConjunction]] = None
) extends ASTNode

object KtDisjunction extends KotlinParserBaseVisitor[Option[ASTNode] => KtDisjunction] {
  override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) = parentNode =>
    new KtDisjunction {
      parent = parentNode
      context = Some(ctx)
      conjunctions = Try(
        ctx.conjunction.asScala.toVector.map(
          KtConjunction.visit(_)(Some(this.asInstanceOf[KtDisjunction]))
        )
      ).toOption
    }
}
