package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.Visitor
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontends.kotlin.ast.ASTNode
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Parse extends Phase {
  def apply(source: String): ASTNode = {
    val lexer = new KotlinLexer(CharStreams.fromString(source))
    val parser = new KotlinParser(new CommonTokenStream(lexer))
    val parseTree = parser.kotlinFile
    val visitor = new Visitor
    visitor.visit(parseTree)
  }

  def parseLisp(source: String): String = {
    val lexer = new KotlinLexer(CharStreams.fromString(source))
    val parser = new KotlinParser(new CommonTokenStream(lexer))
    parser.kotlinFile.toStringTree
  }
}
