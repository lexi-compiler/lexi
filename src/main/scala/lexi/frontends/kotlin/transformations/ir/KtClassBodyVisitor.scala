package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtClassBody
import lexi.ir.IrClassBody

import scala.util.Try

object KtClassBodyVisitor extends Visitor[KtClassBody, IrClassBody] {
  override def visit(ast: KtClassBody): IrClassBody = {
    new IrClassBody(
      declarations = ast.classMemberDeclarations.map(
        _.map(KtDeclarationVisitor.visit(_))
      )
    )
  }
}
