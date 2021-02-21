package lexi

case class CompilationUnit(
  source: Source,
  ir: Option[Tree] = None,
  jvm: Option[Array[Byte]] = None
)

object CompilationUnit {
  def apply(source: Source): CompilationUnit =
    CompilationUnit(
      source = source,
      ir = Frontend(source)
    )
}
