import java.sql.ResultSet;

public class Login extends DB{
    public String userName;
    public String password;
    public String oldPassword;
    public String newPassword;
    public String confPassword;
    public boolean isAdmin;
    public final Validator validator = new Validator();

    public Login(){

    }

    public String hashPassword(){
        return hashPassword(password);
    }

    public String hashPassword(String password){
        return Utility.hashPassword(password);
    }

    public boolean userLogin(String userName, String password){
        return load(userName).password.equals(hashPassword(password));
    }


    public  boolean resetPassword(){
        return update(userName,hashPassword(newPassword),isAdmin);
    }

    public Login load(String userName){
        String sql = "SELECT * from login WHERE userName = '" + userName + "'";

        ResultSet rs = dbResult(con, sql);
        String[] data = new String[4];
        try {
            rs.next();
            data[0] = rs.getString(1);
            data[1] = rs.getString(2);
            data[2] = rs.getString(3);

            this.userName = data[0];
            this.password = data[1];
            this.isAdmin =data[2].equals("1");

            return this;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public boolean save(){
        return save(userName,hashPassword(),isAdmin);
    }

    public  boolean update(){
        return update(userName,password,isAdmin);
    }

    public boolean delete(){
        return delete(userName);
    }

    protected boolean save(String userName, String password, boolean isAdmin){
        String sql = "Insert into login Values('" + userName + "','" + password + "','" + isAdmin + ")";
        return super.save(sql);
    }

    protected boolean update(String userName, String password, boolean isAdmin){
        String sql = "Update login SET userName = '" + userName + "',password = '" + password + "',isAdmin = " + isAdmin + " where userName = '" + userName + "'";
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

    public boolean validateNewPasswrod(){
        return matchPassword(newPassword);
    }

    public boolean validateOldPasswrod(){
        return matchPassword(oldPassword);
    }

    public boolean validateConfPassword(){
        return newPassword.equals(confPassword);
    }

    public boolean validateMatchNewConfPassword(){
        return newPassword.equals(confPassword);
    }

    public boolean validateMatchOldPassword(){
        return matchOldPassword(userName);
    }
    public boolean matchOldPassword(String userName){
        return load(userName).password.equals(hashPassword(oldPassword));
    }

    public boolean matchOldPassword(String userName, String oldPassword){
        return load(userName).password.equals(hashPassword(oldPassword));
    }

   public  boolean validatePasswordReset(boolean isNotComplex){
        return  validateMatchOldPassword() && validateMatchNewConfPassword() && (isNotComplex || validateNewPasswrod());
    }


    public boolean matchUserName(String userName){
        return !userName.isEmpty();
    }

    public boolean matchPassword(String password){
        return validator.matchSimpleCharactors(password) &&
                validator.matchCapicalCharactors(password) &&
                validator.matchANumber(password) &&
                validator.matchSpecialCharacters(password) &&
                (password.length() >=8);
    }

    public boolean validate(boolean isNotComplex){
        return validateUserName() && (isNotComplex || validatePasswrod()) && password.equals(confPassword);
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
