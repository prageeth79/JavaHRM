import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DesignationInfo extends JFrame implements ActionListener {
    private JLabel lblCode, lblDesignation;
    private JTextField txtCode, txtDesignation;
    private Font fntLblFont,fntTxtFont,fntBtnFont;

    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tblEmployeeDesignation;
    private JScrollPane tblEmpDesigPane;
    private final String[] designationFieldNames = {"Code", "Designation"};
    private final Designation db = new Designation();

    public DesignationInfo(){
        setWindow();
        setComponents(db);
        addComponent();
        addTextBoxForcus();
        gridSelection();
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

                if (!db.matchCode(txtCode.getText())) {
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

                if (!db.matchDesigName(txtDesignation.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid Designation, it must contains only letters and space",
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
            if(!db.validate()) {
                JOptionPane.showMessageDialog(this,"Data validation Fail",
                        "Validator",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(db.save()) {
                JOptionPane.showMessageDialog(this,
                        "Record Added Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
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
                    tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
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
            if(db.delete(txtCode.getText())) {
                tblEmployeeDesignation.clearSelection();
                tblEmployeeDesignation.setModel(new DefaultTableModel(db.designationDetailsAll(), designationFieldNames));
                clearFields();
                JOptionPane.showMessageDialog(this,
                        "Record Deleted Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this,
                        "Error In Record Delete.", "ADD RECORD", JOptionPane.ERROR_MESSAGE);
            }
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

            }
        });
    }
}
