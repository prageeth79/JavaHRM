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
        lblEBI.setBounds(460, 10, 150, 25);
        lblEBI.setFont(fntLblFont);

        chkEBI = new JCheckBox();
        chkEBI.setBounds(520, 10, 20, 20);
        chkEBI.setFont(fntTxtFont);

        lblEBII = new JLabel("EB II");
        lblEBII.setBounds(460, 40, 150, 25);
        lblEBII.setFont(fntLblFont);

        chkEBII = new JCheckBox();
        chkEBII.setBounds(520, 40, 20, 20);
        chkEBII.setFont(fntTxtFont);

        lblStatus = new JLabel("Status");
        lblStatus.setBounds(460, 70, 150, 25);
        lblStatus.setFont(fntLblFont);

        String[] EStatus = {"Working", "On leave","Retired"};
        cmbStatus = new JComboBox<>(EStatus);
        cmbStatus.setBounds(520, 70, 100, 25);
        cmbStatus.setFont(fntTxtFont);
    }
    protected void addTextBoxForcus(PersonalInfo me) {
        txtDateAppointment.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if (!db.validator.matchDate(txtDateAppointment.getText())) {
                    txtDateAppointment.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtDateMadePermanent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if (!db.validator.matchDate(txtDateMadePermanent.getText())) {
                    txtDateMadePermanent.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtIncrimentDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if (!db.validator.matchDate(txtIncrimentDate.getText())) {
                    txtIncrimentDate.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(me, "Invalid date format please enter a valid date",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        txtPersonalFileNo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                if (txtPersonalFileNo.getText().isEmpty()) {
                    txtPersonalFileNo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(me, "Personal File No is empty Please Fill the Number",
                            "Validator", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cmbClass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cmbClass.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                cmbClass.setBorder(null);
            }
        });
        cmbGrade.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cmbGrade.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                cmbGrade.setBorder(null);
            }
        });
        chkEBI.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                chkEBI.setBorderPainted(true);
                chkEBI.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                chkEBI.setBorder(null);
            }
        });
        chkEBII.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                chkEBII.setBorderPainted(true);
                chkEBII.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                chkEBII.setBorder(null);
            }
        });
        cmbStatus.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cmbStatus.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                cmbStatus.setBorder(null);
            }
        });
        btnNew.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnNew.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnNew.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        btnSave.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnSave.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnSave.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        btnUpdate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnUpdate.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnUpdate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        btnDelete.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnDelete.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnDelete.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
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
