package lexi

abstract class Phase {
  def run(context: Context): Unit
}
