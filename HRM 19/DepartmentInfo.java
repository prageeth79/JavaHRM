import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DepartmentInfo extends JDialog implements ActionListener {
    private JTextField txtDepartmentId, txtDepartmentName, txtTelephoneNo, txtManagerName;
    private JLabel lblDepartmentId, lblDepartmentName, lblTelephoneNo, lblManagerName;
    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tblEmployeeDepartment;
    private JScrollPane tblEmpDepartPane;
    private final String[] departmentFieldNames = {"Code", "Department", "Tel", "Manager"};
    private final Department db = new Department();
    private final PersonalInfo owner;

    public DepartmentInfo(PersonalInfo owner){
        super(owner,"Department Info",true);
        setWindow();
        initComponent();
        addComponent();
        gridSelection();
        addTextBoxForcus();
        buttonNewEnable(true);
        this.owner = owner;
        this.setVisible(true);

    }

    public void addTextBoxForcus() {
        DepartmentInfo me = this;
        txtDepartmentId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchCode(txtDepartmentId.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Department Number it must contains only numbers",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtDepartmentName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchDeptName(txtDepartmentName.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Department Name it must contains only Characters and space",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtTelephoneNo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchTelNo(txtTelephoneNo.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Telephone Number enter 10 digits",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtManagerName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.matchManager(txtManagerName.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid manager name enter characters space and dot only",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    public void initComponent(){
        String[][] data;

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        lblDepartmentId = new JLabel("Department Code");
        lblDepartmentId.setBounds(10,10, 160,25);
        lblDepartmentId.setFont(fntLblFont);

        txtDepartmentId = new JTextField();
        txtDepartmentId.setBounds(200,10,200,25);
        txtDepartmentId.setFont(fntTxtFont);

        lblDepartmentName = new JLabel("Department Name");
        lblDepartmentName.setBounds(10, 40,160,25);
        lblDepartmentName.setFont(fntLblFont);

        txtDepartmentName = new JTextField();
        txtDepartmentName.setBounds(200,40,200, 25);
        txtDepartmentName.setFont(fntTxtFont);

        lblTelephoneNo = new JLabel("Telephone No");
        lblTelephoneNo.setBounds(10,70, 160,25);
        lblTelephoneNo.setFont(fntLblFont);

        txtTelephoneNo = new JTextField();
        txtTelephoneNo.setBounds(200, 70, 200,25);
        txtTelephoneNo.setFont(fntTxtFont);

        lblManagerName = new JLabel("Manager Name");
        lblManagerName.setBounds(10,100,160,25);
        lblManagerName.setFont(fntLblFont);

        txtManagerName = new JTextField();
        txtManagerName.setBounds(200,100,200,25);
        txtManagerName.setFont(fntTxtFont);

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

        ImageIcon iupdate = new ImageIcon("update.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(290, 160, 120, 40);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("delete.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(430, 160, 120, 40);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(680, 160, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);

        data = db.departmentDetailsAll();

        tblEmployeeDepartment = new JTable(data, departmentFieldNames);
        tblEmployeeDepartment.setFont(fntTxtFont);
        tblEmpDepartPane = new JScrollPane(tblEmployeeDepartment);
        tblEmpDepartPane.setBounds(10, 220, 800, 200);
        tblEmpDepartPane.setVisible(true);
    }

    public void addComponent(){
        this.add(lblDepartmentId);
        this.add(txtDepartmentId);
        this.add(lblDepartmentName);
        this.add(txtDepartmentName);
        this.add(lblTelephoneNo);
        this.add(txtTelephoneNo);
        this.add(lblManagerName);
        this.add(txtManagerName);
        this.add(tblEmpDepartPane);

        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnCancel);
        this.add(btnDelete);
    }

    private void setTextBox(){
        txtDepartmentId.setText(db.code);
        txtDepartmentName.setText(db.departmentName);
        txtTelephoneNo.setText(db.telephoneNo);
        txtManagerName.setText(db.manager);
    }


    private void setWindow(){
        this.setTitle("Department Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(850,500);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    public  void gridSelection(){

        ListSelectionModel tblEmpDeptMod = tblEmployeeDepartment.getSelectionModel();
        tblEmpDeptMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDeptMod.addListSelectionListener(new ListSelectionListener(){
            String deptCode;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deptCode = tblEmployeeDepartment.getValueAt(tblEmployeeDepartment.getSelectedRow(), 0).toString();

                db.load(deptCode);
                setTextBox();
                buttonUpdateEnable(true);

            }
        });
    }


    public void clearFields(){
        txtDepartmentId.setText("");
        txtManagerName.setText("");
        txtTelephoneNo.setText("");
        txtDepartmentName.setText("");
    }

    public void dataAdd(){
        db.code = txtDepartmentId.getText();
        db.departmentName = txtDepartmentName.getText();
        db.telephoneNo = txtTelephoneNo.getText();
        db.manager = txtManagerName.getText();
    }

    private void buttonSaveEnable(boolean enable){
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
    }

    private void buttonNewEnable(boolean enable){
        btnNew.setEnabled(enable);
        btnSave.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        btnUpdate.setEnabled(!enable);
    }

    private  void buttonUpdateEnable(boolean enable){
        btnSave.setEnabled(!enable);
        btnUpdate.setEnabled(enable);
        btnDelete.setEnabled(enable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNew){
            clearFields();
            buttonSaveEnable(true);
        }
        if(e.getSource() == btnSave){
            dataAdd();
            if(!db.validate()) {
                JOptionPane.showMessageDialog(this,"Data validation Fail",
                        "Validator",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(db.save()) {
                owner.updateDepartmentTable();
                JOptionPane.showMessageDialog(this,
                        "Record Added Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployeeDepartment.setModel(new DefaultTableModel(db.departmentDetailsAll(), departmentFieldNames));
                }catch(Exception ex){
                    System.out.println(ex);
                }
                clearFields();
                buttonNewEnable(true);
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
                owner.updateDepartmentTable();
                JOptionPane.showMessageDialog(this,
                        "Record Updated Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployeeDepartment.setModel(new DefaultTableModel(db.departmentDetailsAll(), departmentFieldNames));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                clearFields();
                buttonNewEnable(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error In Record Update.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }

        }

        if(e.getSource() == btnDelete){
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure that you want to Delete this record?","Delete",JOptionPane.YES_NO_OPTION);
            if(option == 0 && db.delete(txtDepartmentId.getText())) {
                tblEmployeeDepartment.clearSelection();
                tblEmployeeDepartment.setModel(new DefaultTableModel(db.departmentDetailsAll(), departmentFieldNames));
                clearFields();
                buttonSaveEnable(true);
                JOptionPane.showMessageDialog(this,
                        "Record Deleted Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(option != 1) JOptionPane.showMessageDialog(this,
                        "Error In Record Delete.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == btnCancel){
            this.dispose();
        }
    }
}
