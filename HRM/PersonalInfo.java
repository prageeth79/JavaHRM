import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PersonalInfo extends JFrame implements ActionListener {
    String sDesignationId, sDepartmentId;
    JLabel lblEmployeeNo, lblEmployeeFullName, lblEmployeeNameWithInit, lblEmployeeNIC, lblEmployeeGender,lblEmployeeDOFB, lblEmployeeDesignation, lblEmployeeDepartment,lblEmpSearch;
    JTextField txtEmployeeNo, txtEmployeeFullName, txtEmployeeNameWithInit, txtEmployeeNIC, txtEmployeeDOFB, txtEmployeeDesignation, txtEmployeeDepartment,txtEmpSearch;
    JComboBox cmbEmployeeGender,cmbEmpSearchField;
    JTable tblEmployeeDesignation,tblEmployeeDepartment,tblEmployee;
    JLabel lblTblEmpDesignation,lblTblEmpDepartment,lblTblEmployee;
    JScrollPane tblEmpDesigPane,tblEmpDepartPane,tblEmployeePane;
    JButton btnNew, btnSave, btnUpdate, btnCancel,btnAddDesig, btnAddDepart;
    Font fntLblFont,fntTxtFont,fntBtnFont;
    public PersonalInfo(){
        this.setWindow();
        this.setComponents();
        this.gridSelection();
        this.adjestTableColumn();
        this.addComponents();
        this.setVisible(true);
    }

    private  void setComponents(){

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

        lblTblEmpDesignation = new JLabel("Designation");
        lblTblEmpDesignation.setBounds(500, 10,300,20);
        String[][] data = new String[1][2];
        data = this.designationDetailsAll();
        String[] column = {"Code", "Designation"};
        tblEmployeeDesignation = new JTable(data, column);
        //tblEmployeeDesignation.setBounds(500, 10, 300, 100);
        tblEmployeeDesignation.setFont(fntTxtFont);
        tblEmpDesigPane = new JScrollPane(tblEmployeeDesignation);
        tblEmpDesigPane.setBounds(500, 25, 300, 100);
        tblEmpDesigPane.setVisible(true);

        lblTblEmpDepartment = new JLabel("Department");
        lblTblEmpDepartment.setBounds(500, 130,300,20);

        data = this.departmentDetailsAll();
        String[] column1 = {"Code", "Department", "Tel", "Manager"};
        tblEmployeeDepartment = new JTable(data, column1);
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

        data = this.employeeDetailsAll();
        String[] column3 = {"Code", "Full Name", "Name With Init","NIC","Date of Birth","Gender","Department","Designation"};
        tblEmployee = new JTable(data, column3);
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

        ImageIcon isave = new ImageIcon("save.png");
        btnSave = new JButton("SAVE");
        btnSave.setBounds(150, 260, 120, 40);
        btnSave.setFont(fntBtnFont);
        btnSave.setIcon(isave);

        ImageIcon iupdate = new ImageIcon("update.png");
        btnUpdate = new JButton("UPDAGE");
        btnUpdate.setBounds(290, 260, 120, 40);
        btnUpdate.setFont(fntBtnFont);
        btnUpdate.setIcon(iupdate);

        ImageIcon icancel = new ImageIcon("close.png");
        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(680, 260, 120, 40);
        btnCancel.setFont(fntBtnFont);
        btnCancel.setIcon(icancel);
    }

    private Connection dbConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm", "root", "");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    private ResultSet dbResult(Connection con, String sql){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[][] designationDetailsAll(){
        try{
            Connection con = this.dbConnect();
            ResultSet rs = this.dbResult(con,"select count(*) from Designation");
            //rs.last();
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][2];
            rs = this.dbResult(con,"select * from Designation");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                i++;
                System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }
            con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][2];
    }

    public String[][] employeeDetailsAll() {
        try{
            Connection con = this.dbConnect();
            ResultSet rs= this.dbResult(con, "select count(*) from Employee");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][8];
            rs = this.dbResult(con, "select e.*,d.depName,de.desigName from Employee e,Department D, Designation De where e.Designation = De.code and e.Department = D.code");
            int i = 0;
            while(rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                data[i][5] = rs.getString(6);
                data[i][6] = rs.getString(9);
                data[i][7] = rs.getString(10);
                i++;
                //System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }
            con.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][8];
    }

    public String[][] departmentDetailsAll() {
        try {
            Connection con = this.dbConnect();
            ResultSet rs = this.dbResult(con, "select count(*) from Department");
            rs.next();
            int rows = rs.getInt(1);
            String[][] data = new String[rows][4];
            rs = this.dbResult(con, "select * from Department");
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                i++;
//                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            con.close();
            return  data;
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[1][4];
    }

    private  void adjestTableColumn(){
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
    }

    private void setWindow(){
        this.setTitle("Personal Inforamtion");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(850,650);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == tblEmployeeDesignation) {
//            sDesignationId = tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 0).toString();
//            txtEmployeeDesignation.setText(tblEmployeeDesignation.getValueAt(tblEmployeeDesignation.getSelectedRow(), 1).toString());
//        }
    }
}
