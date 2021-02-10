package lexi.frontends.kotlin.ast

import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable.ListBuffer

trait ASTNode {
  var parent: Option[ASTNode] = None
  var context: Option[ParserRuleContext] = None

  def accept(visitor: ASTVisitor): ASTNode =
    visitor.visit(this)
}

trait ASTVisitor {
  def visit(ast: ASTNode): ASTNode
}
