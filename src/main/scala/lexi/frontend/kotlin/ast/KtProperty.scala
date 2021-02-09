package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontend.kotlin.phases.TypeInference

import scala.util.Try

case class KtProperty(
  var name: Option[String] = None,
  var expression: Option[String] = None,
  var dataType: Option[String] = None
) extends ASTNode

object KtProperty extends KotlinParserBaseVisitor[Option[ASTNode] => KtProperty] {
  override def visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) = { parentNode =>
    new KtProperty {
      parent = parentNode
      context = Some(ctx)
      name = Try(ctx.variableDeclaration.simpleIdentifier.getText).toOption
      expression = Try(ctx.expression.getText).toOption
      dataType = Try(ctx.variableDeclaration.`type`.getText).toOption
    }
  }
}
