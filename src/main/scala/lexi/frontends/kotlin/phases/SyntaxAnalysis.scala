package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser}
import lexi.frontends.kotlin.ast.{ASTNode, KtFile}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SyntaxAnalysis extends Phase:
  def apply(source: String): ASTNode =
    ((CharStreams.fromString(_: String))
      andThen (new KotlinLexer(_))
      andThen (new CommonTokenStream(_))
      andThen (new KotlinParser(_))
      andThen (_.kotlinFile)
      andThen (KtFile.visit(_)))(source)
