package com.ertbil.front;

import com.ertbil.back.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAdderScr extends JFrame{
    private JPanel panel1;
    private JTextField usernameF;
    private JPasswordField passwordF;
    private JPasswordField passwordF2;
    private JButton ekle;
    private JButton iptalButton;
    private JLabel uyari;

    public AdminAdderScr() {
        add(panel1);
        setVisible(true);
        setSize(800, 600);
        setResizable(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iptalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LogControlPanel controlPanel = new LogControlPanel();

            }
        });
        ekle.addActionListener(new ActionListener() {
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
                    Connector.addAdmin(username,password1);

                    JOptionPane.showMessageDialog(panel1,"Çalışan Eklendi");
                    setVisible(false);
                    LogControlPanel controlPanel = new LogControlPanel();

                }

            }
        });
    }
}
