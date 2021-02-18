package lexi

import lexi.frontends.kotlin.phases.Frontend
import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  var config: CompilerConfiguration = new CompilerConfiguration
) {
  def phases(language: Language): List[List[Phase]] =
    frontendPhases ::: transformPhases
    
  protected def frontendPhases: List[List[Phase]] =
    List(new Frontend)

//  protected def frontendPhasesFor(language: Language): List[List[Phase]] = language match {
//    case Language.Kotlin => lexi.frontends.kotlin.phases.Frontend
//    case Language.Scala => lexi.frontends.scala.phases.Frontend
//  }

  def run(sources: Vector[Source]) =
    CompilationResult(
      configuration = config,
      sources = sources,
      ir = sources.map(source => new FirstPhase.parse(source))
    )
}
