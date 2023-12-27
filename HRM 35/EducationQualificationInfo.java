import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EducationQualificationInfo extends  EmploymentInfo{
    protected JPanel panEducationQualificationInfo;
    private JLabel lblEduQualiId, lblQualification, lblInstitution,lblResult;
    private JTextField txtEduQualiId,txtInstitution;
    protected JComboBox<String> cmbQualification, cmbResult;
    protected JButton btnEduQualiNew, btnEduQualiSave, btnEduQualiUpdate, btnEduQualiDelete;
    protected JTable tblEduQualiTable;
    protected JScrollPane tblEduQualiTablePane;

    protected   final String[] eduQualiFieldNames = {"id","institution", "Qualification", "Result"};

    public final EducationQualification qualification = new EducationQualification();

    @Override
    protected  void setComponents(Employee db) {

        super.setComponents(db);
        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 11);

        panEducationQualificationInfo = new JPanel();
        panEducationQualificationInfo.setBounds(0, 0, 850, 300);
        panEducationQualificationInfo.setLayout(null);

        lblEduQualiId = new JLabel("Edu ID");
        lblEduQualiId.setBounds(10, 10, 250, 25);
        lblEduQualiId.setFont(fntLblFont);

        txtEduQualiId = new JTextField();
        txtEduQualiId.setBounds(200, 10, 100, 25);
        txtEduQualiId.setFont(fntTxtFont);
        txtEduQualiId.setEnabled(false);

        lblQualification = new JLabel("Qualification");
        lblQualification.setBounds(10, 40, 250, 25);
        lblQualification.setFont(fntLblFont);

        cmbQualification = new JComboBox<>(qualification.qualification.eduQualiCombo());
        cmbQualification.setBounds(200, 40, 200, 25);
        cmbQualification.setFont(fntTxtFont);

        lblInstitution = new JLabel("Institution");
        lblInstitution.setBounds(10, 70, 250, 25);
        lblInstitution.setFont(fntLblFont);

        txtInstitution = new JTextField();
        txtInstitution.setBounds(200, 70, 200, 25);
        txtInstitution.setFont(fntTxtFont);

        lblResult = new JLabel("Result");
        lblResult.setBounds(10, 100, 250, 25);
        lblResult.setFont(fntLblFont);

        String[] pResult = {"PhD", "M.Sc.","MA", "B.Sc.","B.Com","BA","HND","Diploma","Certificate"};
        cmbResult = new JComboBox<>(pResult);
        cmbResult.setBounds(200, 100, 100, 25);
        cmbResult.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("images/new16.png");
        btnEduQualiNew = new JButton("NEW");
        btnEduQualiNew.setBounds(10, 140, 100, 30);
        btnEduQualiNew.setFont(fntBtnFont);
        btnEduQualiNew.setIcon(inew);

        ImageIcon isave = new ImageIcon("images/save16.png");
        btnEduQualiSave = new JButton("SAVE");
        btnEduQualiSave.setBounds(120, 140, 100, 30);
        btnEduQualiSave.setFont(fntBtnFont);
        btnEduQualiSave.setIcon(isave);

        ImageIcon iupdate = new ImageIcon("images/update16.png");
        btnEduQualiUpdate = new JButton("UPDAGE");
        btnEduQualiUpdate.setBounds(230, 140, 100, 30);
        btnEduQualiUpdate.setFont(fntBtnFont);
        btnEduQualiUpdate.setIcon(iupdate);

        ImageIcon idelete = new ImageIcon("images/delete16.png");
        btnEduQualiDelete = new JButton("DELETE");
        btnEduQualiDelete.setBounds(340, 140, 100, 30);
        btnEduQualiDelete.setFont(fntBtnFont);
        btnEduQualiDelete.setIcon(idelete);

        String[][] data = new String[1][4];
        tblEduQualiTable = new JTable(data, eduQualiFieldNames);
        tblEduQualiTable.setFont(fntTxtFont);
        tblEduQualiTablePane = new JScrollPane(tblEduQualiTable);
        tblEduQualiTablePane.setBounds(450, 10, 350, 200);
        tblEduQualiTablePane.setVisible(true);

        enableEduQualiNewButton();

    }

    protected  void loadQualification(){
        txtEduQualiId.setText( "" + qualification.id);
        cmbQualification.setSelectedItem(Utility.craeateItem( "" + qualification.qualification.id, qualification.qualification.qualification));
        txtInstitution.setText(qualification.institute);
        cmbResult.setSelectedItem(qualification.result);
    }

    protected void qualificationTableSelection(){
        ListSelectionModel tblSkillModel = tblEduQualiTable.getSelectionModel();
        tblSkillModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSkillModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String scode = "";
                try {
                    scode = tblEduQualiTable.getValueAt(tblEduQualiTable.getSelectedRow(), 0).toString();
                }catch(Exception ex){
                    System.out.println(ex);
                    return;
                }
               qualification.load(Integer.parseInt(scode));
                loadQualification();
                enableEduQualiUpdateButton();

            }
        });
    }

    protected void loadEduQualiTable(String code){

        tblEduQualiTable.setModel(new DefaultTableModel(qualification.qualificationDetailsAll(code), eduQualiFieldNames));

    }
    @Override
    protected void addComponents() {
        super.addComponents();
        panEducationQualificationInfo.add(lblInstitution);
        panEducationQualificationInfo.add(lblQualification);
        panEducationQualificationInfo.add(lblInstitution);
        panEducationQualificationInfo.add(lblEduQualiId);
        panEducationQualificationInfo.add(txtEduQualiId);
        panEducationQualificationInfo.add(txtInstitution);
        panEducationQualificationInfo.add(cmbResult);
        panEducationQualificationInfo.add(cmbQualification);

        panEducationQualificationInfo.add(btnEduQualiNew);
        panEducationQualificationInfo.add(btnEduQualiSave);
        panEducationQualificationInfo.add(btnEduQualiUpdate);
        panEducationQualificationInfo.add(btnEduQualiDelete);

        panEducationQualificationInfo.add(tblEduQualiTablePane);

        tabEmployee.add("Education Qualification", panEducationQualificationInfo);

    }
    protected void enableEduQualiNewButton(){
        btnEduQualiNew.setEnabled(true);
        btnEduQualiSave.setEnabled(false);
        btnEduQualiUpdate.setEnabled(false);
        btnEduQualiDelete.setEnabled(false);
    }

    protected  void enableEduQualiSaveButton(){
        btnEduQualiNew.setEnabled(false);
        btnEduQualiSave.setEnabled(true);
        btnEduQualiUpdate.setEnabled(false);
        btnEduQualiDelete.setEnabled(false);
    }

    protected void enableEduQualiUpdateButton(){
        btnEduQualiNew.setEnabled(false);
        btnEduQualiSave.setEnabled(false);
        btnEduQualiUpdate.setEnabled(true);
        btnEduQualiDelete.setEnabled(true);
    }

    protected void eduQualiAdd(){

        qualification.id = (txtEduQualiId.getText().isEmpty())? 0: Integer.parseInt(txtEduQualiId.getText());
        qualification.employee.load(db.code);
        qualification.institute = txtInstitution.getText();
        qualification.qualification.load(Integer.parseInt(Utility.getComboID(cmbQualification.getSelectedItem().toString()).trim()));
        qualification.result = cmbResult.getSelectedItem().toString();
    }

    protected void txtEduQualiboxnew(){
        txtEduQualiId.setText("");
    }

    protected void enableEduQualiComponent(boolean enable){
        cmbQualification.setEnabled(enable);
        cmbResult.setEnabled(enable);
        btnEduQualiNew.setEnabled(enable);
        btnEduQualiSave.setEnabled(enable);
        btnEduQualiUpdate.setEnabled(enable);
        btnEduQualiDelete.setEnabled(enable);
    }

}
