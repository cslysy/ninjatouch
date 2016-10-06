package io.github.cslysy.ninjatouch

import java.nio.charset.CodingErrorAction
import scala.io.Codec
import scala.io.Source
import scala.xml.XML

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    
    implicit val codec = Codec("UTF-8")

    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    
    val htmlString = Source.fromURL("http://www.ztm.gda.pl/hmvc/index.php/sipinfo/lista_a/").getLines.mkString
    
    //val xml = XML.load(htmlString)
    //val temp = (xml \\ "div")
    
    println("siema" + htmlString)
    
    
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
  }

}
