package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtGenericCallLikeComparisonVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtGenericCallLikeComparison] {
  override def visitGenericCallLikeComparison(ctx: KotlinParser.GenericCallLikeComparisonContext) = { parentNode =>
    new KtGenericCallLikeComparison {
      parent = parentNode
      context = Some(ctx)
      infixOperation = Try(
        KtInfixOperationVisitor.visit(ctx.infixOperation)(Some(this.asInstanceOf[KtGenericCallLikeComparison]))
      ).toOption
    }
  }
}
