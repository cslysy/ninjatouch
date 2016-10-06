package io.github.cslysy.ninjatouch

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration._
import io.github.cslysy.ninjatouch.actors.ZtmGdanskActor

object NinjaTouchApp {
  
  def main(args: Array[String]) {
    
    val system = ActorSystem("NinjaTouch")
    val ztmActor = system.actorOf(Props[ZtmGdanskActor], "ZtmGdansActor")
    
    
    import system.dispatcher
    
    system.scheduler.schedule(
        0 milliseconds, 60 seconds,
        ztmActor, "ask for news"
    )
  }
  
}