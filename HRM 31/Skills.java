import java.sql.ResultSet;

public class Skills extends DB{
    public int skillID;
    public String skillName;

    public String description;

    public final Validator validator = new Validator();
    public Skills load(int id){
        String sql = "SELECT * from skills WHERE skillId = " + id ;
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);

            this.skillID = Integer.parseInt(data[0]);
            this.skillName = data[1];
            this.description = data[2];

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(skillName,description);
    }

    public  boolean update(){
        return update(skillID, skillName, description);
    }

    public boolean delete(){
        return delete(skillID);
    }

    protected boolean save(String skilName, String description){
        String sql = "Insert into skills Values(null,'" + skilName + "','" + description + "')";
        return super.save(sql);
    }
    protected boolean update(int skillId, String skillName, String description){
        String sql = "Update skills SET sKillName = '" + skillName + "',description = '" + description + "'where skillId = " + skillId;
        return super.update(sql);
    }


    protected boolean delete(int skillID){
        String sql = "Delete from skills where skillId =" + skillID;
        return  super.delete(sql);
    }

    public boolean validate(){
        return true;
    }

   public String[][] skillsDetailsAll(){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from skills");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][3];
            rs = this.dbResult(con,"select * from skills");
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
