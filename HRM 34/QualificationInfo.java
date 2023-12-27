import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QualificationInfo extends JDialog implements ActionListener {
    private JTextField txtId, txtQualification;
    private JComboBox cmbType;
    private JLabel lblId, lblQualification, lblType;
    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tblQualificationTable;
    private JScrollPane tblQualificationTablePane;
    private final String[] departmentFieldNames = {"Code", "Department", "Tel", "Manager"};
    private final Qualification db = new Qualification();
    private final PersonalInfo owner;

    public QualificationInfo(PersonalInfo owner){
        super(owner,"Department Info",true);
        setWindow();
        initComponent();
        addComponent();
        gridSelection();
        buttonNewEnable(true);
        this.owner = owner;
        this.setVisible(true);

    }

    public void initComponent(){
        String[][] data;

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 12);

        lblId = new JLabel("id");
        lblId.setBounds(10,10, 160,25);
        lblId.setFont(fntLblFont);

        txtId = new JTextField();
        txtId.setBounds(200,10,200,25);
        txtId.setFont(fntTxtFont);
        txtId.setEnabled(false);

        lblQualification = new JLabel("Qualification");
        lblQualification.setBounds(10, 40,160,25);
        lblQualification.setFont(fntLblFont);

        txtQualification = new JTextField();
        txtQualification.setBounds(200,40,200, 25);
        txtQualification.setFont(fntTxtFont);

        lblType = new JLabel("Type");
        lblType.setBounds(10,70, 160,25);
        lblType.setFont(fntLblFont);

        String data1[] = {"IT", "Computer Science", "Management","Arts","Engineering"};
        cmbType = new JComboBox(data1);
        cmbType.setBounds(200, 70, 200,25);
        cmbType.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("images/new16.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 140, 110, 30);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("images/save16.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(130, 140, 110, 30);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("images/update16.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(250, 140, 110, 30);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("images/delete16.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(370, 140, 110, 30);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("images/close16.png");
        btnCancel = new JButton("CLOSE");
        btnCancel.setBounds(490, 140, 110, 30);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);

        data = db.qualificationDetailsAll();

        tblQualificationTable = new JTable(data, departmentFieldNames);
        tblQualificationTable.setFont(fntTxtFont);
        tblQualificationTablePane = new JScrollPane(tblQualificationTable);
        tblQualificationTablePane.setBounds(10, 190, 590, 100);
        tblQualificationTablePane.setVisible(true);
    }

    public void addComponent(){
        this.add(lblId);
        this.add(txtId);
        this.add(lblQualification);
        this.add(txtQualification);
        this.add(lblType);
        this.add(cmbType);
        this.add(tblQualificationTablePane);

        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnCancel);
        this.add(btnDelete);
    }

    private void setTextBox(){
        txtId.setText(db.id + "");
        txtQualification.setText(db.qualification);
        cmbType.setSelectedItem(db.type);
    }


    private void setWindow(){
        this.setTitle("Qualification Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(630,350);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    public  void gridSelection(){

        ListSelectionModel tblEmpDeptMod = tblQualificationTable.getSelectionModel();
        tblEmpDeptMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDeptMod.addListSelectionListener(new ListSelectionListener(){
            String id;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                id = tblQualificationTable.getValueAt(tblQualificationTable.getSelectedRow(), 0).toString();

                db.load(Integer.parseInt(id));
                setTextBox();
                buttonUpdateEnable(true);

            }
        });
    }


    public void clearFields(){
        txtId.setText("");
        txtQualification.setText("");
    }

    public void dataAdd(){
        db.id = Integer.parseInt(txtId.getText());
        db.qualification = txtQualification.getText();
        db.type = cmbType.getSelectedItem().toString();
    }

    private void buttonSaveEnable(boolean enable){
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        txtId.setEnabled(enable);
    }

    private void buttonNewEnable(boolean enable){
        btnNew.setEnabled(enable);
        btnSave.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        btnUpdate.setEnabled(!enable);
        txtId.setEnabled(enable);
    }

    private  void buttonUpdateEnable(boolean enable){
        btnSave.setEnabled(!enable);
        btnUpdate.setEnabled(enable);
        btnDelete.setEnabled(enable);
        txtId.setEnabled(!enable);
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
                    tblQualificationTable.setModel(new DefaultTableModel(db.qualificationDetailsAll(), departmentFieldNames));
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
                    tblQualificationTable.setModel(new DefaultTableModel(db.qualificationDetailsAll(), departmentFieldNames));
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
            if(option == 0 && db.delete(txtId.getText())) {
                tblQualificationTable.clearSelection();
                tblQualificationTable.setModel(new DefaultTableModel(db.qualificationDetailsAll(), departmentFieldNames));
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
