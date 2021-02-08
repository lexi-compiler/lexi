package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtFunction(
  var name: Option[String] = None,
  var `type`: Option[String] = None,
  var functionBody: Option[KtFunctionBody] = None
) extends ASTNode

object KtFunction extends KotlinParserBaseVisitor[KtFunction] {
  override def visitFunctionDeclaration(
    ctx: KotlinParser.FunctionDeclarationContext
  ): KtFunction =
    new KtFunction {
      context = Some(ctx)
      name = Try(ctx.simpleIdentifier.getText).toOption
      `type` = Try(ctx.`type`.getText).toOption
      functionBody = Try(KtFunctionBody.visit(ctx.functionBody)).toOption
    }
}
