package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtInfixOperation}

import scala.jdk.CollectionConverters._
import scala.util.Try

object InfixOperationContext extends KotlinParserBaseVisitor[Option[AST] => KtInfixOperation] {
  override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = { parentNode =>
    new KtInfixOperation {
      parent = parentNode
      context = Some(ctx)
      elvisExpression = Try(
        ctx.elvisExpression.asScala.toVector
          .map(ElvisExpressionContext.visit(_)(Some(this)))
      ).toOption
    }
  }
}
