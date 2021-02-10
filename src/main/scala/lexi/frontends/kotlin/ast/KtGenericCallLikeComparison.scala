package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtGenericCallLikeComparison(
  var infixOperation: Option[KtInfixOperation] = None
) extends ASTNode

object KtGenericCallLikeComparison extends KotlinParserBaseVisitor[Option[ASTNode] => KtGenericCallLikeComparison] {
  override def visitGenericCallLikeComparison(ctx: KotlinParser.GenericCallLikeComparisonContext) = { parentNode =>
    new KtGenericCallLikeComparison {
      parent = parentNode
      context = Some(ctx)
      infixOperation = Try(
        KtInfixOperation.visit(ctx.infixOperation)(Some(this.asInstanceOf[KtGenericCallLikeComparison]))
      ).toOption
    }
  }
}
