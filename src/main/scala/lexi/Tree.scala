package lexi

trait Tree {
  def accept(visitor: Visitor[Tree, Tree]) =
    visitor.visit(this)
}
