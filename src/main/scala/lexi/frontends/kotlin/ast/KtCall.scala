package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtCall(
  var function: Option[KtFunction] = None
) extends ASTNode


