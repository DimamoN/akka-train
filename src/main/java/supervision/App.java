package supervision;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import supervision.actors.SupervisorActor;

public class App {

    public static void main(String[] args) {

        ActorSystem ac = ActorSystem.create("system");
        ActorRef supervisor = ac.actorOf(SupervisorActor.props(), "supervisor");

        for (int i = 0; i < 10 ; i++) {
            supervisor.tell(new SupervisorActor.StartWork(), ActorRef.noSender());
        }

    }

}
