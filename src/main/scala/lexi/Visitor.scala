package lexi

trait Visitor[A, B] {
  def visit(tree: A): B
}
