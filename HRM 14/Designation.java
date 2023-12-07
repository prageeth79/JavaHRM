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

    public boolean save(){
        return save(code,designation);
    }

    public  boolean update(){
        return update(code,designation);
    }

    public boolean delete(){
        return delete(code);
    }

    public boolean save(String code, String designation){
        String sql = "Insert into designation Values('" + code + "','" + designation + "')";
        return super.save(sql);
    }
    public boolean update(String code, String designation){
        String sql = "Update designation SET code = '" + code + "',depName = '" + designation + "', tel = '" + "'where code = '" + code + "'";
        return super.update(sql);
    }

    @Override
    public boolean delete(String code){
        String sql = "Delete from designation where code ='" + code + "'";
        return  super.delete(sql);
    }

    public boolean validateCode(){
        return matchCode(code);
    }

    public boolean validateDesigName(){
        return matchDesigName(designation);
    }
    public boolean matchCode(String code){
        return code.matches("^\\d{4}$");
    }
    public boolean matchDesigName(String desigName){
        return desigName.matches("^[a-zA-Z ]*$");
    }

    public boolean validate(){
        return validateCode() && validateDesigName();
    }

    public String[][] designationDetailsAll(){
        try{
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
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][2];
    }


}
