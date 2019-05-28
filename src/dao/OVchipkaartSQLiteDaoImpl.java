package dao;

import domain.OVchipkaart;
import domain.Reiziger;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OVchipkaartSQLiteDaoImpl extends SQLiteBaseDao implements OVchipkaartDao {
    @Override
    public ArrayList<OVchipkaart> findAll() {
        ArrayList<OVchipkaart> oVchipkaarts = new ArrayList<>();

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM OV_CHIPKAART " +
                    "LEFT JOIN REIZIGER ON ov_chipkaart.reizigerid = reiziger.reizigerid " +
                    "ORDER BY reiziger.reizigerid")
        ) {
            oVchipkaarts = createOVchipkaartObject(statement.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oVchipkaarts;
    }

    @Override
    public boolean save(OVchipkaart oVchipkaart) {
        boolean saved = false;

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO OV_CHIPKAART VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, oVchipkaart.getKaartNummer());
            statement.setString(2, oVchipkaart.getGeldigTot().toString());
            statement.setInt(3, oVchipkaart.getKlasse());
            statement.setInt(4, (int) oVchipkaart.getSaldo());
            statement.setInt(5, oVchipkaart.getReiziger().getId());


            if(statement.executeUpdate() != 0) {
                saved = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }

    @Override
    public boolean update(OVchipkaart oVchipkaart) {
        boolean finalised = false;

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("UPDATE OV_CHIPKAART SET GELDIGTOT = ?, KLASSE = ?, SALDO = ?, REIZIGERID = ? WHERE KAARTNUMMER = ?")) {

            statement.setString(1, oVchipkaart.getGeldigTot().toString());
            statement.setInt(2, oVchipkaart.getKlasse());
            statement.setInt(3, (int) oVchipkaart.getSaldo());
            statement.setInt(4, oVchipkaart.getReiziger().getId());
            statement.setInt(5, oVchipkaart.getKaartNummer());

            int updated = statement.executeUpdate();

            if(updated != 0) {
                finalised = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalised;
    }

    @Override
    public boolean delete(OVchipkaart oVchipkaart) {
        boolean deleted = false;

        try(Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?")) {
            statement.setInt(1, oVchipkaart.getKaartNummer());

            if(statement.executeUpdate() != 0) { deleted = true; }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    private ArrayList<OVchipkaart> createOVchipkaartObject(ResultSet result) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<OVchipkaart> oVchipkaarts = new ArrayList<>();

        while(result.next()) {
            //Creating LocalDate object from ResultSet since SQLite return types in String yyyy-MM-dd format
            LocalDate gebortedatum = LocalDate.parse(result.getString("GEBORTEDATUM"), formatter);
            LocalDate geldigtot = LocalDate.parse(result.getString("GELDIGTOT"), formatter);

            Reiziger reiziger = new Reiziger(result.getInt("REIZIGERID"),
                                             result.getString("VOORLETTERS"),
                                             result.getString("TUSSENVOEGSEL"),
                                             result.getString("ACHTERNAAM"),
                                             gebortedatum);

            for (OVchipkaart oVchipkaart : oVchipkaarts) {
                if(oVchipkaart.getReiziger().equals(reiziger)) {
                    reiziger = oVchipkaart.getReiziger();
                }
            }

            OVchipkaart oVchipkaart = new OVchipkaart(result.getInt("KAARTNUMMER"),
                                                      geldigtot,
                                                      result.getInt("KLASSE"),
                                                      result.getInt("SALDO"),
                                                      reiziger);

            reiziger.addOvChipKaarten(oVchipkaart);

            if (!oVchipkaarts.contains(oVchipkaart)) {
                oVchipkaarts.add(oVchipkaart);
            }
        }
        result.close();

        return oVchipkaarts;
    }
}
