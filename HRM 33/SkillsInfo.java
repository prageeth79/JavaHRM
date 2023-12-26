import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillsInfo extends JDialog  implements ActionListener {
    private JTextField txtSkillName;
    private JTextArea txtDescription;
    private JLabel lblSkillName, lblDescription;
    private int iSkillId;
    private JButton btnNew, btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tblSkillTable;
    private JScrollPane tblSkillPane;
    private final String[] skillsFieldNames = {"Skill ID", "Skill Name", "Description"};
    private final Skill db = new Skill();
    private final PersonalInfo owner;

    public SkillsInfo(PersonalInfo owner){
        super(owner,"Skills Info",true);
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
        SkillsInfo me = this;

    }
    public void initComponent(){
        String[][] data;

        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 12);

        lblSkillName = new JLabel("Skill Name");
        lblSkillName.setBounds(10, 10,160,25);
        lblSkillName.setFont(fntLblFont);

        txtSkillName = new JTextField();
        txtSkillName.setBounds(200,10,350, 25);
        txtSkillName.setFont(fntTxtFont);

        lblDescription = new JLabel("Description");
        lblDescription.setBounds(10,40, 160,25);
        lblDescription.setFont(fntLblFont);

        txtDescription = new JTextArea();
        txtDescription.setBounds(200, 40, 350,80);
        txtDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtDescription.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("images/new16.png");
        btnNew = new JButton("NEW");
        btnNew.setBounds(10, 130, 110, 30);
        btnNew.setFont(fntBtnFont);
        btnNew.setIcon(inew);
        btnNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("images/save16.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(130, 130, 110, 30);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);
        btnSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("images/update16.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(250, 130, 110, 30);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);
        btnUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("images/delete16.png");
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(370, 130, 110, 30);
        btnDelete.setFont(fntBtnFont);
        btnDelete.setIcon(idelete);
        btnDelete.addActionListener(this);

        ImageIcon icancel = new ImageIcon("images/close16.png");
        btnCancel = new JButton("CLOSE");
        btnCancel.setBounds(490, 130, 110, 30);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
        btnCancel.addActionListener(this);

        data = db.skillsDetailsAll();

        tblSkillTable = new JTable(data, skillsFieldNames);
        tblSkillTable.setFont(fntTxtFont);
        tblSkillPane = new JScrollPane(tblSkillTable);
        tblSkillPane.setBounds(10, 180, 590, 100);
        tblSkillPane.setVisible(true);
    }

    public void addComponent(){

        this.add(lblSkillName);
        this.add(txtSkillName);
        this.add(lblDescription);
        this.add(txtDescription);
        this.add(tblSkillPane);

        this.add(btnNew);
        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnCancel);
        this.add(btnDelete);
    }

    private void setTextBox(){
        iSkillId = db.skillID;
        txtSkillName.setText(db.skillName);
        txtDescription.setText(db.description);
    }


    private void setWindow(){
        this.setTitle("Skill Information");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(630,350);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    public  void gridSelection(){

        ListSelectionModel tblEmpDeptMod = tblSkillTable.getSelectionModel();
        tblEmpDeptMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmpDeptMod.addListSelectionListener(new ListSelectionListener(){
            String skillId;
            @Override
            public void valueChanged(ListSelectionEvent e) {
                skillId = tblSkillTable.getValueAt(tblSkillTable.getSelectedRow(), 0).toString();

                db.load(Integer.parseInt(skillId));
                setTextBox();
                buttonUpdateEnable(true);

            }
        });
    }


    public void clearFields(){
        iSkillId = 0;
        txtDescription.setText("");
        txtSkillName.setText("");
    }

    public void dataAdd(){
        db.skillID = iSkillId;
        db.skillName = txtSkillName.getText();
        db.description = txtDescription.getText();
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
                JOptionPane.showMessageDialog(this,
                        "Record Added Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
              //  try {
                    tblSkillTable.setModel(new DefaultTableModel(db.skillsDetailsAll(), skillsFieldNames));
              //  }catch(Exception ex){
              //      System.out.println(ex);
              //  }
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
                JOptionPane.showMessageDialog(this,
                        "Record Updated Successfully.", "ADD RECORD", JOptionPane.INFORMATION_MESSAGE);
                try {
                    tblSkillTable.setModel(new DefaultTableModel(db.skillsDetailsAll(), skillsFieldNames));
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
            if(option == 0 && db.delete(iSkillId)) {
                tblSkillTable.clearSelection();
                tblSkillTable.setModel(new DefaultTableModel(db.skillsDetailsAll(), skillsFieldNames));
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
