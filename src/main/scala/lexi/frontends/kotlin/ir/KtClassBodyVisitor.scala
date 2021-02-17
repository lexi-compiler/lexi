package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtClassBody}
import lexi.ir.nodes.IrClassBody

import scala.util.Try

object KtClassBodyVisitor extends KtVisitor {
  override def visit(ast: Tree): IrClassBody = {
    val ktClassBody = ast.asInstanceOf[KtClassBody]
    new IrClassBody(
      declarations = ktClassBody.classMemberDeclarations.map(_.map(KtDeclarationVisitor.visit(_)))
    )
  }
}
