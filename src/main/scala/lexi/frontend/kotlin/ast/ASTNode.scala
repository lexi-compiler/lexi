package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.ASTVisitor
import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable.ListBuffer

trait ASTNode {
  var parent: ASTNode = null
  var prevSibling: ASTNode = null
  var nextSibling: ASTNode = null
  var children: ListBuffer[ASTNode] = ListBuffer.empty
  var context: ParserRuleContext = null

  def accept(visitor: ASTVisitor): ASTNode =
    visitor.visit(this)
}
