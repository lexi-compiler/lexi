package lexi

import lexi.frontends.AST
import lexi.ir.IrTree

case class CompilationUnit(
  source: Source,
  var ast: Option[AST] = None,
  var ir: Option[IrTree] = None,
  var jvm: Option[Array[Byte]] = None
)
