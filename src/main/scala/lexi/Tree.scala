package lexi

trait Tree {
  def parent: Option[Tree]
  def children: List[Tree]

  def accept(visitor: Visitor) =
    visitor.visit(this)
}

trait Visitor {
  def visit(tree: Tree): Tree
}