public class ProjectManager {
    private List<Project> projects;

    // Konstruktor och getter/setter-metoder här.

    public void createProject(String projectName, String reportingFrequency, String timeDeadline, int managerId) {
        // Skapa ett nytt projekt och lägg till det i listan av projekt.
        Project project = new Project(projectName, reportingFrequency, timeDeadline, managerId);
        projects.add(project);
    }

    public void customizeReportDesign(Project project, String reportDesign) {
        // Anpassa rapportdesignen för ett specifikt projekt.
        project.customizeReport(reportDesign);
    }

    public void assignTeamMember(Project project, User user) {
        // Tilldela en teammedlem till ett projekt.
        project.addTeamMember(user);
    }

    // Andra användbara metoder för projektuppsättning och konfiguration kan också implementeras här.
}
