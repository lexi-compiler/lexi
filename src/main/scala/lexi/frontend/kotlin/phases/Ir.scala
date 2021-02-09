package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.{ASTNode, ASTVisitor, KtFile, KtFunction, KtProperty}
import lexi.frontend.kotlin.ir.{KtFileVisitor, KtFunctionVisitor, KtPropertyVisitor, KtVisitor}
import lexi.ir.nodes.IrNode

object Ir:
  def apply(ast: ASTNode): IrNode =
    visitorType(ast).visit(ast)

  private def visitorType(ast: ASTNode): KtVisitor = ast match
    case _: KtFile => KtFileVisitor
    case _: KtFunction => KtFunctionVisitor
    case _: KtProperty => KtPropertyVisitor