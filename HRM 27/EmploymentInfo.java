import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EmploymentInfo extends  EmployeeInfo{
    protected JPanel panEmploymentInfo;
    private JLabel lblDateOfAppointment, lblDateMadePermanent,lblPersonalFileNo, lblIncrementDate,lblClass,lblGrade,lblEBI,lblEBII,lblStatus;
    protected JTextField txtDateAppointment, txtDateMadePermanent,txtPersonalFileNo,txtIncrimentDate;
    protected JComboBox<String> cmbClass, cmbGrade, cmbStatus;
    protected JCheckBox chkEBI,chkEBII;

    @Override
    protected  void setComponents(Employee db) {

        super.setComponents(db);
        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 16);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 14);
        Font fntBtnFont = new Font("Comic Sans", Font.BOLD, 14);

        panEmploymentInfo = new JPanel();
        panEmploymentInfo.setBounds(0, 0, 850, 300);
        panEmploymentInfo.setLayout(null);

        lblDateOfAppointment = new JLabel("Date of Appointment");
        lblDateOfAppointment.setBounds(10, 10, 250, 25);
        lblDateOfAppointment.setFont(fntLblFont);

        txtDateAppointment = new JTextField();
        txtDateAppointment.setBounds(200, 10, 100, 25);
        txtDateAppointment.setFont(fntTxtFont);

        lblDateMadePermanent = new JLabel("Date Made Permanent");
        lblDateMadePermanent.setBounds(10, 40, 250, 25);
        lblDateMadePermanent.setFont(fntLblFont);

        txtDateMadePermanent = new JTextField();
        txtDateMadePermanent.setBounds(200, 40, 100, 25);
        txtDateMadePermanent.setFont(fntTxtFont);

        lblPersonalFileNo = new JLabel("Personal File No");
        lblPersonalFileNo.setBounds(10, 70, 150, 25);
        lblPersonalFileNo.setFont(fntLblFont);

        txtPersonalFileNo = new JTextField();
        txtPersonalFileNo.setBounds(200, 70, 200, 25);
        txtPersonalFileNo.setFont(fntTxtFont);

        lblIncrementDate = new JLabel("Increment Date");
        lblIncrementDate.setBounds(10, 100, 150, 25);
        lblIncrementDate.setFont(fntLblFont);

        txtIncrimentDate = new JTextField();
        txtIncrimentDate.setBounds(200, 100, 100, 25);
        txtIncrimentDate.setFont(fntTxtFont);

        lblClass = new JLabel("Class");
        lblClass.setBounds(10, 130, 150, 25);
        lblClass.setFont(fntLblFont);

        String[] EClass = {"Class I", "Class II","Class III", "Supra"};
        cmbClass = new JComboBox<>(EClass);
        cmbClass.setBounds(200, 130, 100, 25);
        cmbClass.setFont(fntTxtFont);

        lblGrade = new JLabel("Grade");
        lblGrade.setBounds(10, 160, 150, 25);
        lblGrade.setFont(fntLblFont);

        String[] EGrade = {"Grade I", "Grade II","Grade III"};
        cmbGrade = new JComboBox<>(EGrade);
        cmbGrade.setBounds(200, 160, 100, 25);
        cmbGrade.setFont(fntTxtFont);

        lblEBI = new JLabel("EB I");
        lblEBI.setBounds(10, 190, 150, 25);
        lblEBI.setFont(fntLblFont);

        chkEBI = new JCheckBox();
        chkEBI.setBounds(200, 190, 25, 25);
        chkEBI.setFont(fntTxtFont);

        lblEBII = new JLabel("EB II");
        lblEBII.setBounds(10, 220, 250, 25);
        lblEBII.setFont(fntLblFont);

        chkEBII = new JCheckBox();
        chkEBII.setBounds(200, 220, 25, 25);
        chkEBII.setFont(fntTxtFont);

        lblStatus = new JLabel("Status");
        lblStatus.setBounds(460, 10, 150, 25);
        lblStatus.setFont(fntLblFont);

        String[] EStatus = {"Working", "On leave","Retired"};
        cmbStatus = new JComboBox<>(EStatus);
        cmbStatus.setBounds(520, 10, 100, 25);
        cmbStatus.setFont(fntTxtFont);
    }
    protected void addTextBoxForcus(PersonalInfo me) {
        txtDateAppointment.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.validator.matchDate(txtDateAppointment.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtDateMadePermanent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.validator.matchDate(txtDateMadePermanent.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtIncrimentDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!db.validator.matchDate(txtIncrimentDate.getText())) {
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtPersonalFileNo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (txtPersonalFileNo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(me, "Personal File No is empty Please Fill the Number",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    @Override
    protected void addComponents() {
        super.addComponents();
        panEmploymentInfo.add(lblClass);
        panEmploymentInfo.add(lblEBI);
        panEmploymentInfo.add(lblEBII);
        panEmploymentInfo.add(lblGrade);
        panEmploymentInfo.add(lblStatus);
        panEmploymentInfo.add(lblDateMadePermanent);
        panEmploymentInfo.add(lblDateOfAppointment);
        panEmploymentInfo.add(lblIncrementDate);
        panEmploymentInfo.add(lblPersonalFileNo);

        panEmploymentInfo.add(txtDateMadePermanent);
        panEmploymentInfo.add(txtIncrimentDate);
        panEmploymentInfo.add(txtDateAppointment);
        panEmploymentInfo.add(txtPersonalFileNo);

        panEmploymentInfo.add(cmbGrade);
        panEmploymentInfo.add(cmbStatus);
        panEmploymentInfo.add(cmbClass);

        panEmploymentInfo.add(chkEBI);
        panEmploymentInfo.add(chkEBII);

        tabEmployee.add("Employment Info",panEmploymentInfo);

    }
}
