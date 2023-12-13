import java.sql.ResultSet;

public class Department extends DB{
    public String code;
    public String departmentName;
    public String telephoneNo;
    public String manager;

    public Department(){

    }

    public boolean validateCode(){
        return matchCode(code);
    }

    public boolean validateDeptName(){
        return matchDeptName(departmentName);
    }

    public boolean validateTelNo(){
        return matchTelNo(telephoneNo);
    }

    public boolean validateManager(){
        return matchManager(manager);
    }

    public boolean matchCode(String code){
        return code.matches("^\\d{4}$");
    }
    public boolean matchDeptName(String deptName){
        return deptName.matches("^[a-zA-Z ]*$");
    }
    public boolean matchTelNo(String telNo){
        return  telNo.matches("^\\d{10}$");
    }
    public boolean matchManager(String manager){
        return manager.matches("^[a-zA-Z .]*$");
    }
    public boolean validate(){
        return validateCode() && validateManager() && validateTelNo() && validateDeptName();
    }


    public Department load(String code){
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
            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(code,departmentName,telephoneNo,manager);
    }

    public  boolean update(){
        return update(code,departmentName,telephoneNo,manager);
    }

    public boolean delete(){
        return delete(code);
    }

    protected boolean save(String code, String deptName, String telNo, String manager){
        String sql = "Insert into department Values('" + code + "','" + deptName + "','" + telNo + "','" + manager + "')";
        return super.save(sql);
    }
    protected boolean update(String code, String deptName, String telNo, String manager){
        String sql = "Update department SET code = '" + code + "',depName = '" + deptName + "', tel = '" + telNo + "', manager = '" + manager + "'where code = '" + code + "'";
        return super.update(sql);
    }

    @Override
    protected boolean delete(String code){
        String sql = "Delete from department where code ='" + code + "'";
        return  super.delete(sql);
    }

    public String[][] departmentDetailsAll() {
        try {
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
            }
            return  data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][4];
    }


}
