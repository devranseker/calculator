/*
 *   Author: Haci Onur Sogut
 *   Date: 27.04.2023
 *   Description: A simple calculator using Java Swing
 */

package MyCalculator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Calculator {
    public static void main(String[] args) {
        createAndShowGUI();
    }

    public static void createAndShowGUI() {

        //Colors I used in the calculator as variables to use easily
        Color lavender = Color.decode("#b57edc");
        Color lightBlue = Color.decode("#1560bd");
        Color lightPink = Color.decode("#eec4c9");
        //----------------------------------------------------------

        //Frame and its initial settings like size
        JFrame frame = new JFrame("One Little Calculator");
        frame.setPreferredSize(new Dimension(300,300));
        frame.setResizable(false);
        //------------------------------------------

        //JMenu at the top of my frame, for functionality I used my own ActionListener
        JMenuActionListener jMenuActionListener = new JMenuActionListener(frame);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,1,0,lightBlue));
        menuBar.setBackground(lavender);

        JMenu settingsMenu = new JMenu("Settings");
        settingsMenu.setFont(new Font("Arial",Font.BOLD,12));
        settingsMenu.setBorder(BorderFactory.createEmptyBorder(3,0,1,0));
        settingsMenu.setFocusable(false);
        settingsMenu.setForeground(lightBlue);

        JMenuItem resizable = new JMenuItem("Resizable");
        JMenuItem exit = new JMenuItem("Exit");
        //----------------------------------------------------------------------------

        //Ever items settings is written in a for loop to avoid repetitive commands
        JMenuItem [] menuItems = {resizable,exit};

        for (JMenuItem menuitem:menuItems) {
            menuitem.setBorder(null);
            menuitem.setForeground(lightBlue);
            menuitem.setOpaque(true);
            menuitem.setBackground(lavender);
            menuitem.addActionListener(jMenuActionListener);
            menuitem.setBorder(BorderFactory.createMatteBorder(1,0,1,0,lightBlue));
        }

        settingsMenu.add(resizable);
        //settingsMenu.addSeparator();
        settingsMenu.add(exit);

        menuBar.add(settingsMenu);
        //--------------------------------------------------------------------------

        //This is the place where numbers occur on the screen and its' visual settings
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(50,50));
        textField.setEditable(false);
        textField.setFocusable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 18));
        textField.setBackground(lightPink);
        textField.setForeground(lightBlue);
        Border innerTextFieldBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, lightBlue);
        Border outerTextFieldBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, lavender);
        textField.setBorder(BorderFactory.createCompoundBorder(outerTextFieldBorder,innerTextFieldBorder));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        //--------------------------------------------------------------------------

        MyActionListener actionListener = new MyActionListener(textField);

        //This frame and action listener is for the keyboard to type numbers
        frame.addKeyListener(actionListener);
        //------------------------------------------------------------------

        //Creating 4x4 panel and buttons (with gap between buttons)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));

        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        ((GridLayout) panel.getLayout()).setHgap(5);
        ((GridLayout) panel.getLayout()).setVgap(5);
        panel.setBackground(lavender);

        JButton[] buttons = {
                new JButton("1"),new JButton("2"),new JButton("3"),new JButton("+"),
                new JButton("4"),new JButton("5"),new JButton("6"),new JButton("-"),
                new JButton("7"),new JButton("8"),new JButton("9"),new JButton("*"),
                new JButton("0"),new JButton("="),new JButton("C"),new JButton("/")};

        for (int i = 1; i<=16; i++) {
            buttons[i-1].setFont(new Font("Arial",Font.BOLD,16));
            buttons[i-1].setFocusable(false);
            panel.add(buttons[i-1]);
            buttons[i-1].addActionListener(actionListener);
            buttons[i-1].setBorder(BorderFactory.createLineBorder(lightBlue,1,false));
            buttons[i-1].setForeground(lightBlue);
            buttons[i-1].setBackground(lightPink);
            if (i%4==0)
                buttons[i-1].setBackground(Color.decode("#e1a4ab"));
        }
        //------------------------------------------------------------------------------

        //Adding all elements together to frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //-------------------------------------
    }
}
