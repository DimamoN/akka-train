package terminating.actors;

import akka.actor.Props;
import common.LoggingActor;

public class WorkerActor extends LoggingActor {

    public void onReceive(Object o) throws Throwable {

        if( o instanceof MasterActor.StartWork){
            doStaff();

            //Work done, terminating
            getContext().stop(getSelf());
        }
    }

    private void doStaff() throws InterruptedException {
        log.info("working");
        Thread.sleep(2000);
        log.info("work done");
    }

    public static Props props(){
        return Props.create(WorkerActor.class);
    }

}
