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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WorkStatus that = (WorkStatus) o;

            if (workId != that.workId) return false;
            return status != null ? status.equals(that.status) : that.status == null;

        }

        @Override
        public int hashCode() {
            int result = workId;
            result = 31 * result + (status != null ? status.hashCode() : 0);
            return result;
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

    public WorkStatus doWork(MasterActor.Work work){
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
