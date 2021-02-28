package lexi

class Context {
  var phases: Vector[Phase] = Vector.empty
  var compilationUnits: Vector[CompilationUnit] = Vector.empty
  var adaptiveLL: Boolean = false
}
