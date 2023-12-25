import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ChangePassword extends JDialog implements ActionListener {
    private JLabel lblUserName, lblPassword, lblNewPassword, lblConfPassword,lblCompexPassword;
    private JTextField txtUserName;
    private  JPasswordField txtPassword, txtNewPassword, txtConfPassword;
    private JButton btnNew, btnSave, btnCancel;
    private JCheckBox chkComplexPassword;
    private final String[] loginFieldNames = {"UserName", "Password"};
    private final Login db = new Login();

    public ChangePassword(PersonalInfo owner){
        super(owner,"Add New User",true);
        setWindow();
        setComponents(db);
        addComponent();
        if(!owner.isAdmin){
            txtUserName.setText(owner.user);
            txtUserName.setEditable(false);
        }
        addTextBoxForcus();
        this.setVisible(true);
    }
    private void setWindow(){
        this.setTitle("Change Password");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(480,250);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    private  void setComponents(Login db) {

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        lblUserName = new JLabel("Username");
        lblUserName.setBounds(10, 10, 150, 25);
        lblUserName.setFont(fntLblFont);

        txtUserName = new JTextField();
        txtUserName.setBounds(200, 10, 250, 25);
        txtUserName.setFont(fntTxtFont);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 40, 250, 25);
        lblPassword.setFont(fntLblFont);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 40, 250, 25);
        txtPassword.setFont(fntTxtFont);

        lblNewPassword = new JLabel("New Password");
        lblNewPassword.setBounds(10, 70, 250, 25);
        lblNewPassword.setFont(fntLblFont);

        txtNewPassword = new JPasswordField();
        txtNewPassword.setBounds(200, 70, 250, 25);
        txtNewPassword.setFont(fntTxtFont);

        lblCompexPassword = new JLabel("Remove Password Complexity");
        lblCompexPassword.setBounds(10, 100, 250, 25);
        lblCompexPassword.setFont(fntLblFont);
        chkComplexPassword = new JCheckBox();
        chkComplexPassword.setBounds(270, 100, 30, 30);
        chkComplexPassword.setSize(30,30);
        chkComplexPassword.setFont(fntLblFont);

        lblConfPassword = new JLabel("Conform Password");
        lblConfPassword.setBounds(10, 130, 150, 25);
        lblConfPassword.setFont(fntLblFont);

        txtConfPassword = new JPasswordField();
        txtConfPassword.setBounds(200, 130, 250, 25);
        txtConfPassword.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("new.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 160, 120, 40);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 160, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(330, 160, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);
    }

    private void clearFields(){
        txtUserName.setText("");
        txtPassword.setText("");
        txtConfPassword.setText("");
        txtNewPassword.setText("");
    }

    private void loadTextbox(){
        txtUserName.setText(db.userName);
        txtPassword.setText(db.oldPassword);
        txtNewPassword.setText(db.newPassword);
        txtConfPassword.setText(db.confPassword);
    }

    private void dataAdd(){
        db.userName = txtUserName.getText();
        db.oldPassword = new String(txtPassword.getPassword());
        db.newPassword = new String(txtNewPassword.getPassword());
        db.confPassword = new String(txtConfPassword.getPassword());
    }

    private void addComponent(){
        this.add(lblUserName);
        this.add(txtUserName);
        this.add(lblPassword);
        this.add(txtPassword);
        this.add(lblNewPassword);
        this.add(txtNewPassword);
        this.add(lblConfPassword);
        this.add(txtConfPassword);

        this.add(lblCompexPassword);
        this.add(chkComplexPassword);

        this.add(btnCancel);
        this.add(btnNew);
        this.add(btnSave);

    }

    private void addTextBoxForcus() {
        ChangePassword me = this;
        txtUserName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchUserName(txtUserName.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid username it shold not be empty",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchOldPassword(txtUserName.getText(), new String(txtPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(me, "given password dose not match with old password",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtNewPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!chkComplexPassword.isSelected() && !db.matchPassword(new String(txtNewPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(me, "Invalid password it should be more than 8 " +
                                    "characters long and should contain capital letter, simple letter, number and special symbol",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtConfPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                String newPassword = new String(txtNewPassword.getPassword());
                String confPassword = new String(txtConfPassword.getPassword());

                if (!newPassword.equals(confPassword)) {
                    JOptionPane.showMessageDialog(me, "password and conform password does not match",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNew){
            clearFields();
        }
        if(e.getSource() == btnSave){
            dataAdd();
            if(!db.validatePasswordReset(chkComplexPassword.isSelected())) {
                JOptionPane.showMessageDialog(this,"Data validation Fail",
                        "Validator",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(db.resetPassword()) {
                JOptionPane.showMessageDialog(this,
                        "Password Saved Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                clearFields();

            }else{
                JOptionPane.showMessageDialog(this,
                        "Error In Record Adding", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == btnCancel){
            this.dispose();
        }
    }
}
