import javax.swing.*;

public class ProjectPulseMain {
    public static void main(String[] args) {
        ProjectPulseApp projectPulseApp = new ProjectPulseApp();

        // windows settings
        JFrame frame = new JFrame("Project Pulse Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080); // Anpassa storleken efter behov
        frame.add(projectPulseApp); // Lägg till din huvudapplikationskomponent

        // draw window
        frame.setVisible(true);
    }



    
}
