import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PersonalInfo extends EmploymentInfo implements ActionListener, KeyListener {
    protected String sDesignationId, sDepartmentId;
    protected JLabel lblEmployeeNo, lblEmployeeFullName, lblEmployeeNameWithInit, lblEmployeeNIC, lblEmployeeGender,lblEmployeeDOFB, lblEmployeeDesignation, lblEmployeeDepartment,lblEmpSearch;
    protected JLabel lblDateFormat;
    protected JTextField txtEmployeeNo, txtEmployeeFullName, txtEmployeeNameWithInit, txtEmployeeNIC, txtEmployeeDOFB, txtEmployeeDesignation, txtEmployeeDepartment,txtEmpSearch;
    protected JComboBox<String> cmbEmployeeGender,cmbEmpSearchField;
    protected JTable tblEmployeeDesignation,tblEmployeeDepartment;
    protected JLabel lblTblEmpDesignation,lblTblEmpDepartment;
    protected JScrollPane tblEmpDesigPane,tblEmpDepartPane;
    protected JButton btnAddDesig, btnAddDepart;
    protected JPanel panPersonalInfo;
    //private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel,btnAddDesig, btnAddDepart;
    //private final Employee db = new Employee();
    private final String[] designationFieldNames = {"Code", "Designation"};
    private final String[] departmentFieldNames = {"Code", "Department", "Tel", "Manager"};
    //private final String[] employeeFieldNames = {"Code", "Full Name", "Name With Init","NIC","Date of Birth","Gender","Department","Designation"};


    public PersonalInfo(){
        new UserLogin(this);
        this.setWindow();
        this.setComponents(db);
        this.gridSelection();
        this.adjustTableColumn();
        this.addTextBoxForcus();
        this.addComponents();
        if(!isAdmin){
            btnAddDesig.setEnabled(false);
            btnAddDepart.setEnabled(false);
        }
        setMenu();
        enableComponents(false);
        btnNew.requestFocusInWindow();
        //tabKeyBindings();
        tblEmployeeDepartment.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tblEmployeeDepartment.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            }
        });

        tblEmployeeDesignation.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tblEmployeeDesignation.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            }
        });
        this.setVisible(true);

    }

    private void tabKeyBindings(){
        createKeybindings(btnNew, txtEmployeeNo);
        createKeybindings(txtEmployeeNo, txtEmployeeFullName);
        createKeybindings(txtEmployeeFullName, txtEmployeeNameWithInit);
    }

    private void createKeybindings(JComponent element,JComponent focus) {
        element.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        element.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        element.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Tab");
        element.getActionMap().put("Tab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                focus.requestFocusInWindow();
            }
        });
    }

    @Override
    protected void setMenu(){
        super.setMenu();
        mnuAddNewUser.addActionListener(this);
        mnuChangePassword.addActionListener(this);
        mnuExit.addActionListener(this);

    }

    @Override
    protected  void setComponents(Employee db){

        super.setComponents(db);
        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        panPersonalInfo = new JPanel();
        panPersonalInfo.setBounds(0,0,850,300);
        panPersonalInfo.setLayout(null);

        lblEmployeeNo = new JLabel("Employee No");
        lblEmployeeNo.setBounds(10,5, 150, 25);
        lblEmployeeNo.setFont(fntLblFont);

        txtEmployeeNo = new JTextField();
        txtEmployeeNo.setBounds(120, 5, 100, 25);
        txtEmployeeNo.setFont(fntTxtFont);

        lblEmployeeFullName = new JLabel("Employee Full Name");
        lblEmployeeFullName.setBounds(230, 5, 250, 25);
        lblEmployeeFullName.setFont(fntLblFont);

        txtEmployeeFullName = new JTextField();
        txtEmployeeFullName.setBounds(400, 5, 400, 25);
        txtEmployeeFullName.setFont(fntTxtFont);

        lblEmployeeNameWithInit = new JLabel("Name with Initials");
        lblEmployeeNameWithInit.setBounds(10, 10, 150, 25);
        lblEmployeeNameWithInit.setFont(fntLblFont);

        txtEmployeeNameWithInit = new JTextField();
        txtEmployeeNameWithInit.setBounds(200, 10, 200, 25);
        txtEmployeeNameWithInit.setFont(fntTxtFont);

        lblEmployeeNIC = new JLabel("National ID Number");
        lblEmployeeNIC.setBounds(10, 40, 150, 25);
        lblEmployeeNIC.setFont(fntLblFont);

        txtEmployeeNIC = new JTextField();
        txtEmployeeNIC.setBounds(200, 40, 150, 25);
        txtEmployeeNIC.setFont(fntTxtFont);

        lblEmployeeGender = new JLabel("Gender");
        lblEmployeeGender.setBounds(10, 70, 150, 25);
        lblEmployeeGender.setFont(fntLblFont);

        String[] gender = {"male", "female"};
        cmbEmployeeGender = new JComboBox<>(gender);
        cmbEmployeeGender.setBounds(200, 70, 100, 25);
        cmbEmployeeGender.setFont(fntTxtFont);

        lblEmployeeDOFB = new JLabel("Date of Birth");
        lblEmployeeDOFB.setBounds(10, 100, 150, 25);
        lblEmployeeDOFB.setFont(fntLblFont);

        txtEmployeeDOFB = new JTextField();
        txtEmployeeDOFB.setBounds(200, 100, 150, 25);
        txtEmployeeDOFB.setFont(fntTxtFont);

        lblDateFormat = new JLabel("<yyyy-mm-dd>");
        lblDateFormat.setBounds(370, 100, 150, 25);
        lblDateFormat.setFont(fntTxtFont);

        lblEmployeeDesignation = new JLabel("Designation");
        lblEmployeeDesignation.setBounds(10, 130, 150, 25);
        lblEmployeeDesignation.setFont(fntLblFont);

        txtEmployeeDesignation = new JTextField();
        txtEmployeeDesignation.setBounds(200, 130, 250, 25);
        txtEmployeeDesignation.setFont(fntTxtFont);
        txtEmployeeDesignation.setEditable(false);

        btnAddDesig = new JButton("+");
        btnAddDesig.setBounds(460,130,25,25);
        btnAddDesig.setFont(fntLblFont);
        btnAddDesig.addActionListener(this);

        lblEmployeeDepartment = new JLabel("Department");
        lblEmployeeDepartment.setBounds(10, 150, 150, 25);
        lblEmployeeDepartment.setFont(fntLblFont);

        txtEmployeeDepartment = new JTextField();
        txtEmployeeDepartment.setBounds(200, 160, 250, 25);
        txtEmployeeDepartment.setFont(fntTxtFont);
        txtEmployeeDepartment.setEditable(false);

        btnAddDepart = new JButton("+");
        btnAddDepart.setBounds(460,160,25,25);
        btnAddDepart.setFont(fntLblFont);
        btnAddDepart.addActionListener(this);

        lblTblEmpDesignation = new JLabel("Designation");
        lblTblEmpDesignation.setBounds(500, 10,300,20);

        String[][] data = db.designation.designationDetailsAll();

        tblEmployeeDesignation = new JTable(data, designationFieldNames);
        tblEmployeeDesignation.setFont(fntTxtFont);

        tblEmpDesigPane = new JScrollPane(tblEmployeeDesignation);
        tblEmpDesigPane.setBounds(500, 30, 300, 80);
        tblEmpDesigPane.setVisible(true);

        lblTblEmpDepartment = new JLabel("Department");
        lblTblEmpDepartment.setBounds(500, 120,300,20);


        data = db.department.departmentDetailsAll();

        tblEmployeeDepartment = new JTable(data, departmentFieldNames);
        tblEmployeeDepartment.setFont(fntTxtFont);

        tblEmpDepartPane = new JScrollPane(tblEmployeeDepartment);
        tblEmpDepartPane.setBounds(500, 140, 300, 80);
        tblEmpDepartPane.setVisible(true);

        lblEmpSearch = new JLabel("Search Employee");
        lblEmpSearch.setBounds(370, 310, 130, 25);
        lblEmpSearch.setFont(fntTxtFont);

        txtEmpSearch = new JTextField();
        txtEmpSearch.setBounds(490,310,200,25);
        txtEmpSearch.setFont(fntTxtFont);

        String fields[] = {"Id", "Full Name","NIC", "Department", "Designation"};
        cmbEmpSearchField = new JComboBox<>(fields);
        cmbEmpSearchField.setBounds(700, 310, 100, 25);
        cmbEmpSearchField.setFont(fntTxtFont);

        txtEmployeeNo.addKeyListener(this);
        txtEmployeeFullName.addKeyListener(this);
        txtEmployeeNameWithInit.addKeyListener(this);
        txtEmployeeNIC.addKeyListener(this);
        cmbEmployeeGender.addKeyListener(this);
        txtEmployeeDOFB.addKeyListener(this);
        tblEmployeeDesignation.addKeyListener(this);
        tblEmployeeDepartment.addKeyListener(this);

        txtDateAppointment.addKeyListener(this);
        txtDateMadePermanent.addKeyListener(this);
        txtPersonalFileNo.addKeyListener(this);
        txtIncrimentDate.addKeyListener(this);
        cmbClass.addKeyListener(this);
        cmbGrade.addKeyListener(this);
        chkEBI.addKeyListener(this);
        chkEBII.addKeyListener(this);
        cmbStatus.addKeyListener(this);

        btnNew.addActionListener(this);
        btnNew.addKeyListener(this);
        btnSave.addActionListener(this);
        btnSave.addKeyListener(this);
        btnSave.setEnabled(false);


        btnUpdate.addActionListener(this);
        btnUpdate.addKeyListener(this);
        btnUpdate.setEnabled(false);

        btnDelete.addActionListener(this);
        btnDelete.addKeyListener(this);
        btnDelete.setEnabled(false);

        btnCancel.addActionListener(this);
    }

    public void updateDesignationTable(){
        tblEmployeeDesignation.setModel(new DefaultTableModel(db.designation.designationDetailsAll(),designationFieldNames));
    }

    public void updateDepartmentTable(){
        tblEmployeeDepartment.setModel(new DefaultTableModel(db.department.departmentDetailsAll(),departmentFieldNames));
    }

    private void addTextBoxForcus(){
        PersonalInfo me = this;
        super.addTextBoxForcus(me);
        txtEmployeeNo.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
                txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if(!db.validator.matchNumber(txtEmployeeNo.getText()))
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
                txtEmployeeFullName.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmployeeFullName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if(!db.validator.matchCharacters(txtEmployeeFullName.getText()))
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
                txtEmployeeNameWithInit.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmployeeNameWithInit.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if(!db.validator.matchCharactersAndDot(txtEmployeeNameWithInit.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Name with Initialize, " +
                                    "it must contains only letters and space or dot",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtEmployeeNIC.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
                txtEmployeeNIC.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmployeeNIC.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if(!db.validator.matchNic(txtEmployeeNIC.getText()))
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
                txtEmployeeDOFB.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmployeeDOFB.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if(!db.validator.matchDate(txtEmployeeDOFB.getText()))
                {
                    JOptionPane.showMessageDialog(me,"Invalid Employee Date of Birth, it must be in 'yyyy-mm-dd' Format",
                            "Validator",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cmbEmployeeGender.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
                cmbEmployeeGender.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                cmbEmployeeGender.setBorder(null);
            }
        });
        tblEmployeeDesignation.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
                tblEmpDesigPane.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                tblEmpDesigPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
            }
        });
        tblEmployeeDepartment.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {
                tblEmpDepartPane.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                tblEmpDepartPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
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

    @Override
    protected void addComponents(){

        panPersonalInfo.add(lblTblEmpDesignation);
        panPersonalInfo.add(tblEmpDesigPane);

        this.add(txtEmployeeNo);
        this.add(lblEmployeeNo);

        this.add(txtEmployeeFullName);
        this.add(lblEmployeeFullName);

        panPersonalInfo.add(txtEmployeeNameWithInit);
        panPersonalInfo.add(lblEmployeeNameWithInit);

        panPersonalInfo.add(txtEmployeeNIC);
        panPersonalInfo.add(lblEmployeeNIC);

        panPersonalInfo.add(cmbEmployeeGender);
        panPersonalInfo.add(lblEmployeeGender);

        panPersonalInfo.add(txtEmployeeDOFB);
        panPersonalInfo.add(lblEmployeeDOFB);
        panPersonalInfo.add(lblDateFormat);

        panPersonalInfo.add(txtEmployeeDesignation);
        panPersonalInfo.add(lblEmployeeDesignation);
        panPersonalInfo.add(btnAddDesig);

        panPersonalInfo.add(txtEmployeeDepartment);
        panPersonalInfo.add(lblEmployeeDepartment);
        panPersonalInfo.add(btnAddDepart);

        panPersonalInfo.add(tblEmpDepartPane);
        panPersonalInfo.add(lblTblEmpDepartment);

        tabEmployee.add("Personal Info",panPersonalInfo);

        super.addComponents();
    }

    private void gridSelection(){
        ListSelectionModel tblEmpDeptMod = tblEmployeeDepartment.getSelectionModel();
        tblEmpDeptMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDeptMod.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    sDepartmentId = tblEmployeeDepartment.getValueAt(tblEmployeeDepartment.getSelectedRow(), 0).toString();
                    txtEmployeeDepartment.setText(tblEmployeeDepartment.getValueAt(tblEmployeeDepartment.getSelectedRow(), 1).toString());
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });

        ListSelectionModel tblEmpDesigMod = tblEmployeeDesignation.getSelectionModel();
        tblEmpDesigMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDesigMod.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    sDesignationId = tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 0).toString();
                    txtEmployeeDesignation.setText(tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 1).toString());
                    //System.out.println(sDesignationId);
                }catch (Exception ex){
                    System.out.println(ex);
                }
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
               clearBorder();
               buttonUpdateEnable(true);

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
        txtDateAppointment.setText(db.dateOfAppointment);
        txtDateMadePermanent.setText(db.dateMadePermenant);
        txtPersonalFileNo.setText(db.personalFileNo);
        txtIncrimentDate.setText(db.incrementDate);
        cmbClass.setSelectedItem(db.eClass);
        cmbGrade.setSelectedItem(db.grade);
        chkEBI.setSelected(db.ebI);
        chkEBII.setSelected(db.ebII);
        cmbStatus.setSelectedItem(db.status);
        enableComponents(true);
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
        txtPersonalFileNo.setText("");
        txtDateAppointment.setText("");
        txtIncrimentDate.setText("");
        txtDateMadePermanent.setText("");
        enableComponents(true);
    }

    private void enableComponents(boolean enable){
        txtEmployeeNo.setEditable(enable);
        txtEmployeeFullName.setEditable(enable);
        txtEmployeeNameWithInit.setEditable(enable);
        txtEmployeeNIC.setEditable(enable);
        cmbEmployeeGender.setEnabled(enable);
        txtEmployeeNIC.setEditable(enable);
        txtEmployeeDOFB.setEditable(enable);
        tblEmployeeDesignation.setEnabled(enable);
        tblEmployeeDepartment.setEnabled(enable);
        txtDateAppointment.setEditable(enable);
        txtDateMadePermanent.setEditable(enable);
        txtIncrimentDate.setEditable(enable);
        cmbGrade.setEnabled(enable);
        cmbClass.setEnabled(enable);
        cmbStatus.setEnabled(enable);
        txtPersonalFileNo.setEditable(enable);
        chkEBI.setEnabled(enable);
        chkEBII.setEnabled(enable);
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
        db.owner = user;
        db.dateOfAppointment = txtDateAppointment.getText();
        db.dateMadePermenant = txtDateMadePermanent.getText();
        db.personalFileNo = txtPersonalFileNo.getText();
        db.incrementDate = txtIncrimentDate.getText();
        db.eClass = cmbClass.getSelectedItem().toString();
        db.grade = cmbGrade.getSelectedItem().toString();
        db.ebI = chkEBI.isSelected();
        db.ebII = chkEBII.isSelected();
        db.status = cmbStatus.getSelectedItem().toString();
    }

    private void buttonSaveEnable(boolean enable){
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        txtEmployeeNo.setEnabled(enable);
    }

    private void buttonNewEnable(boolean enable){
        btnNew.setEnabled(enable);
        btnSave.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        btnUpdate.setEnabled(!enable);
        txtEmployeeNo.setEnabled(enable);
    }

    private  void buttonUpdateEnable(boolean enable){
        btnSave.setEnabled(!enable);
        btnUpdate.setEnabled(enable);
        btnDelete.setEnabled(enable);
        txtEmployeeNo.setEnabled(!enable);
    }

    private void newRecord(){
        clearFields();
        buttonSaveEnable(true);
        txtEmployeeNo.requestFocusInWindow();
        txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
    }

    private  void save(){
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
                tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(user), employeeFieldNames));
            }catch(Exception ex){
                System.out.println(ex);
            }finally {
                clearFields();
                enableComponents(false);
                buttonNewEnable(true);
            }

        }else{
            JOptionPane.showMessageDialog(this,
                    "Error In Record Adding", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
        }
    }

    private  void update(){
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
                tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(user), employeeFieldNames));
            } catch (Exception ex) {
                System.out.println(ex);
            }finally {
                clearFields();
                enableComponents(false);
                buttonNewEnable(true);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error In Record Update.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void delete(){
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure that you want to Delete this record?","Delete",JOptionPane.YES_NO_OPTION);
        if(option == 0 && db.delete(txtEmployeeNo.getText())) {
            buttonNewEnable(true);
            tblEmployee.clearSelection();
            tblEmployee.setModel(new DefaultTableModel(db.employeeDetailsAll(user), employeeFieldNames));
            clearFields();
            JOptionPane.showMessageDialog(this,
                    "Record Deleted Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(option != 1) JOptionPane.showMessageDialog(this,
                    "Error In Record Delete.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNew){
            newRecord();
        }
        if(e.getSource() == btnSave){
            save();
        }
        if(e.getSource() == btnUpdate) {
            update();
        }

        if(e.getSource() == btnDelete){
            delete();
        }

        if(e.getSource() == btnAddDepart){
            new DepartmentInfo(this);
        }
        if(e.getSource()== btnAddDesig){
            new DesignationInfo(this);
        }

        if(e.getSource() == btnCancel){
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure that you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
            if(option==0) System.exit(0);

        }

        if(e.getSource() == mnuExit){
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure that you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
            if(option==0) System.exit(0);
        }

        if(e.getSource() == mnuAddNewUser){
            new AddNewUser(this);
        }

        if(e.getSource() == mnuChangePassword){
            new ChangePassword(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            e.consume();
            if(e.getSource() == txtEmployeeNo) {
                txtEmployeeFullName.requestFocusInWindow();
                //txtEmployeeFullName.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                //txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtEmployeeFullName){
                txtEmployeeNameWithInit.requestFocusInWindow();
                //txtEmployeeNameWithInit.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                //txtEmployeeFullName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtEmployeeNameWithInit){
                txtEmployeeNIC.requestFocusInWindow();
                //txtEmployeeNIC.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtEmployeeNameWithInit.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtEmployeeNIC){
                cmbEmployeeGender.requestFocusInWindow();
                //cmbEmployeeGender.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtEmployeeNIC.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == cmbEmployeeGender){
                txtEmployeeDOFB.requestFocusInWindow();
                //txtEmployeeDOFB.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //cmbEmployeeGender.setBorder(null);
            }
            if(e.getSource() == txtEmployeeDOFB){
                tblEmployeeDesignation.requestFocusInWindow();
                //tblEmployeeDesignation.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtEmployeeDOFB.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == tblEmployeeDesignation){
                tblEmployeeDepartment.requestFocusInWindow();
                //tblEmployeeDepartment.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //tblEmployeeDesignation.setBorder(null);
            }
            if(e.getSource() == tblEmployeeDepartment) {
                tabEmployee.setSelectedIndex(1);
                txtDateAppointment.requestFocusInWindow();
                //txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //tblEmployeeDepartment.setBorder(null);
            }
            if(e.getSource() == txtDateAppointment){
                txtDateMadePermanent.requestFocusInWindow();
                //txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtDateMadePermanent){
                txtPersonalFileNo.requestFocusInWindow();
                //txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtPersonalFileNo){
                txtIncrimentDate.requestFocusInWindow();
                //txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == txtIncrimentDate){
                cmbClass.requestFocusInWindow();
                //cmbClass.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == cmbClass){
                cmbGrade.requestFocusInWindow();
                //cmbGrade.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //cmbClass.setBorder(null);
            }
            if(e.getSource() == cmbGrade){
                chkEBI.requestFocusInWindow();
                //chkEBI.setBorderPainted(true);
                //chkEBI.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //cmbGrade.setBorder(null);
            }
            if(e.getSource() == chkEBI){
                chkEBII.requestFocusInWindow();
                //chkEBII.setBorderPainted(true);
                //chkEBII.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //chkEBI.setBorder(null);
            }
            if(e.getSource() == chkEBII){
                cmbStatus.requestFocusInWindow();
                //cmbStatus.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                //chkEBII.setBorder(null);
            }
            if(e.getSource() == cmbStatus){
                if(btnSave.isEnabled()){
                    btnSave.requestFocusInWindow();
                    //btnSave.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    //cmbStatus.setBorder(null);
                }else if(btnUpdate.isEnabled()){
                    btnUpdate.requestFocusInWindow();
                    //btnUpdate.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    //cmbStatus.setBorder(null);
                }
            }
            if(e.getSource() == btnSave) {
                save();
                tabEmployee.setSelectedIndex(0);
                if(btnSave.isEnabled()){
                    txtEmployeeNo.requestFocusInWindow();
                    //txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }else {
                    btnNew.requestFocusInWindow();
                    //btnNew.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }
                //btnSave.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == btnUpdate){
                update();
                tabEmployee.setSelectedIndex(0);
                if(btnUpdate.isEnabled()){
                    txtEmployeeNo.requestFocusInWindow();
                    //txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }else{
                    btnNew.requestFocusInWindow();
                    //btnNew.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }

                //btnUpdate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
            if(e.getSource() == btnDelete) delete();

            if(e.getSource() == btnNew) newRecord();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void clearBorder(){
        txtEmployeeNo.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtEmployeeFullName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtEmployeeNameWithInit.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtEmployeeNIC.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        cmbEmployeeGender.setBorder(null);
        txtEmployeeDOFB.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        tblEmployeeDesignation.setBorder(null);
        tblEmployeeDepartment.setBorder(null);
        txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        cmbClass.setBorder(null);
        cmbGrade.setBorder(null);
        chkEBI.setBorder(null);
        chkEBII.setBorder(null);
        cmbStatus.setBorder(null);
        btnSave.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        btnUpdate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    }
}
