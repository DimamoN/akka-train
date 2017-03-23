package scheduling;

import akka.actor.Props;
import common.LoggingActor;

public class Actor extends LoggingActor {

    @Override
    public void onReceive(Object o) throws Throwable {
         if ( o instanceof String ){
             log.info(o.toString());
         }
    }

    public static Props props(){
        return Props.create(Actor.class);
    }
}
