package io.github.cslysy.ninjatouch

import java.nio.charset.CodingErrorAction
import scala.io.Source
import scala.xml.XML
import scala.io._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Element
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import java.security.MessageDigest
import akka.actor.Actor
import akka.actor.Props
import scala.concurrent.duration._

/**
 * @author ${user.name}
 */
object App {

  //comment test
  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

  def transaction(code: => Boolean) = {
    //open trasaction
    code
    //commit
  }

   def md5(s: String) = {
    val m = java.security.MessageDigest.getInstance("MD5")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    new java.math.BigInteger(1, m.digest()).toString(16)
  }


  def main(args: Array[String]) {

    val concat: (Int, Int) => String = (x1, x2) => "" + x1 + x2

    var myTuple: (String, Integer) = ("siema", 2)

    transaction {
      println("SELECT * FROM COS")
      true;
    }

    println(myTuple)
    println(Car("Audi A4", "1.8T").engine)
    println(concat(2, 3))
    println("Hello World!")
    println("concat arguments = " + foo(args))

    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    val browser = JsoupBrowser()
    val doc = browser.get("http://www.ztm.gda.pl/hmvc/index.php/sipinfo/lista_a/")
    val div = doc >> elementList(".news")
    println(div(1).text)
    println(md5(div(1).text))
  }

}

case class Car(model: String, engine: String)
