package lexi

import lexi.Phase

import scala.util.Try

object Frontend {
  def apply(source: Source): Option[Tree] = source.language match {
    case Language.Kotlin => Try(lexi.frontends.kotlin.phases.LanguageAnalysis(source)).toOption
    case Language.Scala  => Try(lexi.frontends.scala.phases.LanguageAnalysis(source)).toOption
  }
}
