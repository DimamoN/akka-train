package terminating;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import terminating.actors.MasterActor;

/**
 * Master is watching to Workers lifecycle
 * This mean, than if worker stop -> master will receive TERMINATED message
 */
public class App {

    public static void main(String[] args) {

        ActorSystem ac = ActorSystem.create("system");

        ActorRef master = ac.actorOf(MasterActor.props(),"master");
        master.tell(new MasterActor.StartWork(),ActorRef.noSender());


        //Check DeadLetterOffice
//        ac.stop(master);
//        master.tell(new SupervisorActor.StartWork(),ActorRef.noSender());

        ac.terminate();
    }
}
