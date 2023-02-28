package com.ertbil.front;

import com.ertbil.back.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUpdaterScr extends JFrame{
    private JPasswordField passwordF;
    private JPasswordField passwordF2;
    private JButton guncelle;
    private JButton iptalButton;
    private JLabel uyari;
    private JTextField usernameF;
    private JPanel panel1;
    private  static String firstUsername;

    public static String getFirstUsername() {
        return firstUsername;
    }

    public static void setFirstUsername(String firstUsername) {
        AdminUpdaterScr.firstUsername = firstUsername;
    }

    public AdminUpdaterScr() {
        add(panel1);
        setVisible(true);
        setSize(800, 600);
        setResizable(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uyari.setText("");
                String username = usernameF.getText();
                String password1 = passwordF.getText();
                String password2 = passwordF2.getText();

                if (!password1.equals(password2)){
                    uyari.setText("Parolalar Aynı değil");
                }
                else {
                    Connector.adminUpdater(username,password1);
                    setVisible(false);
                    LogControlPanel controlPanel = new LogControlPanel();
                }
            }
        });
        iptalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LogControlPanel controlPanel = new LogControlPanel();
            }
        });
    }

    public JPasswordField getPasswordF2() {
        return passwordF2;
    }

    public void setPasswordF2(JPasswordField passwordF2) {
        this.passwordF2 = passwordF2;
    }

    public JPasswordField getPasswordF() {
        return passwordF;
    }

    public void setPasswordF(JPasswordField passwordF) {
        this.passwordF = passwordF;
    }

    public JTextField getUsernameF() {
        return usernameF;
    }

    public void setUsernameF(JTextField usernameF) {
        this.usernameF = usernameF;
    }
}
