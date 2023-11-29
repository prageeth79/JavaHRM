import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.Hashtable;

public class Employee extends DB{

    public String code;
    public String fullName;
    public String nameWithInit;
    public String nic;
    public String dateOfBirth;
    public String gender;
    public String designation;
    public String department;
    public Dictionary<String,Integer> fields = new Hashtable<>();
    public Employee(){
        fields.put("code",1);
        fields.put("fullName",2);
        fields.put("NameWIthInit",3);
        fields.put("NIC",4);
        fields.put("dofb",5);
        fields.put("Gender",6);
        fields.put("Designation",7);
        fields.put("Department",8);
    }

    public boolean save(){
        return save(code,fullName,nameWithInit,nic,dateOfBirth,gender,designation,department);
    }

    public  boolean update(){
        return update(code,fullName,nameWithInit,nic,dateOfBirth,gender,designation,department);
    }

    public boolean delete(){
        return delete(code);
    }

    public boolean save(String code, String fullName, String nameWithInit, String nic, String DofB, String gender, String desig, String dept){
        String sql = "Insert into employee Values('" + code + "','" + fullName + "','" + nameWithInit + "','" + nic + "','" + DofB + "','" + gender + "','" + desig +"','" + dept + "')";
        return super.save(sql);
    }
    public boolean update(String code, String fullName, String nameWithInit, String nic, String DofB, String gender, String desig, String dept){
        String sql = "Update employee SET fullName = '" + fullName + "',nameWithInit = '" + nameWithInit + "', NIC = '" + nic + "', dofb = '" + DofB + "', Gender = '" + gender + "', Designation = '" + dept +"', Department = '" + desig + "' WHERE code = '" + code + "'";
        return super.update(sql);
    }

    @Override
    public boolean delete(String code){
        String sql = "Delete from employee where code ='" + code + "'";
        return  super.delete(sql);
    }

    public String[] load(String code){
        String sql = "SELECT * from employee WHERE code = '" + code + "'";
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[8];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);
            data[3] = rs.getString(4);
            data[4] = rs.getString(5);
            data[5] = rs.getString(6);
            data[6] = rs.getString(7);
            data[7] = rs.getString(8);

            this.code = data[0];
            this.fullName = data[1];
            this.nameWithInit = data[2];
            this.nic = data[3];
            this.dateOfBirth = data[4];
            this.gender = data[5];
            this.designation = data[6];
            this.department = data[7];
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

    public String[][] employeeDetailsAll() {
        try{
            //Connection con = this.dbConnect();
            ResultSet rs= this.dbResult(con, "select count(*) from Employee");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][8];
            rs = this.dbResult(con, "select e.*,d.depName,de.desigName from Employee e,Department D, Designation De where e.Designation = De.code and e.Department = D.code order by code");
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
            //con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][8];
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
