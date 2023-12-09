import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PersonalInfo extends JFrame implements ActionListener {
    private String sDesignationId, sDepartmentId;
    private JLabel lblEmployeeNo, lblEmployeeFullName, lblEmployeeNameWithInit, lblEmployeeNIC, lblEmployeeGender,lblEmployeeDOFB, lblEmployeeDesignation, lblEmployeeDepartment,lblEmpSearch;
    private JTextField txtEmployeeNo, txtEmployeeFullName, txtEmployeeNameWithInit, txtEmployeeNIC, txtEmployeeDOFB, txtEmployeeDesignation, txtEmployeeDepartment,txtEmpSearch;
    private JComboBox cmbEmployeeGender,cmbEmpSearchField;
    private JTable tblEmployeeDesignation,tblEmployeeDepartment,tblEmployee;
    private JLabel lblTblEmpDesignation,lblTblEmpDepartment;
    private JScrollPane tblEmpDesigPane,tblEmpDepartPane,tblEmployeePane;
    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel,btnAddDesig, btnAddDepart;
    private Font fntLblFont,fntTxtFont,fntBtnFont;
    private final Employee db = new Employee();

    private final String[] designationFieldNames = {"Code", "Designation"};
    private final String[] departmentFieldNames = {"Code", "Department", "Tel", "Manager"};
    private final String[] employeeFieldNames = {"Code", "Full Name", "Name With Init","NIC","Date of Birth","Gender","Department","Designation"};

    public PersonalInfo(){
        this.setWindow();
        this.setComponents(db);
        this.gridSelection();
        this.adjustTableColumn();
        this.addTextBoxForcus();
        this.addComponents();
        this.setVisible(true);
    }

    private  void setComponents(Employee db){

        fntLblFont = new Font("Comic Sans",Font.BOLD, 16);
        fntTxtFont = new Font("Comic Sans",Font.PLAIN, 14);
        fntBtnFont = new Font("Comic Sans",Font.BOLD, 14);

        lblEmployeeNo = new JLabel("Employee No");
        lblEmployeeNo.setBounds(10,10, 150, 25);
        lblEmployeeNo.setFont(fntLblFont);

        txtEmployeeNo = new JTextField();
        txtEmployeeNo.setBounds(200, 10, 100, 25);
        txtEmployeeNo.setFont(fntTxtFont);

        lblEmployeeFullName = new JLabel("Employee Full Name");
        lblEmployeeFullName.setBounds(10, 40, 250, 25);
        lblEmployeeFullName.setFont(fntLblFont);

        txtEmployeeFullName = new JTextField();
        txtEmployeeFullName.setBounds(200, 40, 250, 25);
        txtEmployeeFullName.setFont(fntTxtFont);

        lblEmployeeNameWithInit = new JLabel("Name with Initials");
        lblEmployeeNameWithInit.setBounds(10, 70, 150, 25);
        lblEmployeeNameWithInit.setFont(fntLblFont);

        txtEmployeeNameWithInit = new JTextField();
        txtEmployeeNameWithInit.setBounds(200, 70, 200, 25);
        txtEmployeeNameWithInit.setFont(fntTxtFont);

        lblEmployeeNIC = new JLabel("National ID Number");
        lblEmployeeNIC.setBounds(10, 100, 150, 25);
        lblEmployeeNIC.setFont(fntLblFont);

        txtEmployeeNIC = new JTextField();
        txtEmployeeNIC.setBounds(200, 100, 150, 25);
        txtEmployeeNIC.setFont(fntTxtFont);

        lblEmployeeGender = new JLabel("Gender");
        lblEmployeeGender.setBounds(10, 130, 150, 25);
        lblEmployeeGender.setFont(fntLblFont);

        String gender[] = {"male", "female"};
        cmbEmployeeGender = new JComboBox(gender);
        cmbEmployeeGender.setBounds(200, 130, 100, 25);
        cmbEmployeeGender.setFont(fntTxtFont);

        lblEmployeeDOFB = new JLabel("Date of Birth");
        lblEmployeeDOFB.setBounds(10, 160, 150, 25);
        lblEmployeeDOFB.setFont(fntLblFont);

        txtEmployeeDOFB = new JTextField();
        txtEmployeeDOFB.setBounds(200, 160, 150, 25);
        txtEmployeeDOFB.setFont(fntTxtFont);

        lblEmployeeDesignation = new JLabel("Designation");
        lblEmployeeDesignation.setBounds(10, 190, 150, 25);
        lblEmployeeDesignation.setFont(fntLblFont);

        txtEmployeeDesignation = new JTextField();
        txtEmployeeDesignation.setBounds(200, 190, 250, 25);
        txtEmployeeDesignation.setFont(fntTxtFont);
        txtEmployeeDesignation.setEditable(false);

        btnAddDesig = new JButton("+");
        btnAddDesig.setBounds(460,190,25,25);
        btnAddDesig.setFont(fntLblFont);
        btnAddDesig.addActionListener(this);

        lblEmployeeDepartment = new JLabel("Department");
        lblEmployeeDepartment.setBounds(10, 220, 150, 25);
        lblEmployeeDepartment.setFont(fntLblFont);

        txtEmployeeDepartment = new JTextField();
        txtEmployeeDepartment.setBounds(200, 220, 250, 25);
        txtEmployeeDepartment.setFont(fntTxtFont);
        txtEmployeeDepartment.setEditable(false);

        btnAddDepart = new JButton("+");
        btnAddDepart.setBounds(460,220,25,25);
        btnAddDepart.setFont(fntLblFont);
        btnAddDepart.addActionListener(this);

        lblTblEmpDesignation = new JLabel("Designation");
        lblTblEmpDesignation.setBounds(500, 10,300,20);
        String[][] data = new String[1][2];
        data = db.designation.designationDetailsAll();

        tblEmployeeDesignation = new JTable(data, designationFieldNames);
        tblEmployeeDesignation.setFont(fntTxtFont);
        tblEmpDesigPane = new JScrollPane(tblEmployeeDesignation);
        tblEmpDesigPane.setBounds(500, 25, 300, 100);
        tblEmpDesigPane.setVisible(true);

        lblTblEmpDepartment = new JLabel("Department");
        lblTblEmpDepartment.setBounds(500, 130,300,20);

        data = db.department.departmentDetailsAll();

        tblEmployeeDepartment = new JTable(data, departmentFieldNames);
        //tblEmployeeDesignation.setBounds(500, 10, 300, 100);
        tblEmployeeDepartment.setFont(fntTxtFont);
        tblEmpDepartPane = new JScrollPane(tblEmployeeDepartment);
        tblEmpDepartPane.setBounds(500, 150, 300, 100);
        tblEmpDepartPane.setVisible(true);

        lblEmpSearch = new JLabel("Search Employee");
        lblEmpSearch.setBounds(370, 310, 130, 25);
        lblEmpSearch.setFont(fntTxtFont);

        txtEmpSearch = new JTextField();
        txtEmpSearch.setBounds(490,310,200,25);
        txtEmpSearch.setFont(fntTxtFont);

        String fields[] = {"Id", "Full Name","NIC", "Department", "Designation"};
        cmbEmpSearchField = new JComboBox(fields);
        cmbEmpSearchField.setBounds(700, 310, 100, 25);
        cmbEmpSearchField.setFont(fntTxtFont);

        data = db.employeeDetailsAll();

        tblEmployee = new JTable(data, employeeFieldNames);
        //tblEmployeeDesignation.setBounds(500, 10, 300, 100);
        tblEmployee.setFont(fntTxtFont);
        tblEmployeePane = new JScrollPane(tblEmployee);
        tblEmployeePane.setBounds(10, 350, 800, 250);
        tblEmployeePane.setVisible(true);

        ImageIcon inew = new ImageIcon("new.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 260, 120, 40);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 260, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("update.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(290, 260, 120, 40);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("delete.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(430, 260, 120, 40);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(680, 260, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
    }

    private void addTextBoxForcus(){
        PersonalInfo me = this;
        txtEmployeeNo.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!db.matchCode(txtEmployeeNo.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Number it must contains only numbers",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtEmployeeFullName.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!db.matchFullName(txtEmployeeFullName.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Name, it must contains only letters and space",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtEmployeeNameWithInit.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!db.matchNameWithInit(txtEmployeeNameWithInit.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Name with Initialize, it must contains only letters and space or dot",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtEmployeeNIC.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!db.matchNic(txtEmployeeNIC.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee NIC, it must be in valid NIC Format",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtEmployeeDOFB.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!db.matchDateOfBirth(txtEmployeeDOFB.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Date of Birth, it must be in 'yyyy-mm-dd' Format",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private  void adjustTableColumn(){
        tblEmployeeDesignation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblEmployeeDesignation.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblEmployeeDesignation.getColumnModel().getColumn(1).setPreferredWidth(200);

        tblEmployeeDepartment.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblEmployeeDepartment.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblEmployeeDepartment.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblEmployeeDepartment.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblEmployeeDepartment.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    private void addComponents(){
        this.add(lblEmpSearch);
        this.add(txtEmpSearch);
        this.add(cmbEmpSearchField);

        this.add(tblEmployeePane);
        this.add(lblTblEmpDesignation);
        this.add(tblEmpDesigPane);

        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnDelete);
        this.add(btnCancel);
        this.add(btnAddDepart);
        this.add(btnAddDesig);

        this.add(tblEmpDepartPane);
        this.add(lblTblEmpDepartment);
        this.add(txtEmployeeDepartment);
        this.add(lblEmployeeDepartment);
        this.add(txtEmployeeDesignation);
        this.add(lblEmployeeDesignation);
        this.add(txtEmployeeDOFB);
        this.add(lblEmployeeDOFB);
        this.add(cmbEmployeeGender);
        this.add(lblEmployeeGender);
        this.add(txtEmployeeNIC);
        this.add(lblEmployeeNIC);
        this.add(txtEmployeeNameWithInit);
        this.add(lblEmployeeNameWithInit);
        this.add(txtEmployeeFullName);
        this.add(lblEmployeeFullName);
        this.add(txtEmployeeNo);
        this.add(lblEmployeeNo);
    }

    private void gridSelection(){
        ListSelectionModel tblEmpDeptMod = tblEmployeeDepartment.getSelectionModel();
        tblEmpDeptMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDeptMod.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                sDepartmentId = tblEmployeeDepartment.getValueAt(tblEmployeeDepartment.getSelectedRow(), 0).toString();
                txtEmployeeDepartment.setText(tblEmployeeDepartment.getValueAt(tblEmployeeDepartment.getSelectedRow(), 1).toString());
            }
        });

        ListSelectionModel tblEmpDesigMod = tblEmployeeDesignation.getSelectionModel();
        tblEmpDesigMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDesigMod.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                sDesignationId = tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 0).toString();
                txtEmployeeDesignation.setText(tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 1).toString());
            }
        });

        ListSelectionModel tblEmpMod = tblEmployee.getSelectionModel();
        tblEmpMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpMod.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String sEmpCode = "";
                try {
                    sEmpCode = tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString();
                }catch(Exception ex){
                    System.out.println(ex);
                    return;
                }
               db.load(sEmpCode);
               loadTextbox();

            }
        });
    }

    private void loadTextbox(){
        txtEmployeeNo.setText(db.code);
        txtEmployeeFullName.setText(db.fullName);
        txtEmployeeNameWithInit.setText(db.nameWithInit);
        txtEmployeeNIC.setText(db.nic);
        txtEmployeeDOFB.setText(db.dateOfBirth);
        cmbEmployeeGender.setSelectedItem(db.gender);
        sDesignationId = db.designation.code;
        txtEmployeeDesignation.setText(db.designation.designation);
        sDepartmentId = db.department.code;
        txtEmployeeDepartment.setText(db.department.departmentName);
    }

    private void setWindow(){
        this.setTitle("Personal Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(850,650);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    private void clearFields(){
        txtEmployeeNo.setText("");
        txtEmployeeFullName.setText("");
        txtEmployeeNameWithInit.setText("");
        txtEmployeeNIC.setText("");
        txtEmployeeDOFB.setText("");
        txtEmployeeDesignation.setText("");
        txtEmployeeDepartment.setText("");
        sDesignationId = "";
        sDepartmentId = "";
    }

    private void dataAdd(){
        db.code = txtEmployeeNo.getText();
        db.fullName = txtEmployeeFullName.getText();
        db.nameWithInit = txtEmployeeNameWithInit.getText();
        db.nic = txtEmployeeNIC.getText();
        db.gender = cmbEmployeeGender.getSelectedItem().toString();
        db.designation.load(sDesignationId);
        db.department.load(sDepartmentId);
        db.dateOfBirth = txtEmployeeDOFB.getText();
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
                try {
                    tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(), employeeFieldNames));
                }catch(Exception ex){
                    System.out.println(ex);
                }
                clearFields();

            }else{
                JOptionPane.showMessageDialog(this,
                        "Error In Record Adding", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == btnUpdate) {
            dataAdd();
            if (!db.validate()) {
                JOptionPane.showMessageDialog(this, "Data validation Fail",
                        "Validator", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (db.update()) {
                //tblEmployee.clearSelection();
                JOptionPane.showMessageDialog(this,
                        "Record Updated Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(), employeeFieldNames));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                clearFields();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Error In Record Update.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }

        }

        if(e.getSource() == btnDelete){
            if(db.delete(txtEmployeeNo.getText())) {
                tblEmployee.clearSelection();
                tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(), employeeFieldNames));
                clearFields();
                JOptionPane.showMessageDialog(this,
                        "Record Deleted Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this,
                        "Error In Record Delete.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == btnAddDepart){
            new DepartmentInfo();
        }
        if(e.getSource()== btnAddDesig){
            new DesignationInfo();
        }
    }
}
