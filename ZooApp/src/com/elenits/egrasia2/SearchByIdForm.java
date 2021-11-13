package com.elenits.egrasia2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class SearchByIdForm extends JFrame{
    private JLabel label1;
    private JTextField textField1;
    private JPanel panel1;
    private JButton ENTERButton;
    private int AnimalID;
    private boolean x;

    public SearchByIdForm(){
        setTitle("Search by ID");
        setPreferredSize(new Dimension(600, 500));
        setContentPane(panel1);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack(); //sets the panel
        setLocationRelativeTo(null); //makes the app appear at the center of the screen
        setVisible(true);

        ENTERButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AnimalID = Integer.parseInt(textField1.getText()); // takes the input from the textfield
                    FileInputStream fileInputStream = new FileInputStream("animals.ser");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    List<Animal> animals = (List<Animal>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                    StringBuilder builder = new StringBuilder();
                    x = false;
                    for (Animal s : animals){
                        if (s.getId() == (AnimalID)){ //checks if the id that user wrote is in the list "animals"
                            builder.append("ID: ").append(s.getId()).append(",").append("\n").append("NAME: ").append(s.getName()).append(",").append("\n").append("SPECIES: ").append(s.getSpecies()).append(",").append("\n").append("WEIGHT(kg): ").append(s.getWeight()).append(",").append("\n").append("MAX AGE(years): ").append(s.getMaxAge()).append("\n");
                            x = true;
                        }
                    }
                    if (x == false){
                        builder.append("There is no animal with that ID!");
                    }
                    JOptionPane.showMessageDialog(SearchByIdForm.this, builder.toString());
                }
                catch(FileNotFoundException | ClassNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
