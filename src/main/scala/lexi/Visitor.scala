package lexi

trait Visitor[T] {
  def visit(tree: Tree): T
}
