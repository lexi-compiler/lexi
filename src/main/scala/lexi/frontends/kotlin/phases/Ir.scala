package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.transformations.ir.{KtClassVisitor, KtFileVisitor, KtFunctionVisitor, KtPropertyVisitor, KtVisitor}
import lexi.frontends.kotlin.{AST, KtClass, KtFile, KtFunction, KtProperty}
import lexi.ir.nodes.IrTree

object Ir extends Phase {
  def apply(ast: AST): IrTree =
    visitorType(ast).visit(ast)

  private def visitorType(ast: AST): KtVisitor = ast match {
    case _: KtFile     => KtFileVisitor
    case _: KtClass    => KtClassVisitor
    case _: KtFunction => KtFunctionVisitor
    case _: KtProperty => KtPropertyVisitor
  }
}
