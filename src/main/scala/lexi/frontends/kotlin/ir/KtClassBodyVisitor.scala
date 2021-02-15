package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtClassBody}
import lexi.ir.nodes.IrClassBody

import scala.util.Try

object KtClassBodyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrClassBody =
    (
      (ktClassBody: KtClassBody) =>
        new IrClassBody {
          declarations = ktClassBody.classMemberDeclarations.map(_.map(KtDeclarationVisitor.visit(_)))
        }
    )(ast.asInstanceOf[KtClassBody])
}
