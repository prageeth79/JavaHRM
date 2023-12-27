import java.sql.ResultSet;

public class EducationQualification extends DB{
    public int id;
    public final Qualification qualification = new Qualification();
    public final Employee employee = new Employee();
    public String institute;

    public String result;

    public final Validator validator = new Validator();
    public EducationQualification load(int id){
        String sql = "SELECT * from educationqualification WHERE id = " + id ;
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[5];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);
            data[3] = rs.getString(4);
            data[4] = rs.getString(5);

            this.id = Integer.parseInt(data[0]);
            this.employee.load(data[1]);
            this.qualification.load(Integer.parseInt(data[2]));
            this.institute = data[3];
            this.result = data[4];

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(employee.code, qualification.id, institute, result);
    }

    public  boolean update(){
        return update(id, employee.code, qualification.id, institute, result);
    }

    public boolean delete(){
        return delete(id);
    }

    protected boolean save( String empCode, int qulification, String institute, String result){
        String sql = "Insert into  educationqualification Values(null,'" + empCode + "'," + qulification +
                ",'" + institute + "','" + result + "')";
        return super.save(sql);
    }
    protected boolean update(int id, String empCode, int qualification, String institute, String result){
        String sql = "Update educationqualification SET id = id, empCode = '" + empCode +
                "', qualification = " + qualification + ", institution ='" + institute + "',result ='" + result + "' where id = " + id;
        return super.update(sql);
    }

    protected boolean delete(int id){
        String sql = "Delete from educationqualification where id =" + id;
        return  super.delete(sql);
    }

  public boolean validate(){
        return true;
    }

    public String[][] qualificationDetailsAll(String code){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from educationqualification where empCode ='" + code + "'");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][4];
            rs = this.dbResult(con,"select eq.id,eq.institution,q.qualification,eq.result " +
                    "from educationqualification eq, qualification q " +
                    "where eq.qualification = q.id " +
                    "and eq.empCode ='" + code + "'");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                i++;
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][4];
    }
}
