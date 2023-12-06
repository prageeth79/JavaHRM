import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesignationInfo extends JFrame implements ActionListener {
    JLabel lblCode, lblDesignation;
    JTextField txtCode, txtDesignation;
    Font fntLblFont,fntTxtFont,fntBtnFont;

    JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    JTable tblEmployeeDesignation;
    JScrollPane tblEmpDesigPane;
    String[] designationFieldNames = {"Code", "Designation"};
    Designation db = new Designation();

    public DesignationInfo(){
        setWindow();
        setComponents(db);
        addComponent();
        this.setVisible(true);

    }

    private void setWindow(){
        this.setTitle("Personal Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(850,400);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    private  void setComponents(Designation db) {

        fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        lblCode = new JLabel("Designation No");
        lblCode.setBounds(10,10, 150, 25);
        lblCode.setFont(fntLblFont);

        txtCode = new JTextField();
        txtCode.setBounds(200, 10, 100, 25);
        txtCode.setFont(fntTxtFont);

        lblDesignation = new JLabel("Employee Full Name");
        lblDesignation.setBounds(10, 40, 250, 25);
        lblDesignation.setFont(fntLblFont);

        txtDesignation = new JTextField();
        txtDesignation.setBounds(200, 40, 250, 25);
        txtDesignation.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("new.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 80, 120, 40);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 80, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("update.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(290, 80, 120, 40);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("delete.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(430, 80, 120, 40);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(680, 80, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        String[][] data = new String[1][2];
        data = db.designationDetailsAll();
        tblEmployeeDesignation = new JTable(data, designationFieldNames);
        tblEmployeeDesignation.setFont(fntTxtFont);
        tblEmpDesigPane = new JScrollPane(tblEmployeeDesignation);
        tblEmpDesigPane.setBounds(10, 130, 800, 200);
        tblEmpDesigPane.setVisible(true);
    }

    private void addComponent(){
        this.add(lblCode);
        this.add(txtCode);
        this.add(lblDesignation);
        this.add(txtDesignation);

        this.add(btnCancel);
        this.add(btnDelete);
        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);

        this.add(tblEmpDesigPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
