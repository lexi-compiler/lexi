package lexi.frontends.kotlin.phases

import lexi.{Phase, Tree, Visitor}
import lexi.frontends.kotlin.transformations.ir.{KtClassVisitor, KtFileVisitor, KtFunctionVisitor, KtPropertyVisitor}
import lexi.frontends.kotlin.{AST, KtClass, KtFile, KtFunction, KtProperty}
import lexi.ir.IrTree

object Ir extends Phase {
  def apply(tree: Tree): Tree =
    visitorType(tree).visit(tree)

  private def visitorType(tree: Tree): Visitor = tree match {
    case _: KtFile     => KtFileVisitor
    case _: KtClass    => KtClassVisitor
    case _: KtFunction => KtFunctionVisitor
    case _: KtProperty => KtPropertyVisitor
  }
}
