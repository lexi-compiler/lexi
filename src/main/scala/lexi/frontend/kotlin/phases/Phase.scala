package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontend.kotlin.ast.{ASTNode, KtFile}
import lexi.ir.nodes.IrNode
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Phase {
  val lexicalAnalysis: String => CommonTokenStream = (source: String) =>
    new CommonTokenStream(new KotlinLexer(CharStreams.fromString(source)))

  val syntaxAnalysis: CommonTokenStream => KotlinParser =
    (tokenStream: CommonTokenStream) => (new KotlinParser(tokenStream))

  val semanticAnalysis: KotlinParser => ASTNode = (parser: KotlinParser) =>
    KtFile.visit(parser.kotlinFile)

  val parse: String => ASTNode = (source: String) =>
    (lexicalAnalysis
      andThen syntaxAnalysis
      andThen semanticAnalysis)(source)

  val ir: ASTNode => IrNode = (ast: ASTNode) => Ir(ast)

  val languageAnalysis: String => IrNode = (source: String) =>
    (parse
      andThen ir)(source)
}
