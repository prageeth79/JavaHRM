import java.sql.ResultSet;

public class Department extends DB{
    public String code;
    public String departmentName;
    public String telephoneNo;
    public String manager;

    public String[] load(String code){
        String sql = "SELECT * from department WHERE code = '" + code + "'";
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);
            data[3] = rs.getString(4);


            this.code = data[0];
            this.departmentName = data[1];
            this.telephoneNo = data[2];
            this.manager = data[3];
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public String[][] departmentDetailsAll() {
        try {
            //Connection con = this.dbConnect();
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
            //con.close();
            return  data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][4];
    }


}
