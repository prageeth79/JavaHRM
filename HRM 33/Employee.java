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
    public String dateOfAppointment;
    public String dateMadePermenant;
    public String incrementDate;
    public String personalFileNo;
    public String eClass;
    public String grade;
    public boolean ebI;
    public boolean ebII;
    public String status;
    public String owner;
     public Designation designation = new Designation();
    public Department department = new Department();
    public Dictionary<String,Integer> fields = new Hashtable<>();
    public final Validator validator = new Validator();
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

    private  boolean validateCode(){
        return validator.matchNumber(code);
    }
    private boolean validateFullName(){
        return validator.matchCharacters(fullName);
    }
    private boolean validateNameWithInit(){
        return  validator.matchCharactersAndDot(nameWithInit);
    }
    private boolean validateNic(){
        return validator.matchNicOld(nic) || validator.matchNicNew(nic);
    }
    private boolean validateDateOfBirth(){
        return validator.matchDate(dateOfBirth);
    }
    private boolean validateDateMadePermenant(){
        return validator.matchDate(dateMadePermenant);
    }
    public boolean validateAppointmentDate(){
        return validator.matchDate(dateOfAppointment);
    }
    public boolean validateIncrementDate(){
        return validator.matchDate(incrementDate);
    }
    public  boolean validatePersonalFileNo(){
        return !personalFileNo.isEmpty();
    }
    public boolean validate(){
        return validateCode() && validateFullName() && validateNameWithInit() && validateNic() &&
                validateDateOfBirth() && validateAppointmentDate() && validateDateMadePermenant() &&
                validateIncrementDate() && validatePersonalFileNo();
    }

    public boolean save(){
        return save(code,fullName,nameWithInit,nic,dateOfBirth,gender,designation.code,department.code,
                dateOfAppointment, dateMadePermenant, personalFileNo, incrementDate,eClass, grade, ebI, ebII, status, owner);
    }

    public  boolean update(){
        return update(code,fullName,nameWithInit,nic,dateOfBirth,gender,designation.code,department.code,
                dateOfAppointment, dateMadePermenant, personalFileNo, incrementDate,eClass, grade, ebI, ebII, status, owner);
    }

    public boolean delete(){
        return delete(code);
    }

    protected boolean save(String code, String fullName, String nameWithInit, String nic, String DofB,
                           String gender, String desig, String dept,String dateOfAppointment, String dateMadePermanent,
                           String personalFileNo, String incrementDate, String eClass, String grade, boolean ebI,
                           boolean ebII, String status, String owner){
        String sql = "Insert into employee Values('" + code + "','" + fullName + "','" +
                nameWithInit + "','" + nic + "','" + DofB + "','" + gender + "','" + desig +"','" + dept + "','" +
                dateOfAppointment + "','" + dateMadePermanent + "','" + personalFileNo + "','" + incrementDate + "','" +
                eClass + "','" + grade + "'," + ebI + "," +ebII + ",'" + status + "','" + owner + "')";
        return super.save(sql);
    }
    protected boolean update(String code, String fullName, String nameWithInit, String nic, String DofB,
                             String gender, String desig, String dept,String dateOfAppointment, String dateMadePermanent,
                             String personalFileNo, String incrementDate, String eClass, String grade, boolean ebI,
                             boolean ebII, String status, String owner){
        String sql = "Update employee SET fullName = '" + fullName + "',nameWithInit = '" +
                nameWithInit + "', NIC = '" + nic + "', dofb = '" + DofB + "', Gender = '" + gender +
                "', Designation = '" + desig +"', Department = '" + dept + "', user = '" + owner +
                "', dateOfApointment = '" + dateOfAppointment + "', dateMadePermenant = '" + dateMadePermanent +
                "', personalFileNo = '" + personalFileNo + "', incrementDate = '" + incrementDate +
                "', class = '" + eClass + "', grade = '" + grade + "',ebI = " + ebI + ", ebII = " + ebII +
                ",status = '" + status + "' WHERE code = '" + code + "'";
        return super.update(sql);
    }

    @Override
    protected boolean delete(String code){
        String sql = "Delete from employee where code ='" + code + "'";
        return  super.delete(sql);
    }

    public Employee load(String code){
        String sql = "SELECT * from employee WHERE code = '" + code + "'";

        ResultSet rs = dbResult(con, sql);
        String[] data = new String[18];
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
            data[8] = rs.getString(9);
            data[9] = rs.getString(10);
            data[10] = rs.getString(11);
            data[11] = rs.getString(12);
            data[12] = rs.getString(13);
            data[13] = rs.getString(14);
            data[14] = rs.getString(15);
            data[15] = rs.getString(16);
            data[16] = rs.getString(17);
            data[17] = rs.getString(18);

            this.code = data[0];
            this.fullName = data[1];
            this.nameWithInit = data[2];
            this.nic = data[3];
            this.dateOfBirth = data[4];
            this.gender = data[5];
            this.designation.load(data[6]);
            this.department.load(data[7]);
            this.dateOfAppointment = data[8];
            this.dateMadePermenant = data[9];
            this.personalFileNo = data[10];
            this.incrementDate = data[11];
            this.eClass = data[12];
            this.grade = data[13];
            this.ebI = data[14].equals("1");
            this.ebII = data[15].equals("1");
            this.status = data[16];
            this.owner = data[17];
            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public String[][] employeeDetailsAll(String userName) {
        System.out.println(userName);
        try{
            ResultSet rs= this.dbResult(con, "select count(*) from Employee where user like '" + userName + "'");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][8];
            rs = this.dbResult(con, "select e.*,d.depName,de.desigName from Employee e,Department D, " +
                    "Designation De where e.Designation = De.code and e.Department = D.code " +
                    " and e.user like '" + userName + "' order by e.code");
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
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][8];
    }
}
