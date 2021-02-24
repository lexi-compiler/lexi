package lexi

object TestUtils {
  def kotlinSource(code: String): Source =
    Source.fromString(code, Language.Kotlin)
}
