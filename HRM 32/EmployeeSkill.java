import java.sql.ResultSet;

public class EmployeeSkill  extends DB{
    public int id;
    public Skill skill = new Skill();
    public  Employee employee = new Employee();
    public String proficiency;

    public final Validator validator = new Validator();

    public EmployeeSkill(){

    }
    public EmployeeSkill load(int id){
        String sql = "SELECT * from skills WHERE skillId = " + id ;
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);

            this.id = Integer.parseInt(data[0]);
            this.skill.load(Integer.parseInt(data[1]));
            this.employee.load(data[2]);
            this.proficiency = data[3];

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(skill.skillID,employee.code, proficiency);
    }

    public  boolean update(){
        return update(id,skill.skillID, employee.code, proficiency);
    }

    public boolean delete(){
        return delete(id);
    }

    protected boolean save(int skillId, String employeeCode, String proficiency){
        String sql = "Insert into employeeSkills Values(null," + skillId + ",'" + employeeCode + "','" + proficiency + "')";
        return super.save(sql);
    }
    protected boolean update(int id,int skillId, String empCode, String proficiency){
        String sql = "Update employeeSkills SET skillId =" + skillId + " employeeid = '" + empCode + "',proficiency = '" + proficiency + "'where id = " + id;
        return super.update(sql);
    }


    protected boolean delete(int id){
        String sql = "Delete from employeeSkills where id =" + id;
        return  super.delete(sql);
    }

    public boolean validate(){
        return true;
    }

    public String[][] skillsDetailsAll(String code){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from employeeSkills where employeeid = '" + code + "'");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][3];
            rs = this.dbResult(con,"select es.id,s.skillName,es.proficiency from employeeSkills es, skills s, employee e " +
                    "where es.skillId = s.skillId and es.employeeid = e.code and es.employeeid = '" + code + "'");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                i++;
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][3];
    }

}
