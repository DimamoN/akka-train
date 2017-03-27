package dispatcher.actors;

import akka.actor.Props;
import common.LoggingActor;

public class DBActor extends LoggingActor {

    @Override
    public void onReceive(Object o) throws Throwable {

        if(o instanceof String){
            log.info("working with db " + o);
            Thread.sleep(5000);
        }

    }

    public static Props props(){
        return Props.create(DBActor.class).withDispatcher("oneThread-dispatcher");
//        return Props.create(DBActor.class).withDispatcher("blocking-dispatcher");
    }

}
