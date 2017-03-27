package twoActorsFixed.actors;

import akka.actor.Props;
import akka.japi.Creator;
import common.LoggingActor;

public class WorkerActor extends LoggingActor {
    //protocol
    public static class WorkStatus {

        private final int workId;
        private final String status;

        public WorkStatus(int workId, String status) {
            this.workId = workId;
            this.status = status;
        }

        public int getWorkId() {
            return workId;
        }

        public String getStatus() {
            return status;
        }
    }

    public WorkerActor(){}

    public void onReceive(Object o) throws Throwable {

        if(o instanceof MasterActor.Work){
            final WorkStatus status = doWork((MasterActor.Work)o);
            getSender().tell(status, getSelf());
        } else {
            unhandled(o);
        }

    }

    private WorkStatus doWork(MasterActor.Work work){
        log.info("Working on task: " + work.getWorkId());
        return new WorkStatus(work.getWorkId(), "DONE");
    }

    public static Props props(){
        return Props.create(new Creator<WorkerActor>() {
            public WorkerActor create() throws Exception {
                return new WorkerActor();
            }
        });
    }

}
