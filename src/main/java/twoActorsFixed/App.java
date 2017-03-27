package twoActorsFixed;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import twoActorsFixed.actors.MasterActor;
import twoActorsFixed.actors.WorkerActor;

public class App {

    public static void main(String[] args) {

        final ActorSystem ac = ActorSystem.create("system");

        ActorRef master = ac.actorOf(MasterActor.props(), "master");

        for (int i = 1; i < 10; i++) {
            master.tell(new StartWork(i),ActorRef.noSender());
        }
    }
}
