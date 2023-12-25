import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    Connection con;
    public DB(){
        con = dbConnect();
    }

    protected Connection dbConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm", "root", "");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    protected boolean save(String sql){
        return dbExecute(sql);
    }

    protected  boolean update(String sql){
        return dbExecute(sql);
    }

    protected boolean delete(String sql){
        return dbExecute(sql);
    }

    protected boolean dbExecute(String sql){
        try{
            //Connection con = dbConnect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    protected ResultSet dbResult(String sql){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    protected boolean beginTrans(){
        try {
            con.setAutoCommit(false);
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    protected boolean endTrans(){
        try{
            con.setAutoCommit(true);
            return  true;
        }catch (Exception e){
            System.out.println(e);
        }
        return  false;
    }
    protected boolean commit(){
        try{
            con.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    protected  boolean rolleback(){
        try{
            con.rollback();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    protected boolean setSavePoint(){
        try{
            con.setSavepoint();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return  false;
    }

    protected ResultSet dbResult(Connection con, String sql){
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
