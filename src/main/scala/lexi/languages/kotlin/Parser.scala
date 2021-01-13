package lexi.languages.kotlin

import lexi.languages.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.languages.kotlin.ast.ASTNode
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Parser {
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
