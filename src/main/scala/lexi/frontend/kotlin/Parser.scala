package lexi.frontend.kotlin

import lexi.Phase
import lexi.frontend.Language.Language

trait Parser[T <: Language] extends Phase
