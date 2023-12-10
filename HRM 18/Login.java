import java.sql.ResultSet;

public class Login extends DB{
    public String userName;
    public String password;
    public String confPassword;

    public boolean userLogin(String userName, String password){
        return load(userName).password.equals(password);
    }

    public Login load(String userName){
        String sql = "SELECT * from login WHERE userName = '" + userName + "'";
        //Connection con = dbConnect();

        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);

            this.userName = data[0];
            this.password = data[1];

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(userName,password);
    }

    public  boolean update(){
        return update(userName,password);
    }

    public boolean delete(){
        return delete(userName);
    }

    protected boolean save(String userName, String password){
        String sql = "Insert into login Values('" + userName + "','" + password + "')";
        return super.save(sql);
    }
    protected boolean update(String userName, String password){
        String sql = "Update login SET userName = '" + userName + "',password = '" + password + "'where userName = '" + userName + "'";
        return super.update(sql);
    }

    @Override
    protected boolean delete(String userName){
        String sql = "Delete from login where userName ='" + userName + "'";
        return  super.delete(sql);
    }

    public boolean validateUserName(){
        return matchUserName(userName);
    }

    public boolean validatePasswrod(){
        return matchPassword(password);
    }
    public boolean matchUserName(String userName){
        return !userName.matches("");
    }
    public boolean matchPassword(String password){
        return password.matches("[a-z]") && password.matches("[A-Z]") && password.matches("[0-9]") && password.matches("[&%$#@^*?+-=!|:;~]") && (password.length() >=8);
    }

    public boolean validate(){
        return validateUserName() && validatePasswrod() && password.equals(confPassword);
    }

    public String[][] loginDetailsAll(){
        try{
            ResultSet rs = this.dbResult(con,"select count(*) from login");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][2];
            rs = this.dbResult(con,"select * from login");
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
