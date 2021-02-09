package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtInfixOperation(
  var elvisExpression: Option[Vector[KtElvisExpression]] = None
) extends ASTNode

object KtInfixOperation extends KotlinParserBaseVisitor[Option[ASTNode] => KtInfixOperation] {
  override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = { parentNode =>
    new KtInfixOperation {
      parent = parentNode
      context = Some(ctx)
      elvisExpression = Try(
        ctx.elvisExpression.asScala.toVector.map(
          KtElvisExpression.visit(_)(Some(this.asInstanceOf[KtInfixOperation]))
        )
      ).toOption
    }
  }
}
