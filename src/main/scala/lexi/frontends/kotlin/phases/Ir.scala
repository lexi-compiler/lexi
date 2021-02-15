package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.{ASTNode, ASTVisitor, KtClass, KtFile, KtFunction, KtProperty}
import lexi.frontends.kotlin.ir.{KtClassVisitor, KtFileVisitor, KtFunctionVisitor, KtPropertyVisitor, KtVisitor}
import lexi.ir.nodes.IrNode

object Ir extends Phase:
  def apply(ast: ASTNode): IrNode =
    visitorType(ast).visit(ast)

  private def visitorType(ast: ASTNode): KtVisitor = ast match
    case _: KtFile => KtFileVisitor
    case _: KtClass => KtClassVisitor
    case _: KtFunction => KtFunctionVisitor
    case _: KtProperty => KtPropertyVisitor