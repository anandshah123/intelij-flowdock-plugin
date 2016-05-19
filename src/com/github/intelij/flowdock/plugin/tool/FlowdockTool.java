package com.github.intelij.flowdock.plugin.tool;

import com.github.intelij.flowdock.plugin.api.FlowdockAPI;
import com.github.intelij.flowdock.plugin.forms.FlowLogin;
import com.github.intelij.flowdock.plugin.util.ApplicationConstants;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;

public class FlowdockTool implements ToolWindowFactory {


    private JLabel lblSearch;
    private JComboBox comboSearch;
    private JPanel formPanel;
    private JTextArea textArea1;
    private JButton sendButton;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        String username = PropertiesComponent.getInstance().getValue(ApplicationConstants.FLOWDOCK_API_USERNAME);
        String password = PropertiesComponent.getInstance().getValue(ApplicationConstants.FLOWDOCK_API_PASSWORD);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || !isCredentialsOK()) {
            FlowLogin dialog = new FlowLogin();
            dialog.setLocationRelativeTo(null);
            dialog.setTitle("Flowdock Login");
            dialog.pack();
            dialog.setVisible(true);
        }

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(formPanel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private boolean isCredentialsOK() {
        boolean credentialsOK = false;
        try {
            credentialsOK = FlowdockAPI.checkCredentials();
        } catch (IOException e) {
            // Ignore any network error here.
        }
        return credentialsOK;
    }

}
