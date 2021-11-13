package com.elenits.egrasia2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.io.IOException;

public class Edit extends JFrame {
    private JPanel panel1;
    private JLabel label1;
    private JLabel idLabel;
    private JTextField textField1;
    private JLabel nameLabel;
    private JLabel speciesLabel;
    private JLabel weightLabel;
    private JLabel maxAgeLabel;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton enterButton;
    private List<Animal> animals;

    public  Edit(int AnimalID, Animal animal) {
        setTitle("Edit by ID");
        setPreferredSize(new Dimension(600, 500));
        setContentPane(panel1);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack(); //sets the panel
        setLocationRelativeTo(null); //makes the app appear at the center of the screen
        setVisible(true);
        
        textField1.setText(Integer.toString(animal.getId())); //the previous data
        textField2.setText(animal.getName());
        textField3.setText(animal.getSpecies());
        textField4.setText(Integer.toString(animal.getWeight()));
        textField5.setText(Integer.toString(animal.getMaxAge()));


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream("animals.ser");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = new ObjectInputStream(fileInputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                List<Animal> animals = null;
                try {
                    animals = (List<Animal>) objectInputStream.readObject();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    objectInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    fileInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                for (Animal animal : animals){
                    if (animal.getId() == AnimalID){
                        String input1 = textField1.getText(); //get the inputs from the textfields
                        String input2 = textField2.getText();
                        String input3 = textField3.getText();
                        String input4 = textField4.getText();
                        String input5 = textField5.getText();

                        animal.setId(Integer.parseInt(input1)); //pass the inputs to the setters of "Animal"
                        animal.setName(input2);
                        animal.setSpecies(input3);
                        animal.setWeight(Integer.parseInt(input4));
                        animal.setMaxAge(Integer.parseInt(input5));
                        FileOutputStream fileOutputStream = null; //writes the file
                        try {
                            fileOutputStream = new FileOutputStream("animals.ser");
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        ObjectOutputStream objectOutputStream = null;
                        try {
                            objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            objectOutputStream.writeObject(animals);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            objectOutputStream.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            fileOutputStream.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }
                }

                StringBuilder builder = new StringBuilder();
                builder.append("SUCCESS!");
                setVisible(false);

                JOptionPane.showMessageDialog(Edit.this, builder.toString()); // prints to a new window

            }
        });
    }
}