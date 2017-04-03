package creatingActors;

import akka.actor.Props;
import akka.japi.Creator;
import common.LoggingActor;

public class MyActor extends LoggingActor {

    private String text;
    private int days;

    public MyActor(String text, int days) {
        this.text = text;
        this.days = days;
    }

    @Override
    public void onReceive(Object o) throws Throwable {

        if(o instanceof String){
            log.info("hello " + o);
        } else {
            log.warning("unhandled message: " + o);
            unhandled(o);
        }

    }

    public static Props props(final String text, final int days){
        return Props.create(new Creator<MyActor>() {
            public MyActor create() throws Exception {
                return new MyActor(text,days);
            }
        });
    }

}
