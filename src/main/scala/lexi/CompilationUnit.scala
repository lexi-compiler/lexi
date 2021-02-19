package lexi

import lexi.ir.nodes.IrTree

case class CompilationUnit(
  source: Source,
  ir: Option[IrTree] = None,
  jvm: Option[Array[Byte]] = None
)

object CompilationUnit {
  def apply(source: Source): CompilationUnit =
    CompilationUnit(
      source = source,
      ir = Frontend(source)
    )
}
