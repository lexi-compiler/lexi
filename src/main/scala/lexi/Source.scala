package lexi

import lexi.Language
import lexi.ir.IrTree

import java.nio.file.{Files, Path}
import scala.util.{Failure, Success, Try}

case class Source(
  file: String,
  text: String,
  language: Language
)

object Source {
  def fromFile(file: String): Either[Throwable, Source] =
    for {
      language <- fileLanguage(file)
      content <- readFile(file)
    } yield Source(
      file = file,
      text = content,
      language = language
    )

  def fromString(source: String, language: Language): Source =
    new Source(file = null, text = source, language = language)

  def fileLanguage(file: String): Either[Throwable, Language] =
    Language.withFileType(getFileExtension(file))

  def readFile(file: String): Either[Throwable, String] =
    Try(Files.readString(Path.of(file))).toEither

  private def getFileExtension(file: String): String =
    file.substring(file.lastIndexOf('.') + 1)
}
