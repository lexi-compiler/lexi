package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtGenericCallLikeComparison}

import scala.util.Try

object GenericCallLikeComparisonContext
  extends KotlinParserBaseVisitor[Option[AST] => KtGenericCallLikeComparison] {
  override def visitGenericCallLikeComparison(
    ctx: KotlinParser.GenericCallLikeComparisonContext
  ) = { parentNode =>
    new KtGenericCallLikeComparison {
      parent = parentNode
      context = Some(ctx)
      infixOperation = Try(
        InfixOperationContext.visit(ctx.infixOperation)(Some(this))
      ).toOption
    }
  }
}
