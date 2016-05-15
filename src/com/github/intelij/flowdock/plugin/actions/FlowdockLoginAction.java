package com.github.intelij.flowdock.plugin.actions;

import com.github.intelij.flowdock.plugin.forms.FlowLogin;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

public class FlowdockLoginAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        FlowLogin dialog = new FlowLogin();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Flowdock Login");
        dialog.setVisible(true);

        Notification notification = new Notification("Flow", "New message from Anand", "Hello <br/> This is <a>Link</a> message", NotificationType.INFORMATION);
        Notification notification2 = new Notification("Flow", "New message from Jose", "Hello <br/> This is <a>Link</a> message", NotificationType.INFORMATION);
        Notification notification23 = new Notification("Flow", "New message from Jose", "Hello <br/> This is <a>Link</a> message", NotificationType.INFORMATION);

        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        notification.notify(projects[0]);
        notification2.notify(projects[0]);
        notification23.notify(projects[0]);

    }
}
