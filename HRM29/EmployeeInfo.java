import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EmployeeInfo extends JFrame{
    private JLabel lblEmpSearch;
    protected JTextField txtEmpSearch;
    protected JTable tblEmployee;
    protected JScrollPane tblEmployeePane;
    protected JTabbedPane tabEmployee;
    protected JComboBox<String> cmbEmpSearchField;
    protected JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    protected final Employee db = new Employee();

    protected final String[] employeeFieldNames = {"Code", "Full Name", "Name With Init", "NIC", "Date of Birth", "Gender", "Department", "Designation"};

    protected final JMenuBar mnuMenuBar = new JMenuBar();
    protected final JMenu mnuFile = new JMenu("File");
    protected final JMenu mnuAdmin = new JMenu("Admin");
    protected final JMenuItem mnuAddNewUser = new JMenuItem("Add New User");
    protected final JMenuItem mnuChangePassword = new JMenuItem("Change Password");
    protected final JMenuItem mnuExit = new JMenuItem("Exit");

    public String user;
    public boolean isAdmin;

    public EmployeeInfo() {

    }

    protected void setMenu() {
        mnuMenuBar.add(mnuFile);
        if (isAdmin) {
            mnuMenuBar.add(mnuAdmin);
            mnuAdmin.add(mnuAddNewUser);
        }
        mnuAdmin.add(mnuChangePassword);
        mnuFile.add(mnuExit);
        this.setJMenuBar(mnuMenuBar);
    }

    protected void setComponents(Employee db) {

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        tabEmployee = new JTabbedPane();
        tabEmployee.setBounds(0,40,850,260);

        lblEmpSearch = new JLabel("Search Employee");
        lblEmpSearch.setBounds(370, 370, 130, 25);
        lblEmpSearch.setFont(fntTxtFont);

        txtEmpSearch = new JTextField();
        txtEmpSearch.setBounds(490, 370, 200, 25);
        txtEmpSearch.setFont(fntTxtFont);

        String fields[] = {"Id", "Full Name", "NIC", "Department", "Designation"};
        cmbEmpSearchField = new JComboBox<>(fields);
        cmbEmpSearchField.setBounds(700, 370, 100, 25);
        cmbEmpSearchField.setFont(fntTxtFont);

        String[][] data = db.employeeDetailsAll(user);

        tblEmployee = new JTable(data, employeeFieldNames);
        //tblEmployeeDesignation.setBounds(500, 10, 300, 100);
        tblEmployee.setFont(fntTxtFont);
        tblEmployeePane = new JScrollPane(tblEmployee);
        tblEmployeePane.setBounds(10, 400, 800, 150);
        tblEmployeePane.setVisible(true);

        ImageIcon inew = new ImageIcon("new.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 320, 120, 40);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        //btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 320, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        //btnSave.addActionListener(this);
        btnSave.setEnabled(false);

        ImageIcon iupdate = new ImageIcon("update.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(290, 320, 120, 40);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        //btnUpdate.addActionListener(this);
        btnUpdate.setEnabled(false);

        ImageIcon idelete = new ImageIcon("delete.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(430, 320, 120, 40);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        //btnDelete.addActionListener(this);
        btnDelete.setEnabled(false);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("EXIT");
        btnCancel.setBounds(680, 320, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        //btnCancel.addActionListener(this);
    }

    protected void addComponents() {
        this.add(lblEmpSearch);
        this.add(txtEmpSearch);
        this.add(cmbEmpSearchField);

        this.add(tblEmployeePane);
        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnDelete);
        this.add(btnCancel);

        this.add(tabEmployee);
    }

    protected void setWindow() {
        this.setTitle("Personal Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(850, 650);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    protected void buttonSaveEnable(boolean enable, JTextField txtEmployeeNo) {
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        txtEmployeeNo.setEnabled(enable);
    }

    protected void buttonNewEnable(boolean enable, JTextField txtEmployeeNo) {
        btnNew.setEnabled(enable);
        btnSave.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        btnUpdate.setEnabled(!enable);
        txtEmployeeNo.setEnabled(enable);
    }

    protected void buttonUpdateEnable(boolean enable, JTextField txtEmployeeNo) {
        btnSave.setEnabled(!enable);
        btnUpdate.setEnabled(enable);
        btnDelete.setEnabled(enable);
        txtEmployeeNo.setEnabled(!enable);
    }
}



