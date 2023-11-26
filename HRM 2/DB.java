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

    public String[][] designationDetailsAll(){
        try{
            Connection con = this.dbConnect();
            ResultSet rs = this.dbResult(con,"select count(*) from Designation");
            //rs.last();
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][2];
            rs = this.dbResult(con,"select * from Designation");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                i++;
                System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }
            con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][2];
    }

    public String[][] employeeDetailsAll() {
        try{
            Connection con = this.dbConnect();
            ResultSet rs= this.dbResult(con, "select count(*) from Employee");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][8];
            rs = this.dbResult(con, "select e.*,d.depName,de.desigName from Employee e,Department D, Designation De where e.Designation = De.code and e.Department = D.code");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                data[i][5] = rs.getString(6);
                data[i][6] = rs.getString(9);
                data[i][7] = rs.getString(10);
                i++;
                //System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }
            con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][8];
    }

    public String[][] departmentDetailsAll() {
        try {
            Connection con = this.dbConnect();
            ResultSet rs = this.dbResult(con, "select count(*) from Department");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][4];
            rs = this.dbResult(con, "select * from Department");
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                i++;
//                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            con.close();
            return  data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][4];
    }


}
