package services

import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import javax.inject._
import play.api.inject.ApplicationLifecycle
import scala.concurrent.Future

@Singleton
class GreeterService @Inject()(lifecycle: ApplicationLifecycle) {
  val system: ActorSystem[HelloWorldMain.SayHello] = ActorSystem(HelloWorldMain(), "hello")

  // Send initial messages
  system ! HelloWorldMain.SayHello("World")
  system ! HelloWorldMain.SayHello("Akka")

  // Clean up the actor system when the app shuts down
  lifecycle.addStopHook { () =>
    Future.successful(system.terminate())
  }
}

object HelloWorld {
  final case class Greet(whom: String, replyTo: akka.actor.typed.ActorRef[Greeted])
  final case class Greeted(whom: String, from: akka.actor.typed.ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive[Greet] { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }
}

object HelloWorldBot {
  def apply(max: Int): Behavior[HelloWorld.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int, max: Int): Behavior[HelloWorld.Greeted] = {
    Behaviors.receive[HelloWorld.Greeted] { (context, message) =>
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! HelloWorld.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
  }
}

object HelloWorldMain {
  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] = Behaviors.setup[SayHello] { context =>
    val greeter = context.spawn(HelloWorld(), "greeter")

    Behaviors.receiveMessage { message =>
      val replyTo = context.spawn(HelloWorldBot(max = 3), message.name)
      greeter ! HelloWorld.Greet(message.name, replyTo)
      Behaviors.same
    }
  }
} 