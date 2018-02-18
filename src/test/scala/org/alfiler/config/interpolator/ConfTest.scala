package org.alfiler.config.interpolator

import org.scalatest.{FlatSpecLike, Matchers}

class ConfTest extends FlatSpecLike with Matchers {
  import org.alfiler.config.interpolator.Conf._
  "Conf" should "parse valid texts" in {
    val c = conf"""{
                      stringField: hello everyone
                      intField: 342
                    }"""
    c.getString("stringField") shouldBe "hello everyone"
    c.getInt("intField") shouldBe 342
  }
  it should "work with resolving values also" in {
    val c = conf"""{
                    originalThing: 1234
                    copyThing: $${originalThing}
                   }"""
    c.getString("originalThing") shouldBe c.getString("copyThing")
    c.getString("originalThing") shouldBe "1234"
  }
}
