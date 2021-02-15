package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtClassParameter(
  var name: Option[String] = None,
  var `type`: Option[String] = None
) extends ASTNode

object KtClassParameter extends KotlinParserBaseVisitor[Option[ASTNode] => KtClassParameter] {
  override def visitClassParameter(ctx: KotlinParser.ClassParameterContext) = { parentNode =>
    new KtClassParameter {
      context = Some(ctx)
      parent = parentNode
      name = Some(ctx.simpleIdentifier.getText)
      `type` = Some(ctx.`type`.getText)
    }
  }
}
