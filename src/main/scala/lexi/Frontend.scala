package lexi

import lexi.Phase

import scala.util.Try

object Frontend {
  def apply(language: Language): Phase = language match {
    case Language.Kotlin => new lexi.frontends.kotlin.phases.LanguageAnalysis
    case Language.Scala  => new lexi.frontends.scala.phases.LanguageAnalysis
  }
}
