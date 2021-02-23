package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtClassParameter}

object KtClassParameterVisitor extends KotlinParserBaseVisitor[Option[AST] => KtClassParameter] {
  override def visitClassParameter(ctx: KotlinParser.ClassParameterContext) = { parentNode =>
    new KtClassParameter {
      context = Some(ctx)
      parent = parentNode
      name = Some(ctx.simpleIdentifier.getText)
      `type` = Some(ctx.`type`.getText)
    }
  }
}
