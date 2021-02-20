package lexi


// enum Language(value: String) {
//   case Kotlin extends Language("kt")
//   case Scala extends Language("scala")
// }

object Language extends Enumeration {
  type Language = Value
  val Kotlin, Scala = Value

  def withFileType(fileType: String): Either[Throwable, Language] = fileType match {
    case "kt" => Right(Kotlin)
    case "scala" => Right(Scala)
    case _ => Left(new Error("Unable to determine language for the file type."))
  }
}