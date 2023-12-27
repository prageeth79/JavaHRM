import java.sql.ResultSet;

public class Qualification extends DB{
    public int id;
    public String qualification;
    public String type;

    public final Validator validator = new Validator();
    public Qualification load(int id){
        String sql = "SELECT * from qualification WHERE id = " + id ;
        //Connection con = dbConnect();
        ResultSet rs = dbResult(con, sql);
        String[] data = new String[3];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);

            this.id = Integer.parseInt(data[0]);
            this.qualification = data[1];
            this.type = data[2];

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public String[] eduQualiCombo(){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from qualification");
            rs.next();
            int rows = rs.getInt(1);
            String[] data = new String[rows];
            String sql = "Select id, qualification from qualification";
            rs = dbResult(con, sql);
            int i = 0;
            while(rs.next()) {
                data[i] = Utility.craeateItem(rs.getString(1) ,
                        rs.getString(2));
                i++;
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1];
    }

    public boolean save(){
        return save(qualification, type);
    }

    public  boolean update(){
        return update(id, qualification, type);
    }

    public boolean delete(){
        return delete(id);
    }

    protected boolean save( String qulification, String type){
        String sql = "Insert into  qualification Values(null,'" + qulification + "','" + type + "')";
        return super.save(sql);
    }
    protected boolean update(int id, String qualification, String type){
        String sql = "Update qualification SET id = id, qualification = '" + qualification + "', type ='" + type + "' where id = " + id;
        return super.update(sql);
    }

    protected boolean delete(int id){
        String sql = "Delete from qualification where id =" + id;
        return  super.delete(sql);
    }


    public boolean validate(){
        return true;
    }

    public String[][] qualificationDetailsAll(){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from qualification");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][3];
            rs = this.dbResult(con,"select * from qualification");
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
