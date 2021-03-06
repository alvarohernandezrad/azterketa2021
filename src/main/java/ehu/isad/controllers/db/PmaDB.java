package ehu.isad.controllers.db;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PmaDB {

    private static PmaDB instantzia = new PmaDB();

    public static PmaDB getInstantzia(){
        return instantzia;
    };

    private PmaDB(){}

    private DBController dbController = DBController.getInstantzia();

    public boolean dago(String md5){
        String query = "select idCMS from checksums where md5 = '"+md5+"';";
        ResultSet rs = dbController.execSQL(query);
        int i = 0;
        try {
            i = rs.getInt("idCMS");
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(i==1){
            return true;
        }else{
            return false;
        }
    }

    public String bertsioa(String md5){
        String query = "select version from checksums where md5 = '"+md5+"';";
        ResultSet rs = dbController.execSQL(query);
        String bertsioa;
        try{
            bertsioa = rs.getString("version");
            return bertsioa;
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public void bertsioBerriaSartu(String md5, String bertsioa){
        String query = "insert into checksums (idCMS, version, md5, path) values (1,'"+bertsioa+"','"+md5+"','README')";
        DBController.getInstantzia().execSQL(query);
    }
}