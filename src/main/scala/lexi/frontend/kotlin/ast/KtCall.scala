package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtCall(
  var function: KtFunction = null,
) extends ASTNode


