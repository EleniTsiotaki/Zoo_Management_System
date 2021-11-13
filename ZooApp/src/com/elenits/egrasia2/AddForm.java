package com.elenits.egrasia2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddForm extends JFrame {
    private JPanel panel1;
    private JLabel label1;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel speciesLabel;
    private JLabel weightLabel;
    private JLabel maxAgeLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton enterButton;
    List<Animal> animals = new ArrayList<>();

    public AddForm() {
        setTitle("Add a new animal");
        setPreferredSize(new Dimension(600, 500));
        setContentPane(panel1);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack(); //sets the panel
        setLocationRelativeTo(null); //makes the app appear at the center of the screen
        setVisible(true);


        enterButton.addActionListener(new ActionListener() { //BUTTON TO ADD NEW ANIMALS
            @Override
            public void actionPerformed(ActionEvent e) {
                deserialize("animals.ser"); //deserialize first so as to not overwrite the file with the new animals

                Animal animal = new Animal(Integer.parseInt(textField1.getText()), textField2.getText(), textField3.getText(), Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()));
                animals.add(new Animal(animal.getId(), animal.getName(), animal.getSpecies(), animal.getWeight(), animal.getMaxAge()));
                //adds to the arraylist

                serialize("animals.ser");
                deserialize("animals.ser");

                StringBuilder builder = new StringBuilder();
                builder.append("SUCCESS!");

                JOptionPane.showMessageDialog(AddForm.this, builder.toString()); // prints to a new window
                setVisible(false);
            }
        });
}
public void deserialize(String Filename) {
    try {
        FileInputStream f = new FileInputStream(Filename); //read file objects and puts them in the list "animals"
        ObjectInputStream o = new ObjectInputStream(f);
        animals = (List<Animal>) o.readObject();
        o.close();
        f.close();
    } catch (IOException | ClassNotFoundException h) {
        h.printStackTrace();
    }
}
public void serialize(String Filename){
    try{
        FileOutputStream f = new FileOutputStream(Filename); //writes to file
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(animals);
        o.close();
        f.close();
    }
    catch (IOException h){
        h.printStackTrace();
    }
}
}