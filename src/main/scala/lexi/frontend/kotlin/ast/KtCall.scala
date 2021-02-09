package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtCall(
  var function: Option[KtFunction] = None
) extends ASTNode


