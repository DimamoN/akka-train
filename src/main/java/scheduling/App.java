package scheduling;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {

        ActorSystem ac = ActorSystem.create("system");
        ActorRef actor = ac.actorOf(Actor.props());

        //After time, Duration, receiver, message, dispatcher, sender
        ac.scheduler().schedule(Duration.Zero(),
                Duration.create(3, TimeUnit.SECONDS),
                actor, "hello", ac.dispatcher(), null);

    }

}
