package lexi

import lexi.Language
import lexi.ir.nodes.IrNode

import java.nio.file.{Files, Path}
import scala.util.{Failure, Success, Try}

case class Source(
  file: String,
  text: String,
  language: Language
)

object Source {
  def fromFile(file: String): Either[Throwable, Source] =
    Try(Files.readString(Path.of(file))).toEither match {
      case Right(content) =>
        Right(
          Source(
            file = file,
            text = content,
            language = languageFromFile(file)
          )
        )
      case Left(exception) => Left(exception)
    }
    
  def fromString(source: String, language: Language): Source =
    new Source(file = null, text = source, language = language)

  def languageFromFile(file: String): Language =
    Language.withFileType(getFileExtension(file))

  private def getFileExtension(file: String): String =
    file.substring(file.lastIndexOf('.') + 1)
}
