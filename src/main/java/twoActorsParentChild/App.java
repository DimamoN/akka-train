package twoActorsParentChild;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import twoActorsParentChild.actors.MasterActor;

/**
 * In this demo worker is a child of master actor
 */
public class App {

    public static void main(String[] args) {

        final ActorSystem ac = ActorSystem.create("system");

        ActorRef master = ac.actorOf(MasterActor.props(), "master");

        for (int i = 1; i < 10; i++) {
            master.tell(new StartWork(i),ActorRef.noSender());
        }
    }
}
