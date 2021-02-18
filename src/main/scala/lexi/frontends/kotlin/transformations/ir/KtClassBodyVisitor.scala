package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtClassBody}
import lexi.frontends.kotlin.transformations.ast.KtClassBodyVisitor
import lexi.ir.nodes.IrClassBody

import scala.util.Try

object KtClassBodyVisitor extends KtVisitor {
  override def visit(ast: AST): IrClassBody = {
    val ktClassBody = ast.asInstanceOf[KtClassBody]
    new IrClassBody(
      declarations = ktClassBody.classMemberDeclarations.map(
        _.map(KtDeclarationVisitor.visit(_))
      )
    )
  }
}
