package com.ertbil.front;

import com.ertbil.back.Connector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class Updater extends JFrame{
    private JPanel panel1;
    private JTextField adF;
    private JTextField departF;
    private JTextField maasF;
    private JButton güncelleButton;
    private JButton iptalButton;
    private JTextField soyadF;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getAdF() {
        return adF;
    }

    public void setAdF(JTextField adF) {
        this.adF = adF;
    }

    public JTextField getDepartF() {
        return departF;
    }

    public void setDepartF(JTextField departF) {
        this.departF = departF;
    }

    public JTextField getMaasF() {
        return maasF;
    }

    public void setMaasF(JTextField maasF) {
        this.maasF = maasF;
    }

    public JTextField getSoyadF() {
        return soyadF;
    }

    public void setSoyadF(JTextField soyadF) {
        this.soyadF = soyadF;
    }

    public Updater() {
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
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ad = adF.getText();
                String soyad = soyadF.getText();
                String departman = departF.getText();
                int maas = Integer.parseInt(maasF.getText());
                Connector.updateEmployee(ad,soyad,departman,maas,id);

                JOptionPane.showMessageDialog(panel1,"Çalışan Güncellendi");
                setVisible(false);
                ControlPanel controlPanel = new ControlPanel();
            }
        });
    }
}
