package dao;

import domain.OVchipkaart;
import domain.Reiziger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReizigerSQLiteDaoImpl extends SQLiteBaseDao implements ReizigerDao {
    @Override
    public ArrayList<Reiziger> findAll() {
        ArrayList<Reiziger> reizigers = new ArrayList<>();

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM REIZIGER " +
                        "LEFT JOIN OV_CHIPKAART ON " +
                        "reiziger.reizigerid = ov_chipkaart.reizigerid " +
                        "ORDER BY reiziger.reizigerid")
        ) {
            reizigers = createReizigerObject(statement.executeQuery());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reizigers;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        boolean finalised = false;

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO REIZIGER(voorletters, tussenvoegsel, achternaam, gebortedatum) VALUES(?, ?, ?, ?)")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsel());
            statement.setString(3, reiziger.getAchternaam());
            statement.setString(4, reiziger.getGbDatum().toString());

            if(statement.executeUpdate() != 0) { finalised = true; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalised;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        boolean finalised = false;

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("UPDATE REIZIGER SET VOORLETTERS = ?, TUSSENVOEGSEL = ?, ACHTERNAAM = ?, GEBORTEDATUM = ? WHERE REIZIGERID = ?")) {

            statement.setInt(5, reiziger.getId());
            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsel());
            statement.setString(3, reiziger.getAchternaam());
            statement.setString(4, reiziger.getGbDatum().toString());

            if(statement.executeUpdate() != 0) { finalised = true; }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return finalised;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        OVchipkaartDao oVchipkaartDao = new OVchipkaartSQLiteDaoImpl();
        boolean deleted = false;

        try(Connection conn = connect()) {
            if(!reiziger.getOvChipKaarten().isEmpty()) {
                reiziger.getOvChipKaarten().forEach(oVchipkaartDao::delete);
            }
            try(PreparedStatement statement = conn.prepareStatement("DELETE FROM REIZIGER WHERE REIZIGERID = ?")) {
                statement.setInt(1, reiziger.getId());

                if(statement.executeUpdate() != 0) {
                    deleted = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    private ArrayList<Reiziger> createReizigerObject(ResultSet result) throws SQLException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Reiziger> reizigers = new ArrayList<>();

        while(result.next()) {
            LocalDate gebortedatum = LocalDate.parse(result.getString("GEBORTEDATUM"), formatter);

            Reiziger reiziger = new Reiziger(result.getInt("REIZIGERID"),
                                             result.getString("VOORLETTERS"),
                                             result.getString("TUSSENVOEGSEL"),
                                             result.getString("ACHTERNAAM"),
                                             gebortedatum);

            if(!reizigers.contains(reiziger)) {
                reizigers.add(reiziger);
            }

            if(result.getInt("KAARTNUMMER") != 0) {
                LocalDate geldigtot = LocalDate.parse(result.getString("GELDIGTOT"), formatter);

                OVchipkaart oVchipkaart = new OVchipkaart(
                        result.getInt("KAARTNUMMER"),
                        geldigtot,
                        result.getInt("KLASSE"),
                        result.getInt("SALDO"),
                        reiziger);

                for(Reiziger r : reizigers) {
                    if(r.getId() == oVchipkaart.getReiziger().getId()) {
                        r.addOvChipKaarten(oVchipkaart);
                    }
                }
            }
        }

        result.close();

        return reizigers;
    }
}
