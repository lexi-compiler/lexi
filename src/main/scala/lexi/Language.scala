package lexi

enum Language(value: String) {
  case Kotlin extends Language("kt")
  case Scala extends Language("scala")
}

object Language {
  def withFileType(fileType: String): Either[Throwable, Language] = fileType match {
    case "kt" => Right(Kotlin)
    case "scala" => Right(Scala)
    case _ => Left(Exception("Unable to determine language for the file type."))
  }

  def withLangParam(param: String): Either[Throwable, Language] = param match {
    case "kotlin" => Right(Kotlin)
    case "scala" => Right(Scala)
    case _ => Left(Exception("Unable to determine language for the file type."))
  }
}