package lexi

trait Tree {
  def parent: Option[Tree]
  def children: List[Tree]

  def accept(visitor: Visitor[Tree]) =
    visitor.visit(this)
}
