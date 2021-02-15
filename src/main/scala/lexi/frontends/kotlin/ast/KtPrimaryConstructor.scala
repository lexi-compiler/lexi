package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtPrimaryConstructor(
  var classParameters: Option[Vector[KtClassParameter]] = None
) extends ASTNode

object KtPrimaryConstructor extends KotlinParserBaseVisitor[Option[ASTNode] => KtPrimaryConstructor] {
  override def visitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) = { parentNode =>
    new KtPrimaryConstructor {
      context = Some(ctx)
      parent = parentNode
      classParameters = Try {
        ctx.classParameters.classParameter.asScala.toVector.map(
          KtClassParameter.visit(_)(Some(this.asInstanceOf[KtPrimaryConstructor]))
        )
      }.toOption
    }
  }
}
