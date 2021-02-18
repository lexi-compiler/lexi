package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.{AST, KtClass, KtFile, KtFunction, KtProperty}
import lexi.frontends.kotlin.ir.{KtClassVisitor, KtFileVisitor, KtFunctionVisitor, KtPropertyVisitor, KtVisitor}
import lexi.ir.nodes.IrNode

object Ir extends Phase {
  def apply(ast: AST): IrNode =
    visitorType(ast).visit(ast)

  private def visitorType(ast: AST): KtVisitor = ast match {
    case _: KtFile     => KtFileVisitor
    case _: KtClass    => KtClassVisitor
    case _: KtFunction => KtFunctionVisitor
    case _: KtProperty => KtPropertyVisitor
  }
}
