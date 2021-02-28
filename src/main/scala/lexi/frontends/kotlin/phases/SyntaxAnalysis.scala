package lexi.frontends.kotlin.phases

import lexi._
import lexi.frontends.kotlin.antlr.{KotlinLexer, KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.transformations.AST._
import lexi.frontends.kotlin.{AST, KtNamedFunction}
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

class SyntaxAnalysis extends Phase {
  def run(context: Context): Unit = {
    context.compilationUnits.foreach { unit =>
      val lexer = new KotlinLexer(CharStreams.fromString(unit.source.text.trim))
      val parser = new KotlinParser(new CommonTokenStream(lexer))
      unit.ast = startRule(context, parser)
    }
  }

  private def startRule(context: Context, parser: KotlinParser): Option[AST] = {
    if (context.adaptiveLL) {
      parser.getCurrentToken.getText match {
        case token if token.equals("fun") => FunctionDeclarationContext.visit(parser.functionDeclaration)(None)
        case token if token.equals("val") || token.equals("var") =>
          PropertyDeclarationContext.visit(parser.propertyDeclaration)(None)
        case token if token.equals("class") => ClassDeclarationContext.visit(parser.classDeclaration)(None)
        case _                              => KotlinFileContext.visit(parser.kotlinFile)
      }
    } else {
      KotlinFileContext.visit(parser.kotlinFile)
    }
  }
}
