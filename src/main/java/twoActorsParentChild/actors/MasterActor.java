package twoActorsParentChild.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import common.LoggingActor;
import twoActorsParentChild.StartWork;

import java.util.Arrays;
import java.util.List;

/**
 * SupervisorActor send work to WorkerActor
 */
public class MasterActor extends LoggingActor{
    //protocol
    public static class Work {

        private final int workId;
        private final List<Integer> data;

        public Work(int workId, List<Integer> data) {
            this.workId = workId;
            this.data = data;
        }

        public int getWorkId() {
            return workId;
        }

        public List<Integer> getData() {
            return data;
        }
    }

    //child
    final ActorRef worker = getContext().actorOf(WorkerActor.props(), "worker");

    public MasterActor() {}

    public void onReceive(Object o) throws Throwable {

        if(o instanceof StartWork){
            log.info("Starting new work");
            final Work work = new Work(((StartWork)o).getWorkId(), Arrays.asList(1,2,3));
            worker.tell(work,getSelf());
        }

        if(o instanceof WorkerActor.WorkStatus){

            WorkerActor.WorkStatus workStatus  = (WorkerActor.WorkStatus)o;

            if((workStatus.getStatus() == "DONE")) {
                log.info("Work [" + workStatus.getWorkId() + "] is done");
            }

        } else unhandled(o);

    }

    public static Props props(){
       return Props.create(MasterActor.class);
    }

}
