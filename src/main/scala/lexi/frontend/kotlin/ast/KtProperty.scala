package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontend.kotlin.phases.TypeInference

case class KtProperty(
  var name: String,
  var expression: String,
  var dataType: String
) extends ASTNode

object KtProperty extends KotlinParserBaseVisitor[ASTNode] {
  override def visitPropertyDeclaration(
    ctx: KotlinParser.PropertyDeclarationContext
  ): KtProperty =
    TypeInference(
      new KtProperty(
        name = ctx.variableDeclaration.simpleIdentifier.getText,
        expression = ctx.expression.getText,
        dataType =
          Option(ctx.variableDeclaration.`type`).map(_.getText).getOrElse(null)
      ) {
        context = ctx
      }
    )
}
