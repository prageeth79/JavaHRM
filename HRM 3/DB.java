import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    Connection con;
    public DB(){
        con = dbConnect();
    }

    public Connection dbConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm", "root", "");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ResultSet dbResult(String sql){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean beginTranse(){
        try {
            con.setAutoCommit(false);
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean commit(){
        try{
            con.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public  boolean rolleback(){
        try{
            con.rollback();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean setSavePoint(){
        try{
            con.setSavepoint();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return  false;
    }

    public ResultSet dbResult(Connection con, String sql){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
