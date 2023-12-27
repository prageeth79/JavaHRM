import javax.swing.*;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.Vector;

public class Utility {

    public static String hashPassword(String password){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] digest = md5.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0;i<digest.length;i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static boolean fillCombo(ResultSet rs,JComboBox combo){
        combo.removeAllItems();
        try {
            while (rs.next()) {
                String item = craeateItem(rs.getString(0) , rs.getString(1));
                combo.addItem(item);
            }
            return true;
        }catch(Exception e){}
        return  false;
    }
    public static String craeateItem(String code, String item){
        return  code + " - " + item;
    }
    public static String getComboID(String item){
        return item.substring(0,item.indexOf("-"));
    }
}


