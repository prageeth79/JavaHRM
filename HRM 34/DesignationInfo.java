import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DesignationInfo extends JDialog implements ActionListener {
    private JLabel lblCode, lblDesignation;
    private JTextField txtCode, txtDesignation;
    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tblEmployeeDesignation;
    private JScrollPane tblEmpDesigPane;
    private final String[] designationFieldNames = {"Code", "Designation"};
    private final Designation db = new Designation();

    private final PersonalInfo owner;

    public DesignationInfo(PersonalInfo owner){
        super(owner,"Designation Info",true);
        setWindow();
        setComponents(db);
        addComponent();
        addTextBoxForcus();
        gridSelection();
        this.owner = owner;
        buttonNewEnable(true);
        this.setVisible(true);

    }

    private void setWindow(){
        this.setTitle("Personal Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(630,300);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    private  void setComponents(Designation db) {

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 12);

        lblCode = new JLabel("Designation No");
        lblCode.setBounds(10,10, 150, 25);
        lblCode.setFont(fntLblFont);

        txtCode = new JTextField();
        txtCode.setBounds(200, 10, 250, 25);
        txtCode.setFont(fntTxtFont);

        lblDesignation = new JLabel("Employee Full Name");
        lblDesignation.setBounds(10, 40, 250, 25);
        lblDesignation.setFont(fntLblFont);

        txtDesignation = new JTextField();
        txtDesignation.setBounds(200, 40, 250, 25);
        txtDesignation.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("images/new16.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 80, 110, 30);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("images/save16.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(130, 80, 110, 30);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("images/update16.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(250, 80, 110, 30);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("images/delete16.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(370, 80, 110, 30);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("images/close16.png");
        btnCancel = new JButton("CLOSE");
        btnCancel.setBounds(490, 80, 110, 30);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);

        String[][] data = db.designationDetailsAll();
        tblEmployeeDesignation = new JTable(data, designationFieldNames);
        tblEmployeeDesignation.setFont(fntTxtFont);
        tblEmpDesigPane = new JScrollPane(tblEmployeeDesignation);
        tblEmpDesigPane.setBounds(10, 130, 590, 100);
        tblEmpDesigPane.setVisible(true);
    }

    private void clearFields(){
        txtCode.setText("");
        txtDesignation.setText("");
    }

    private void loadTextbox(){
        txtCode.setText(db.code);
        txtDesignation.setText(db.designation);
    }

    private void dataAdd(){
        db.code = txtCode.getText();
        db.designation = txtDesignation.getText();
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

    private void addTextBoxForcus() {
        DesignationInfo me = this;
        txtCode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.validator.matchNumber(txtCode.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Designation Code it must contains only numbers",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        txtDesignation.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.validator.matchCharacters(txtDesignation.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Designation, it must contains only letters and space",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buttonSaveEnable(boolean enable){
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        txtCode.setEnabled(enable);
    }

    private void buttonNewEnable(boolean enable){
        btnNew.setEnabled(enable);
        btnSave.setEnabled(!enable);
        btnDelete.setEnabled(!enable);
        btnUpdate.setEnabled(!enable);
        txtCode.setEnabled(enable);
    }

    private  void buttonUpdateEnable(boolean enable){
        btnSave.setEnabled(!enable);
        btnUpdate.setEnabled(enable);
        btnDelete.setEnabled(enable);
        txtCode.setEnabled(!enable);
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
                owner.updateDesignationTable();
                JOptionPane.showMessageDialog(this,
                        "Record Added Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
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
                owner.updateDesignationTable();
                JOptionPane.showMessageDialog(this,
                        "Record Updated Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
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
            if(option == 0 && db.delete(txtCode.getText())) {
                tblEmployeeDesignation.clearSelection();
                tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
                clearFields();
                buttonNewEnable(true);
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

    private void gridSelection() {

        ListSelectionModel tblEmpDesigMod = tblEmployeeDesignation.getSelectionModel();
        tblEmpDesigMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDesigMod.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String desCode;
                desCode = tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 0).toString();
                db.load(desCode);
                loadTextbox();
                buttonUpdateEnable(true);
            }
        });
    }
}
