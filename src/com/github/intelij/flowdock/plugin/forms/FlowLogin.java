package com.github.intelij.flowdock.plugin.forms;

import javax.swing.*;
import java.awt.event.*;

public class FlowLogin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lbluser;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private static String flowdockUser;
    private static String flowdockPass;

    public FlowLogin() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        flowdockUser = usernameField.getText();
        flowdockPass = String.valueOf(passwordField.getPassword());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static String getFlowdockUser() {
        return flowdockUser;
    }

    public static String getFlowdockPass() {
        return flowdockPass;
    }

    public static void main(String[] args) {
        FlowLogin dialog = new FlowLogin();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
