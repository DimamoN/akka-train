package supervision.actors;

import akka.actor.Props;
import common.LoggingActor;

public class DataWorkerActor extends LoggingActor {

    private int messages = 0;

    @Override
    public void onReceive(Object o) throws Throwable {

        if(o instanceof SupervisorActor.StartWork){
            connectToDB();
        }
    }

    private void connectToDB() throws Exception {
        messages++;
        if(messages == 3){
            throw new Exception("Connection fail");
        }
        log.info("Connected: " + messages);
    }

    public static Props props(){
        return Props.create(DataWorkerActor.class);
    }
}
