package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontend.kotlin.ast.{ASTNode, KtFile}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SyntaxAnalysis {
  def apply(source: String): ASTNode = {
    val lexer = new KotlinLexer(CharStreams.fromString(source))
    val lexerStream = new CommonTokenStream(lexer)
    val parser: KotlinParser = new KotlinParser(lexerStream)
    KtFile.visit(parser.kotlinFile)
  }
}
