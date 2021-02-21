package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtClassBody
import lexi.ir.IrClassBody

import scala.util.Try

object KtClassBodyVisitor extends Visitor {
  override def visit(ast: Tree): IrClassBody = {
    val ktClassBody = ast.asInstanceOf[KtClassBody]
    new IrClassBody(
      declarations = ktClassBody.classMemberDeclarations.map(
        _.map(KtDeclarationVisitor.visit(_))
      )
    )
  }
}
