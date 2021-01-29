package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontend.kotlin.ast.{ASTNode, KtFile}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SyntaxAnalysis:
  def apply(source: String): ASTNode =
    ((CharStreams.fromString(_: String))
      andThen (new KotlinLexer(_))
      andThen (new CommonTokenStream(_))
      andThen (new KotlinParser(_))
      andThen (_.kotlinFile)
      andThen (KtFile.visit(_)))(source)
