package twoActors.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.Creator;
import common.LoggingActor;
import twoActors.StartWork;

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

    private ActorRef worker;

    public MasterActor(ActorRef worker) {
        this.worker = worker;
    }

    public void onReceive(Object o) throws Throwable {

        if(o instanceof StartWork){
            log.info("Starting new work");
            Work work = new Work(((StartWork)o).getWorkId(), Arrays.asList(1,2,3));
            worker.tell(work,getSelf());
        }

        if(o instanceof WorkerActor.WorkStatus){

            WorkerActor.WorkStatus workStatus  = (WorkerActor.WorkStatus)o;

            if((workStatus.getStatus() == "DONE")) {
                log.info("Work [" + workStatus.getWorkId() + "] is done");
            }

        } else unhandled(o);

    }

    public static Props props(final ActorRef worker){
        return Props.create(new Creator<MasterActor>() {
            public MasterActor create() throws Exception {
                return new MasterActor(worker);
            }
        });
    }

}
