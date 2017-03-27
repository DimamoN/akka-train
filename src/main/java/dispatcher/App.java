package dispatcher;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import dispatcher.actors.DBActor;
import dispatcher.actors.OtherActor;


public class App {

    public static void main(String[] args) {

        final ActorSystem ac = ActorSystem.create("system");

        ActorRef dbActor = ac.actorOf(DBActor.props(), "dbActor");
        ActorRef otherActor = ac.actorOf(OtherActor.props(), "otherActor");

        for (int i = 1; i <= 10; i++) {
            dbActor.tell("Work : " + i, ActorRef.noSender());
            otherActor.tell("other : " + i, ActorRef.noSender());
        }

        System.out.println("All messages are sent");
    }

}
