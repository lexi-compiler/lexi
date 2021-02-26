package lexi

trait Tree {
  def parent: Option[Tree]
  def children: Vector[Tree]

  def accept(visitor: Visitor[Tree, Tree]) =
    visitor.visit(this)
}
