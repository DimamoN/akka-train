package common;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class LoggingActor extends UntypedActor {

    protected LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object o) throws Throwable {

    }
}
