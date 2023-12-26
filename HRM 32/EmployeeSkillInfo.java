import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EmployeeSkillInfo extends  EmploymentInfo implements ActionListener {
    protected JPanel panSkillInfo;
    private JLabel lblSkillId, lblSkillName, lblProficiency;
    private JTextField txtSkillId;
    protected JComboBox<String> cmbSkillName, cmbProficiency;
    private JButton btnSkillNew, btnSkillSave, btnSkillUpdate, btnSkillDelete;
    protected JTable tblSkillTable;
    protected JScrollPane tblSkillTablePane;

    protected   final String[] skillFiledNames = {"id","Skill Name", "Proficiency"};

    public final EmployeeSkill skill = new EmployeeSkill();

    @Override
    protected  void setComponents(Employee db) {

        super.setComponents(db);
        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 11);

        panSkillInfo = new JPanel();
        panSkillInfo.setBounds(0, 0, 850, 300);
        panSkillInfo.setLayout(null);

        lblSkillId = new JLabel("Skill ID");
        lblSkillId.setBounds(10, 10, 250, 25);
        lblSkillId.setFont(fntLblFont);

        txtSkillId = new JTextField();
        txtSkillId.setBounds(200, 10, 100, 25);
        txtSkillId.setFont(fntTxtFont);
        txtSkillId.setEnabled(false);

        lblSkillName = new JLabel("Skill Name");
        lblSkillName.setBounds(10, 40, 250, 25);
        lblSkillName.setFont(fntLblFont);

        cmbSkillName = new JComboBox<>(skill.skill.skillCombo());
        cmbSkillName.setBounds(200, 40, 200, 25);
        cmbSkillName.setFont(fntTxtFont);

        lblProficiency = new JLabel("Proficiency");
        lblProficiency.setBounds(10, 70, 250, 25);
        lblProficiency.setFont(fntLblFont);

        String[] pProficency = {"Excellent", "Very Good","Good", "Weak"};
        cmbProficiency = new JComboBox<>(pProficency);
        cmbProficiency.setBounds(200, 70, 100, 25);
        cmbProficiency.setFont(fntTxtFont);

        ImageIcon inew = new ImageIcon("new16.png");
        btnSkillNew = new JButton("NEW");
        btnSkillNew.setBounds(10, 110, 100, 30);
        btnSkillNew.setFont(fntBtnFont);
        btnSkillNew.setIcon(inew);
        btnSkillNew.addActionListener(this);

        ImageIcon isave = new ImageIcon("save16.png");
        btnSkillSave = new JButton("SAVE");
        btnSkillSave.setBounds(120, 110, 100, 30);
        btnSkillSave.setFont(fntBtnFont);
        btnSkillSave.setIcon(isave);
        btnSkillSave.addActionListener(this);

        ImageIcon iupdate = new ImageIcon("update16.png");
        btnSkillUpdate = new JButton("UPDAGE");
        btnSkillUpdate.setBounds(230, 110, 100, 30);
        btnSkillUpdate.setFont(fntBtnFont);
        btnSkillUpdate.setIcon(iupdate);
        btnSkillUpdate.addActionListener(this);

        ImageIcon idelete = new ImageIcon("delete16.png");
        btnSkillDelete = new JButton("DELETE");
        btnSkillDelete.setBounds(340, 110, 100, 30);
        btnSkillDelete.setFont(fntBtnFont);
        btnSkillDelete.setIcon(idelete);
        btnSkillDelete.addActionListener(this);
        String[][] data = new String[1][3];
        tblSkillTable = new JTable(data, skillFiledNames);
        tblSkillTable.setFont(fntTxtFont);
        tblSkillTablePane = new JScrollPane(tblSkillTable);
        tblSkillTablePane.setBounds(450, 10, 350, 200);
        tblSkillTablePane.setVisible(true);

    }

    protected void loadSkillTable(String code){

        tblSkillTable.setModel(new DefaultTableModel(skill.skillsDetailsAll(code), skillFiledNames));

    }
    @Override
    protected void addComponents() {
        super.addComponents();
        panSkillInfo.add(lblProficiency);
        panSkillInfo.add(lblSkillName);
        panSkillInfo.add(lblSkillId);
        panSkillInfo.add(txtSkillId);
        panSkillInfo.add(cmbProficiency);
        panSkillInfo.add(cmbSkillName);

        panSkillInfo.add(btnSkillNew);
        panSkillInfo.add(btnSkillSave);
        panSkillInfo.add(btnSkillUpdate);
        panSkillInfo.add(btnSkillDelete);

        panSkillInfo.add(tblSkillTablePane);

        tabEmployee.add("Skill Info", panSkillInfo);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
