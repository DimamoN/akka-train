package supervision.actors;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import common.LoggingActor;
import scala.concurrent.duration.Duration;

public class SupervisorActor extends LoggingActor {

    //protocol
    public static class StartWork{}

    final ActorRef worker = getContext().actorOf(DataWorkerActor.props(), "worker");


    @Override
    public void onReceive(Object o) throws Throwable {

        if(o instanceof StartWork){
            worker.forward(o,getContext());
        }

    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(
                10,
                Duration.create("10 seconds"),
                DeciderBuilder
                        .match(Exception.class, ex -> SupervisorStrategy.stop())
                        .build()
        );
    }

    public static Props props(){
        return Props.create(SupervisorActor.class);
    }

}
