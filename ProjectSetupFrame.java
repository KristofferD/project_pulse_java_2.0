import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectSetupFrame extends JFrame {
    private JTextField txtProjectName;
    private JComboBox<String> cboReportingFrequency;
    private JTextField txtTimeDeadline;
    private JButton btnCreateProject;

    public ProjectSetupFrame() {
        setTitle("Project Setup");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Project Name:"));
        txtProjectName = new JTextField();
        panel.add(txtProjectName);

        panel.add(new JLabel("Reporting Frequency:"));
        cboReportingFrequency = new JComboBox<>(new String[]{"Daily", "Weekly", "Fortnightly", "Monthly"});
        panel.add(cboReportingFrequency);

        panel.add(new JLabel("Time Deadline:"));
        txtTimeDeadline = new JTextField();
        panel.add(txtTimeDeadline);

        btnCreateProject = new JButton("Create Project");
        panel.add(btnCreateProject);

        btnCreateProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hämta och använd inmatade projektuppgifter
                String projectName = txtProjectName.getText();
                String reportingFrequency = (String) cboReportingFrequency.getSelectedItem();
                String timeDeadline = txtTimeDeadline.getText();

                // Skapa ett Project-objekt och spara det i databasen
                Project project = new Project(projectName, reportingFrequency, timeDeadline);
                DatabaseHandler.saveProject(project);

                // Återställ textfälten efter projektuppsättning
                txtProjectName.setText("");
                txtTimeDeadline.setText("");
            }
        });

        add(panel);
    }
}
