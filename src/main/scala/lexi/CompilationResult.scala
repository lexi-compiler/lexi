package lexi

import lexi.ir.nodes.IrNode

case class CompilationResult(
  configuration: CompilerConfiguration,
  sources: Vector[Source],
  ir: Vector[Option[IrNode]] = Vector.empty,
  jvm: Vector[Option[Array[Byte]]] = Vector.empty
)
