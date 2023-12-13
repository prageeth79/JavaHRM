import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JDialog implements ActionListener {
    private JTextField txtuserName;
    private JPasswordField txtPassword;
    private JLabel lblUserName,lblPassword;
    private Font fntLblFont,fntTxtFont,fntBtnFont;
    private JButton btnLogin, btnCancel;

    private final Login db = new Login();

    private PersonalInfo owner;
    public  UserLogin(PersonalInfo owner){
        super(owner,"User Login",true);
        setWindow();
        setComponents();
        addComponents();
        this.owner = owner;
        this.setVisible(true);
    }

    private void setWindow(){
        this.setTitle("UserLogin");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(430,150);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    private  void setComponents() {

        fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        lblUserName = new JLabel("Username");
        lblUserName.setBounds(10, 10, 150, 25);
        lblUserName.setFont(fntLblFont);

        txtuserName = new JTextField();
        txtuserName.setBounds(150, 10, 250, 25);
        txtuserName.setFont(fntTxtFont);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 40, 250, 25);
        lblPassword.setFont(fntLblFont);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 40, 250, 25);
        txtPassword.setFont(fntTxtFont);

        ImageIcon ilogin = new ImageIcon("login.png");
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(170, 70, 110, 30);
        btnLogin.setFont(fntBtnFont);
        btnLogin.setIcon(ilogin);
        btnLogin.addActionListener(this);

        ImageIcon iexit = new ImageIcon("close.png");
        btnCancel = new JButton("EXIT");
        btnCancel.setBounds(290, 70, 110, 30);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(iexit);
        btnCancel.addActionListener(this);
    }

    private void addComponents(){
        this.add(lblUserName);
        this.add(txtuserName);
        this.add(lblPassword);
        this.add(txtPassword);
        this.add(btnLogin);
        this.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin){
            if(db.userLogin(txtuserName.getText(),new String(txtPassword.getPassword()))) {
                this.owner.user = txtuserName.getText();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"invalided username or password","Error LOgin",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == btnCancel) System.exit(0);
    }
}
