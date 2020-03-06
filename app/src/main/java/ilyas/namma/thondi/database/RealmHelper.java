package ilyas.namma.thondi.database;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public void save(final SpaceCraft spacecraft)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                SpaceCraft s=realm.copyToRealm(spacecraft);

            }
        });

    }

    //READ
    public ArrayList<String> retrieve()
    {
        ArrayList<String> spacecraftNames=new ArrayList<>();
        RealmResults<SpaceCraft> spacecrafts=realm.where(SpaceCraft.class).findAll();

        for(SpaceCraft s:spacecrafts)
        {
            spacecraftNames.add(s.getName());
        }

        return spacecraftNames;
    }
}