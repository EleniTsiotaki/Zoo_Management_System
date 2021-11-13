package com.elenits.egrasia2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class Delete extends JFrame {
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton DELETEButton;
    String message;
    public Delete(int AnimalID, Animal animal, List<Animal> animals){
        setTitle("Delete by ID");
        setPreferredSize(new Dimension(600, 500));
        setContentPane(panel1);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack(); //sets the panel
        setLocationRelativeTo(null); //makes the app appear at the center of the screen
        setVisible(true);
        label2.setText("ID: " + String.valueOf(animal.getId())); // print animal and its element to the form
        label3.setText("NAME: " + animal.getName());
        label4.setText("SPECIES: " + animal.getSpecies());
        label5.setText("WEIGHT(kg): " + String.valueOf(animal.getWeight()));
        label6.setText("MAX AGE(years): " + String.valueOf(animal.getMaxAge()));
        DELETEButton.addActionListener(new ActionListener() {
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
                java.util.List<Animal> animals = null;
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
                int count = 0;
                for (Animal animal1 : animals){
                    if (animal1.getId() == AnimalID){
                        animals.remove(count); // remove the object by its index
                        FileOutputStream fileOutputStream = null;
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

                        StringBuilder builder = new StringBuilder();
                        builder.append("SUCCESS!");
                        JOptionPane.showMessageDialog(Delete.this, builder.toString()); // prints to a new window
                        setVisible(false);

                    }
                    count+=1;
                }
            }
        });
    }
}
