package com.github.intelij.flowdock.plugin.forms;

import com.github.intelij.flowdock.plugin.api.FlowdockAPI;
import com.github.intelij.flowdock.plugin.util.ApplicationConstants;
import com.intellij.ide.util.PropertiesComponent;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class FlowLogin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lbluser;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel msgLabel;

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
        buttonOK.setVisible(false);
        msgLabel.setVisible(false);
        PropertiesComponent.getInstance().setValue(ApplicationConstants.FLOWDOCK_API_USERNAME, usernameField.getText());
        PropertiesComponent.getInstance().setValue(ApplicationConstants.FLOWDOCK_API_PASSWORD, String.valueOf(passwordField.getPassword()));
        boolean credentialsOK = false;
        try {
            credentialsOK = FlowdockAPI.checkCredentials();
        } catch (IOException e) {
            msgLabel.setText("Network Error");
            msgLabel.setVisible(true);
        }
        if (!credentialsOK) {
            msgLabel.setText("Invalid credentials");
            msgLabel.setVisible(true);
        }
        if (credentialsOK)
            dispose();
        buttonOK.setVisible(true);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        FlowLogin dialog = new FlowLogin();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
