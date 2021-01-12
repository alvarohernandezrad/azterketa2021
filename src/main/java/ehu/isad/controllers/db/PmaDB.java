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

    /*public ObservableList<Gunea> getList(){
        ObservableList<Gunea> list = FXCollections.observableArrayList();
        String query = "Select * from captchas;";
        ResultSet rs = dbController.execSQL(query);
        try{
            String path,content;
            Integer id;
            Date date;
            while(rs.next()){
                id = rs.getInt("id");
                path = rs.getString("filename");
                content = rs.getString("value");
                date = rs.getDate("date");
                list.add(new Gunea(id,path,date,content));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void updateRow(Gunea model){
        String query = "update captchas set value = '"+model.getContent()+"' where id = "+model.getId()+";";
        dbController.execSQL(query);
    }*/

   /* public void addRow(Gunea model){
        String query = "insert into checksum values("+model.getId()+",'"+model.getPath()+"',"+model.getMd5()+",'"+model.getVersion()+"');";
        dbController.execSQL(query);
    }*/

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