package lexi

enum Language(value: String) {
  case Kotlin extends Language("kt")
  case Scala extends Language("scala")
}

object Language {
  def withFileType(fileType: String): Language =
    fileType match
      case "kt" => Kotlin
      case "scala" => Scala
}