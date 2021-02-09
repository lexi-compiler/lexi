package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunctionBody}
import lexi.ir.nodes.IrFunctionBody

import scala.util.Try

object KtFunctionBodyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunctionBody =
    (
      (body: KtFunctionBody) =>
        new IrFunctionBody {
          block = None
          expression = body.expression.map(KtExpressionVisitor.visit(_))
        }
    )(ast.asInstanceOf[KtFunctionBody])
}
