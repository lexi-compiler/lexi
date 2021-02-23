package lexi.frontends.kotlin.phases

import lexi.{Phase, Tree, Visitor}
import lexi.frontends.kotlin.transformations.ir.{
  KtClassVisitor,
  KtFileVisitor,
  KtFunctionVisitor,
  KtPropertyVisitor
}
import lexi.frontends.kotlin.{AST, KtClass, KtFile, KtFunction, KtProperty}
import lexi.ir.IrTree

object Ir extends Phase {
  def apply(ast: AST): IrTree = ast match {
    case n: KtFile     => KtFileVisitor.visit(n)
    case n: KtClass    => KtClassVisitor.visit(n)
    case n: KtFunction => KtFunctionVisitor.visit(n)
    case n: KtProperty => KtPropertyVisitor.visit(n)
  }
}
