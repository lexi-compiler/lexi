package lexi

trait Tree {
  def parent: Option[Tree]
  def children: List[Tree]
}
