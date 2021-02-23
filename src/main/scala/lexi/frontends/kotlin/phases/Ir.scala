package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin._
import lexi.frontends.kotlin.transformations.IR.{irClass, irFile, irFunction, irProperty}
import lexi.ir.IrTree
import lexi.Phase

object Ir extends Phase {
  def apply(ast: AST): IrTree = ast match {
    case node: KtFile     => irFile(node)
    case node: KtClass    => irClass(node)
    case node: KtFunction => irFunction(node)
    case node: KtProperty => irProperty(node)
  }
}
