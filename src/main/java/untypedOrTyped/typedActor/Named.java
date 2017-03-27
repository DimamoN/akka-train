package untypedOrTyped.typedActor;

import java.util.Random;

public class Named implements HasName {

    private int id = new Random().nextInt(1024);
    private String name;

    public Named() {
        this.name = "actor";
    }

    public Named(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name + "-" + id;
    }

}
