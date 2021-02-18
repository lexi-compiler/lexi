package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtPrimaryConstructor}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtPrimaryConstructorVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryConstructor] {
  override def visitPrimaryConstructor(
    ctx: KotlinParser.PrimaryConstructorContext
  ) = { parentNode =>
    new KtPrimaryConstructor {
      context = Some(ctx)
      parent = parentNode
      classParameters = Try {
        ctx.classParameters.classParameter.asScala.toVector.map(
          KtClassParameterVisitor.visit(_)(
            Some(this.asInstanceOf[KtPrimaryConstructor])
          )
        )
      }.toOption
    }
  }
}
