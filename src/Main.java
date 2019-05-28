import dao.*;
import domain.OVchipkaart;
import domain.Reiziger;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReizigerDao reizigerDao = new ReizigerSQLiteDaoImpl();
        OVchipkaartDao oVchipkaartDao = new OVchipkaartSQLiteDaoImpl();

        ArrayList<Reiziger> reizigers = reizigerDao.findAll();
        Reiziger reiziger = new Reiziger("T", "van der", "Meijs", reizigers.get(0).getGbDatum());

        /*

        CRUD STATEMENTS REIZIGER

         */

//        System.out.println("FINDALL");
//        reizigers.forEach((r) -> System.out.println(r.toString()));

//        System.out.println("Inserting");
//        System.out.println(reiziger.toString());
//        System.out.println("Saved: " + reizigerDao.save(reiziger));

//        System.out.println("Updating");
//        Reiziger reiziger1 = reizigers.get(0);
//        reiziger1.setAchternaam("Verandert");
//        System.out.println(reizigerDao.update(reiziger1));

//        System.out.println("Deleting");
//        System.out.println(reizigerDao.delete(reizigers.get(0)));


        /*

        CRUD STATEMENTS OVchipkaart

         */

//        ArrayList<OVchipkaart> oVchipkaarts = oVchipkaartDao.findAll();
//        OVchipkaart oVchipkaart = new OVchipkaart(57408, oVchipkaartDao.findAll().get(0).getGeldigTot(), 1, 100, reizigers.get(0));

//        System.out.println("FINDALL");
//        oVchipkaarts.forEach((r) -> System.out.println(r.toString() + r.getReiziger().toString()));

//        System.out.println("Inserting");
//        System.out.println("Saved: " + oVchipkaartDao.save(oVchipkaart));

//        System.out.println("Updating");
//        oVchipkaart.setSaldo(200);
//        System.out.println("Updated: " + oVchipkaartDao.update(oVchipkaart));

//        System.out.println("Deleting");
//        System.out.println("Deleted: " + oVchipkaartDao.delete(oVchipkaarts.get(0)));
    }
}
