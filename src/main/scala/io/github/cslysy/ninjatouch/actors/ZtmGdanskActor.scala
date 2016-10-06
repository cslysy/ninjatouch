package io.github.cslysy.ninjatouch.actors

import akka.actor.Actor
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Element
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import com.roundeights.hasher.Implicits._
import scala.language.postfixOps
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import net.sargue.mailgun.Configuration
import net.sargue.mailgun.Mail

class ZtmGdanskActor extends Actor {

  var counter = 0;
  var latestHash = "";
  val browser = JsoupBrowser()
  
  val mailConf = new Configuration()
    .domain("sandbox401c5d6ae6d64c41845872c1073737f6.mailgun.org")
    .apiKey("key-55fb5e9b9e67c58718351085259cd1d9")
    .from("NinjaTouch", "ninjatouch@cslys.github.io")


  def receive = {
    case _ => checkForNews
  }

  def checkForNews = {
    val website = browser.get("http://www.ztm.gda.pl/hmvc/index.php/sipinfo/lista_a/")
    val divs = website >> elementList(".news")
    val newsContent = divs(1).text
    val currentHash = newsContent.sha1.hash.toString

    if (latestHash == currentHash) {
      println("Same hash detected - nothing changed")
    } else {
      latestHash = currentHash
      sendNewsByEmail(newsContent)
      println(newsContent)
    }
  }
  


  def sendNewsByEmail(content:String) = {
    println("Sending an email: " + content)
    Mail.using(mailConf)
    .to("kubolec85@gmail.com")
    .subject("NinjaTouch Alert - ZTM Gdańsk")
    .text(content)
    .build()
    .send();
  }

}
