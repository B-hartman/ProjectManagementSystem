import java.io.*;
import java.util.ArrayList;

public class ProjectDatabase {
    private final File dbFile;

    public ProjectDatabase(String filename) {
        this.dbFile = new File(filename);
    }

    public void saveProject(Project project) {
        ArrayList<Project> projects = loadProjects();
        projects.add(project);
        writeProjects(projects);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Project> loadProjects() {
        if (!dbFile.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            Object obj = ois.readObject();
            return (ArrayList<Project>) obj;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void writeProjects(ArrayList<Project> projects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(projects);
        } catch (IOException e) {
            System.out.println("Failed to save projects: " + e.getMessage());
        }
    }

    public void saveAllProjects(ArrayList<Project> projects) {
        writeProjects(projects);
    }

}
