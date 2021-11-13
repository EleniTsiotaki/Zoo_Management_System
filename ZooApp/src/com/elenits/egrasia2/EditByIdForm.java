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

public class EditByIdForm extends JFrame{
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JButton ENTERButton;
    private int AnimalID;
    private boolean x;

    public EditByIdForm(){
        setTitle("Edit by ID");
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
                    AnimalID = Integer.parseInt(textField1.getText());
                    FileInputStream fileInputStream = new FileInputStream("animals.ser");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    java.util.List<Animal> animals = (List<Animal>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                    x = false;
                    for (Animal s : animals){
                        if (s.getId() == AnimalID){
                            x = true;
                            Animal animal = new Animal(s.getId(),s.getName(),s.getSpecies(),s.getWeight(),s.getMaxAge()); //check it
                            new Edit(AnimalID, animal); //edit the animal (takes you to another form)
                        }
                    }

                    if (x == false){
                        StringBuilder builder = new StringBuilder();
                        builder.append("There is no animal with that ID!");
                        JOptionPane.showMessageDialog(EditByIdForm.this, builder.toString());
                    }
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
