package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtDisjunction}

import scala.jdk.CollectionConverters._
import scala.util.Try

object DisjunctionContext extends KotlinParserBaseVisitor[Option[AST] => KtDisjunction] {
  override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) =
    parentNode =>
      new KtDisjunction {
        parent = parentNode
        context = Some(ctx)
        conjunctions = Try(
          ctx.conjunction.asScala.toVector
            .map(ConjunctionContext.visit(_)(Some(this)))
        ).toOption
      }
}
