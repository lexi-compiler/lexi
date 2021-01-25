package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtConjunction(
  var equalities: Vector[KtEquality] = Vector.empty
) extends ASTNode

object KtConjunction extends KotlinParserBaseVisitor[ASTNode] {
  override def visitConjunction(
    ctx: KotlinParser.ConjunctionContext
  ): KtConjunction =
    new KtConjunction {
      context = ctx
      equalities = ctx.equality.asScala.map { item =>
        val equality = KtEquality.visitEquality(item)
        equality.parent = this
        equality
      }.toVector
    }

}
