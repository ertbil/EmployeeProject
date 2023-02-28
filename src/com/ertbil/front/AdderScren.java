package com.ertbil.front;

import com.ertbil.back.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdderScren extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel adLabel;
    private JLabel soyadLabel;
    private JLabel departLabel;
    private JLabel maasLabel;
    private JButton ekleButton;
    private JButton iptalButton;
    private JPanel panel1;

    public AdderScren() {
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
                ControlPanel controlPanel = new ControlPanel();

            }
        });
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = textField1.getText();
                String soyad = textField2.getText();
                String departman = textField3.getText();
                int maas = Integer.parseInt(textField4.getText());
                Connector.addEmployee(ad,soyad,departman,maas);

                JOptionPane.showMessageDialog(panel1,"Çalışan Eklendi");
                setVisible(false);
                ControlPanel controlPanel = new ControlPanel();
            }
        });
    }
}
