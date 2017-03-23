package twoActors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import twoActors.actors.MasterActor;
import twoActors.actors.WorkerActor;

public class App {

    public static void main(String[] args) {

        final ActorSystem ac = ActorSystem.create("system");

        ActorRef worker = ac.actorOf(WorkerActor.props(), "worker");
        ActorRef master = ac.actorOf(MasterActor.props(worker), "master");

        for (int i = 1; i < 10; i++) {
            master.tell(new StartWork(i),ActorRef.noSender());
        }
    }
}
