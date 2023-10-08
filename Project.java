public class Project {
    private String projectName;
    private String reportingFrequency;
    private String timeDeadline;
    private int managerId;
    private List<User> teamMembers;

    public Project(String projectName, String reportingFrequency, String timeDeadline, int managerId) {
        this.projectName = projectName;
        this.reportingFrequency = reportingFrequency;
        this.timeDeadline = timeDeadline;
        this.managerId = managerId;
        this.teamMembers = new ArrayList<>();
    }

    // Getters and Setters
    // ...

    public void addTeamMember(User user) {
        teamMembers.add(user);
    }

    public void customizeReport(String reportDesign) {
        // Implement logic to change the report design.
    }

    // You can add other methods or functionality as needed.
}
