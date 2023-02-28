package com.ertbil.front;

import com.ertbil.back.Connector;
import com.ertbil.back.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlPanel extends JFrame{
    private JPanel panel1;
    private JButton ekle;
    private JButton sil;
    private JButton guncelleButton;
    private JTextField textField;
    private JTable table1;
    private JButton geri;
    private JButton exit;
    DefaultTableModel model;
    Connector connector = new Connector();



    public void calisanGoruntule(){

        model.setRowCount(0);
        ArrayList<Employee> employees = new ArrayList<Employee>();

       employees= connector.calisanlariGetir();

       if (employees != null){
           for (Employee employee: employees){
               Object[] eklenecek= {employee.getId(),employee.getAd(),employee.getSoyad(),employee.getDepart(),employee.getMaas()};
               model.addRow(eklenecek);
           }

       }
       else {
           System.out.println("Tablo Alınamadı");

       }



    }




    public ControlPanel(){
        table1.setModel(new DefaultTableModel(null, new String[]{"ID","Ad","Soyad","Departman","Maaş"}));
        model = (DefaultTableModel) table1.getModel();

        add(panel1);
        setVisible(true);
        setSize(800, 600);
        setResizable(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calisanGoruntule();


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainMenu menu = new MainMenu();

            }
        });
        ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdderScren adderScren = new AdderScren();
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                String ara =textField.getText();

                dianmikAra(ara);

            }
        });
        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table1.getSelectedRow();
                String seciliID = String.valueOf(model.getValueAt(selected,0));
                String seciliAd= (String) model.getValueAt(selected,1);
                String seciliSoyad= (String) model.getValueAt(selected,2);
                String seciliDepartman= (String) model.getValueAt(selected,3);
                int seciliMaas= (Integer) model.getValueAt(selected,4);
                Updater updater = new Updater();

                updater.getAdF().setText(seciliAd);
                updater.getSoyadF().setText(seciliSoyad);
                updater.getDepartF().setText(seciliDepartman);
                updater.getMaasF().setText(String.valueOf(seciliMaas));
                updater.setId(Integer.parseInt(seciliID));
                setVisible(false);



            }
        });
        sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table1.getSelectedRow();
                String seciliID = String.valueOf(model.getValueAt(selected,0));


               int cevap = JOptionPane.showConfirmDialog(panel1,"Silmek İstediğinize Emin misiniz?");

               if (cevap == JOptionPane.YES_OPTION){
                   setVisible(false);
                   Connector.deleteEmployee(Integer.parseInt(seciliID));
                   ControlPanel controlPanel = new ControlPanel();

               }

            }
        });
    }

    private void dianmikAra(String ara) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(ara));
    }
}
