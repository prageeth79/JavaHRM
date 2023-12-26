import javax.swing.*;
import java.awt.*;

public class Splash extends JDialog {
    private JLabel lblWelcome,lblCopyright;

    private void addComponent(){
        Font fntLblFont = new Font("Comic Sans", Font.BOLD, 40);
        Font fntTxtFont = new Font("Comic Sans", Font.PLAIN, 18);
        lblWelcome = new JLabel("Welcome to PN HRM");
        lblWelcome.setBounds(100,120,400,50);
        lblWelcome.setFont(fntLblFont);
        lblWelcome.setForeground(Color.BLUE);

        lblCopyright = new JLabel("Copyright (C) Prageeth Niranjan 2023");
        lblCopyright.setBounds(150,230,400,30);
        lblCopyright.setFont(fntTxtFont);
        lblCopyright.setForeground(Color.BLUE);
        add(lblCopyright);
        add(lblWelcome);
    }
    protected void setWindow() {
        this.setTitle("PN HRM");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 300);
        this.setUndecorated(true);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2);

        //this.getContentPane().setBackground(new Color(51,204,255));
    }
    public Splash(PersonalInfo me){
        setWindow();
        addComponent();
        this.setVisible(true);
    }
}
