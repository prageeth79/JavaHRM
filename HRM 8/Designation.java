import java.sql.ResultSet;

public class Designation extends DB{
    public String code;
    public String designation;
    public String[] load(String code){
        String sql = "SELECT * from designation WHERE code = '" + code + "'";
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);

            this.code = data[0];
            this.designation = data[1];

            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public String[][] designationDetailsAll(){
        try{
            //Connection con = this.dbConnect();
            ResultSet rs = this.dbResult(con,"select count(*) from Designation");
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
            //con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][2];
    }


}
