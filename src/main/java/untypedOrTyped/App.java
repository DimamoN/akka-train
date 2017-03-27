package untypedOrTyped;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.japi.Creator;
import untypedOrTyped.typedActor.HasName;
import untypedOrTyped.typedActor.Named;


public class App {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("system");

        //Default constructor
        HasName namedActor = TypedActor.get(system).typedActorOf(
                new TypedProps<>(HasName.class,Named.class));

        String name = namedActor.name();
        System.out.println(name);


        //Custom constructor
        HasName namedActor2 = TypedActor.get(system).typedActorOf(
                        new TypedProps<>(HasName.class, (Creator<Named>) () -> new Named("joy")));

        String name2 = namedActor2.name();
        System.out.println(name2);
    }
}
