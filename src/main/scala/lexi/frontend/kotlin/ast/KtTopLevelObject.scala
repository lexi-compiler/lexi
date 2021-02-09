package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.TopLevelObjectContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext

import scala.util.Try

case class KtTopLevelObject(
  var declaration: Option[KtDeclaration] = None
) extends ASTNode

object KtTopLevelObject extends KotlinParserBaseVisitor[Option[ASTNode] => KtTopLevelObject] {
  override def visitTopLevelObject(ctx: TopLevelObjectContext) = { parentNode =>
    new KtTopLevelObject {
      parent = parentNode
      context = Some(ctx)
      declaration = Try(KtDeclaration.visit(ctx.declaration)(Some(this.asInstanceOf[KtTopLevelObject]))).toOption
    }
  }
}
