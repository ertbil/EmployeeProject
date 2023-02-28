package com.ertbil.front;

import com.ertbil.back.Admin;
import com.ertbil.back.Connector;
import com.ertbil.back.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LogControlPanel extends JFrame{
    private JPanel panel1;
    private JButton sil;
    private JButton guncelle;
    private JTextField textField1;
    private JTable table1;
    private JButton don;
    private JButton exit;
    private JButton ekle;
    DefaultTableModel model;
    Connector connector = new Connector();

        public void adminGoruntule() {

            model.setRowCount(0);
            ArrayList<Admin> admins = new ArrayList<Admin>();

            admins = connector.adminleriGetir();

            if (admins != null) {
                for (Admin admin : admins) {
                    Object[] eklenecek = {admin.getUsername(), admin.getPassword()};
                    model.addRow(eklenecek);
                }

            } else {
                System.out.println("Tablo Alınamadı");

            }
        }

        public LogControlPanel(){
            table1.setModel(new DefaultTableModel(null, new String[]{"Kullanıcı Adı","Şifre"}));
            model = (DefaultTableModel) table1.getModel();

            add(panel1);
            setVisible(true);
            setSize(800, 600);
            setResizable(true);
            setFocusable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminGoruntule();

            ekle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                   AdminAdderScr adderScren = new AdminAdderScr();

                }
            });
            don.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    MainMenu menu = new MainMenu();

                }
            });
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }
            });
            sil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int selected = table1.getSelectedRow();
                    String seciliUsername = String.valueOf(model.getValueAt(selected,0));


                    int cevap = JOptionPane.showConfirmDialog(panel1,"Silmek İstediğinize Emin misiniz?");

                    if (cevap == JOptionPane.YES_OPTION){
                        setVisible(false);
                        Connector.deleteAdmin(seciliUsername);
                        LogControlPanel controlPanel = new LogControlPanel();

                }
            }});

            textField1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    String ara =textField1.getText();

                    dianmikAra(ara);
                }
            });
            guncelle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selected = table1.getSelectedRow();
                    String seciliUsername = String.valueOf(model.getValueAt(selected,0));
                    String seciliPassword= (String) model.getValueAt(selected,1);
                    AdminUpdaterScr.setFirstUsername(seciliUsername);


                    AdminUpdaterScr updater = new AdminUpdaterScr();

                    updater.getUsernameF().setText(seciliUsername);
                    updater.getPasswordF().setText(seciliPassword);
                    updater.getPasswordF2().setText(seciliPassword);


                    setVisible(false);

                }
            });
        }

    private void dianmikAra(String ara) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(ara));
    }

}

