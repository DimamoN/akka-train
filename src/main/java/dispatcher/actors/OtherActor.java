package dispatcher.actors;

import akka.actor.Props;
import common.LoggingActor;

/**
 * Created by dimamon on 3/27/17.
 */
public class OtherActor extends LoggingActor {


    @Override
    public void onReceive(Object o) throws Throwable {

        if(o instanceof String){
            log.info("Other work: " + o);
        }

    }

    public static Props props(){
        return Props.create(OtherActor.class).withDispatcher("oneThread-dispatcher");
    }
}
