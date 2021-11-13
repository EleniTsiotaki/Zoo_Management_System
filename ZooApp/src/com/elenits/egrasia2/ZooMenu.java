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

public class ZooMenu extends JFrame {
    private JPanel panel1;
    private JButton seeAllAnimalsButton;
    private JButton addANewAnimalButton;
    private JButton searchAnimalByNameButton;
    private JButton searchAnimalByIdButton;
    private JButton deleteAnimalById;
    private JButton editAnimalById;
    private JButton exitButton;
    private JLabel chooseAnOptionLabel;

    public ZooMenu() {
        setTitle("Zoo Menu");
        setPreferredSize(new Dimension(600, 500));
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); //sets the panel
        setLocationRelativeTo(null); //makes the app appear at the center of the screen
        setVisible(true);
        seeAllAnimalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("animals.ser"); //reads file objects
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    java.util.List<Animal> animals = (List<Animal>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                    StringBuilder builder = new StringBuilder();
                    if (animals.size() != 0) {
                        for (Animal s : animals) {
                            builder.append(" ID: ").append(s.getId()).append(" | ").append(" NAME: ").append(s.getName()).append(" | ").append(" SPECIES: ").append(s.getSpecies()).append(" | ").append(" WEIGHT(kg): ").append(s.getWeight()).append(" | ").append(" MAX AGE(years): ").append(s.getMaxAge()).append(" | ").append("\n");
                        }
                        JOptionPane.showMessageDialog(ZooMenu.this, builder.toString()); //appears an extra window
                    }
                    else{
                        builder.append("There are no animals saved!");
                        JOptionPane.showMessageDialog(ZooMenu.this, builder.toString()); //appears an extra window
                    }
                } catch (FileNotFoundException | ClassNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        addANewAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddForm(); // go to AddForm
            }
        });
        searchAnimalByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new SearchByNameForm(); } //searches an animal by entering its name
        });
        searchAnimalByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchByIdForm();
            } //searches an animal by entering its id
        });
        editAnimalById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditByIdForm();
            } //edits an animal by entering its id
        });
        deleteAnimalById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteById();
            } //deletes an animal by entering its id
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //exits completely
            }
        });
    }
}

