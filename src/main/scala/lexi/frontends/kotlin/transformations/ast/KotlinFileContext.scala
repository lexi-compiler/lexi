package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.KtFile
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KotlinFileContext extends KotlinParserBaseVisitor[KtFile] {
  override def visitKotlinFile(ctx: KotlinParser.KotlinFileContext) =
    new KtFile {
      context = Some(ctx)
      topLevelObjects = Try(
        ctx.topLevelObject.asScala.toVector
          .map(TopLevelObjectContext.visit(_)(Some(this)))
      ).toOption
    }
}
