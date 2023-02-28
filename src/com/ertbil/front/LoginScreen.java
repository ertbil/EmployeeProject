package com.ertbil.front;

import com.ertbil.back.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton girisButton;
    private JButton forgetButton;
    private JPanel imgpanel;
    BufferedImage image;

    {
        try {
            image = ImageIO.read(new File("C:\\Users\\oguzy\\IdeaProjects\\EmployeeProject2\\src\\com\\ertbil\\front\\CPapnmTd_400x400.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image,imgpanel.getX()+110,imgpanel.getY()-40, image.getWidth()/5 ,image.getHeight()/5,this);
    }

    public LoginScreen() {
        add(panel1);
        setVisible(true);
        setSize(800, 600);
        setResizable(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = (String) passwordField1.getText();

                if (Connector.logControl(username,password)){
                    setVisible(false);
                    MainMenu menu = new MainMenu();

                }
                else {
                    JOptionPane.showMessageDialog(panel1,"Girişde hata oldu\nOlası Sebepler\n1-kullanıcı adı veya parola yanlış" +
                            "\nServer Bağlantı hatası");
                    textField1.setText("");
                    passwordField1.setText("");
                }
            }
        });
        forgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1,"Burayı yapmaya üşendim google'dan\nlocalhost'a gir oradan bulursun");
                
            }
        });

    }
}

