package maki

import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable.ListBuffer

trait AST {
  var parent: AST = null

  var prevSibling: AST = null

  var nextSibling: AST = null

  var children: ListBuffer[AST] = ListBuffer.empty

  var parserRuleContext: ParserRuleContext = null
}
