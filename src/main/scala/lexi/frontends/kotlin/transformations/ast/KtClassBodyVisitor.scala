package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtClassBody}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtClassBodyVisitor extends KotlinParserBaseVisitor[Option[AST] => KtClassBody] {
  override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = { parentNode =>
    new KtClassBody {
      context = Some(ctx)
      parent = parentNode
      classMemberDeclarations = Try {
        ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
          .map(KtDeclarationVisitor.visit(_)(Some(this)))
      }.toOption
    }
  }
}
