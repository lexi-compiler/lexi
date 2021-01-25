package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtDisjunction(
  var conjunctions: Vector[KtConjunction] = Vector.empty
) extends ASTNode

object KtDisjunction extends KotlinParserBaseVisitor[ASTNode] {
  override def visitDisjunction(
    ctx: KotlinParser.DisjunctionContext
  ): KtDisjunction =
    new KtDisjunction {
      context = ctx
      conjunctions = ctx.conjunction.asScala.map { conjunctionContext =>
        val conjunction = KtConjunction.visitConjunction(conjunctionContext)
        conjunction.parent = this
        conjunction
      }.toVector
    }

}
