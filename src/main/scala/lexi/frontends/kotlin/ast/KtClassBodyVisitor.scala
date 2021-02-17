package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtClassBodyVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtClassBody] {
  override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = { parentNode =>
    new KtClassBody {
      context = Some(ctx)
      parent = parentNode
      classMemberDeclarations = Try {
        ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector.map(
          KtDeclarationVisitor.visit(_)(Some(this.asInstanceOf[KtClassBody]))
        )
      }.toOption
    }
  }
}
