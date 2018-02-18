package org.alfiler.config.interpolator

import com.typesafe.config.{Config, ConfigFactory}
import contextual._

import scala.util.Try
object Conf {
  object ConfigInterpolator extends Interpolator {
    case class ContextType() extends Context
    type Output = Config

    def contextualize(interpolation: ConfigInterpolator.StaticInterpolation): Seq[ContextType] = {
      val lit@Literal(_, configContent) = interpolation.parts.head
      Try {
        ConfigFactory.parseString(configContent).resolve()
      }.recover{case t => interpolation.abort(lit, 0, s"can't create a config from the string: ${t.getMessage}")
        }.get
      Nil
    }

    def evaluate(interpolation: RuntimeInterpolation): Config = {
      ConfigFactory.parseString(interpolation.literals.head).resolve()
    }
  }

  implicit class ConfigStringContext(sc: StringContext) {
    val conf = Prefix(ConfigInterpolator, sc)
  }

}