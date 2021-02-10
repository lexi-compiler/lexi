package lexi

import lexi.Phase
import lexi.ir.nodes.IrNode

object Frontend {
  def apply(language: Language): String => IrNode = language match {
    case Language.Kotlin => { source => lexi.frontends.kotlin.phases.LanguageAnalysis(source) }
    case Language.Scala => { source => lexi.frontends.scala.phases.LanguageAnalysis(source) }
  }
}