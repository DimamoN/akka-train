package terminating.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import common.LoggingActor;

public class MasterActor extends LoggingActor {

    //protocol
    public static class StartWork{}

    //child
    final ActorRef child = getContext().actorOf(WorkerActor.props(),"myChild");

    //Subscribe to watch child lifecycle
    {
        getContext().watch(child);
    }

    public void onReceive(Object o) throws Throwable {

        if (o instanceof StartWork){
            child.tell(new StartWork(),getSelf());
        }
        else if(o instanceof Terminated){
            final Terminated t = (Terminated) o;
            log.info(t.getActor().path() + " is terminated");
        }
    }

    public static Props props(){
        return Props.create(MasterActor.class);
    }

}
