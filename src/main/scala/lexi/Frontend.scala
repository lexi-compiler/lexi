package lexi

import lexi.Phase
import lexi.ir.nodes.IrTree

import scala.util.Try

object Frontend {
  def apply(source: Source): Option[IrTree] = source.language match {
    case Language.Kotlin => Try(lexi.frontends.kotlin.phases.LanguageAnalysis(source)).toOption
    case Language.Scala  => Try(lexi.frontends.scala.phases.LanguageAnalysis(source)).toOption
  }
}
