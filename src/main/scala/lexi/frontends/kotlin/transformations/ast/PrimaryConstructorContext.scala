package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtPrimaryConstructor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object PrimaryConstructorContext
  extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryConstructor] {
  override def visitPrimaryConstructor(
    ctx: KotlinParser.PrimaryConstructorContext
  ) = { parentNode =>
    new KtPrimaryConstructor {
      context = Some(ctx)
      parent = parentNode
      classParameters = Try {
        ctx.classParameters.classParameter.asScala.toVector
          .map(ClassParameterContext.visit(_)(Some(this)))
      }.toOption
    }
  }
}
