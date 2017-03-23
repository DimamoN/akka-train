package terminating;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import terminating.actors.MasterActor;

public class App {

    public static void main(String[] args) {

        ActorSystem ac = ActorSystem.create("system");

        ActorRef master = ac.actorOf(MasterActor.props(),"master");
        master.tell(new MasterActor.StartWork(),ActorRef.noSender());


        //Check DeadLetterOffice
//        ac.stop(master);
//        master.tell(new SupervisorActor.StartWork(),ActorRef.noSender());
    }
}
