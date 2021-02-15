package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtClassBody(
  var classMemberDeclarations: Option[Vector[KtDeclaration]] = None
) extends ASTNode

object KtClassBody extends KotlinParserBaseVisitor[Option[ASTNode] => KtClassBody] {
  override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = { parentNode =>
    new KtClassBody {
      context = Some(ctx)
      parent = parentNode
      classMemberDeclarations = Try {
        ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector.map(
          KtDeclaration.visit(_)(Some(this.asInstanceOf[KtClassBody]))
        )
      }.toOption
    }
  }
}
