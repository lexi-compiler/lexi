package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtInfixOperationVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtInfixOperation] {
  override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = { parentNode =>
    new KtInfixOperation {
      parent = parentNode
      context = Some(ctx)
      elvisExpression = Try(
        ctx.elvisExpression.asScala.toVector.map(
          KtElvisExpressionVisitor.visit(_)(Some(this.asInstanceOf[KtInfixOperation]))
        )
      ).toOption
    }
  }
}
