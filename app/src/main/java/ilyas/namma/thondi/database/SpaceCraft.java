package ilyas.namma.thondi.database;

import io.realm.RealmObject;

public class SpaceCraft extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}