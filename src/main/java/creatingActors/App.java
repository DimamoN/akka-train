package creatingActors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {

    public static void main(String[] args) throws InterruptedException {

        ActorSystem as = ActorSystem.create("system");
        ActorRef myActor = as.actorOf(MyActor.props("test",30));

        //Send messages
        myActor.tell("Yan", ActorRef.noSender());
        myActor.tell("Joy", ActorRef.noSender());

        //Trying to send wrong-type message
        myActor.tell(9999, ActorRef.noSender());




        //Check deadLetters
//        Thread.sleep(1000);
//        as.stop(myActor);
//        Thread.sleep(1000);
//        myActor.tell("Yan", ActorRef.noSender());

        as.terminate();
    }

}
