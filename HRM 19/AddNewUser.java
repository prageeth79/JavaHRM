import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewUser extends JDialog implements ActionListener {
    private JLabel lblUserName, lblPassword, lblConfPassword;
    private JTextField txtuserName;
    private  JPasswordField txtPassword, txtConfPassword;
    private JButton btnNew, btnSave, btnCancel;
    private final String[] loginFieldNames = {"UserName", "Password"};
    private final Login db = new Login();

    public AddNewUser(PersonalInfo owner){
        super(owner,"Add New User",true);
        setWindow();
        setComponents(db);
        addComponent();
        this.setVisible(true);
    }
    private void setWindow(){
        this.setTitle("Add New User");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(480,200);
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

        txtuserName = new JTextField();
        txtuserName.setBounds(200, 10, 250, 25);
        txtuserName.setFont(fntTxtFont);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 40, 250, 25);
        lblPassword.setFont(fntLblFont);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 40, 250, 25);
        txtPassword.setFont(fntTxtFont);

        lblConfPassword = new JLabel("Conform Password");
        lblConfPassword.setBounds(10, 70, 250, 25);
        lblConfPassword.setFont(fntLblFont);

        txtConfPassword = new JPasswordField();
        txtConfPassword.setBounds(200, 70, 250, 25);
        txtConfPassword.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("new.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 100, 120, 40);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 100, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(330, 100, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);
    }

    private void clearFields(){
        txtuserName.setText("");
        txtPassword.setText("");
        txtConfPassword.setText("");
    }

    private void loadTextbox(){
        txtuserName.setText(db.userName);
        txtPassword.setText(db.password);
        txtConfPassword.setText(db.confPassword);
    }

    private void dataAdd(){
        db.userName = txtuserName.getText();
        db.password = new String(txtPassword.getPassword());
        db.confPassword = new String(txtConfPassword.getPassword());
        System.out.println(db.password);
        System.out.println(db.confPassword);
    }

    private void addComponent(){
        this.add(lblUserName);
        this.add(txtuserName);
        this.add(lblPassword);
        this.add(txtPassword);
        this.add(lblConfPassword);
        this.add(txtConfPassword);

        this.add(btnCancel);
        this.add(btnNew);
        this.add(btnSave);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNew){
            clearFields();
        }
        if(e.getSource() == btnSave){
            dataAdd();
            if(!db.validate()) {
                JOptionPane.showMessageDialog(this,"Data validation Fail",
                        "Validator",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(db.save()) {
                JOptionPane.showMessageDialog(this,
                        "Record Added Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
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
