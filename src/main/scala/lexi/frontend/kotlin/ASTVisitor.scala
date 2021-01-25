package lexi.frontend.kotlin

import lexi.frontend.kotlin.ast.ASTNode

trait ASTVisitor {
  def visit(ast: ASTNode): ASTNode
}
