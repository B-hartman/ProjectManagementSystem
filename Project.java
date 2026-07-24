import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String managerName;
    private ArrayList<String> teamMembers;
    private ArrayList<Risk> risks;
    private ArrayList<Requirement> requirements;
    private double hoursAnalysis;
    private double hoursDesign;
    private double hoursCoding;
    private double hoursTesting;
    private double hoursManagement;

    /**
     * Full Constructor with all parameters for class variables.
     * @param name The name of the project. Does not need to be unique, as the descriptions can help differentiate the projects
     * @param description A short project description for labeling purposes
     * @param managerName The name of the project manager if there is one, can be empty
     * @param teamMembers A list of all the team members on the project
     */
    public Project(String name, String description, String managerName, ArrayList<String> teamMembers, ArrayList<Risk> risks, ArrayList<Requirement> requirements){
        this.name = name;
        this.description = description;
        this.managerName = managerName;
        this.teamMembers = teamMembers;
        this.risks = risks;
        this.requirements = requirements;
        hoursAnalysis = 0;
        hoursDesign = 0;
        hoursCoding = 0;
        hoursTesting = 0;
        hoursManagement = 0;
    } 
     
    public Project(String name, String description, String managerName, ArrayList<String> teamMembers){
        this.name = name;
        this.description = description;
        this.managerName = managerName;
        this.teamMembers = teamMembers;
        this.risks = new ArrayList<Risk>();
        this.requirements = new ArrayList<Requirement>();
        hoursAnalysis = 0;
        hoursDesign = 0;
        hoursCoding = 0;
        hoursTesting = 0;
        hoursManagement = 0;
    }

    public Project(String name, String description, String managerName){
        this.name = name;
        this.description = description;
        this.managerName = managerName;
        this.teamMembers = new ArrayList<String>();
        this.risks = new ArrayList<Risk>();
        this.requirements = new ArrayList<Requirement>();
        hoursAnalysis = 0;
        hoursDesign = 0;
        hoursCoding = 0;
        hoursTesting = 0;
        hoursManagement = 0;
    }

    public Project(String name, String description){
        this.name = name;
        this.description = description;
        managerName = "No Manager";
        this.teamMembers = new ArrayList<String>();
        this.risks = new ArrayList<Risk>();
        this.requirements = new ArrayList<Requirement>();
        hoursAnalysis = 0;
        hoursDesign = 0;
        hoursCoding = 0;
        hoursTesting = 0;
        hoursManagement = 0;
    }

    /**
     * Most simple constructor with only a name given. Sets default values for other class variables and ensures the Arraylists are instantiated and not null
     * @param name Only the project name is used for creation.
     */
    public Project(String name){
        this.name = name;
        description = "No Description";
        managerName = "No Manager";
        this.teamMembers = new ArrayList<String>();
        this.risks = new ArrayList<Risk>();
        this.requirements = new ArrayList<Requirement>();
        hoursAnalysis = 0;
        hoursDesign = 0;
        hoursCoding = 0;
        hoursTesting = 0;
        hoursManagement = 0;
    }

    /*
     * Getter and Setter Methods. Arraylists only have getters here as the next section has the add and remove methods
     */

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public double getSectionTime(int segment) {
        return switch (segment) {
            case 0 -> hoursAnalysis;
            case 1 -> hoursDesign;
            case 2 -> hoursCoding;
            case 3 -> hoursTesting;
            case 4 -> hoursManagement;
            default -> 0;
        };
    }
    public void updateSectionTime(int segment, double hours) {
        switch (segment) {
            case 0 -> hoursAnalysis+=hours;
            case 1 -> hoursDesign+=hours;
            case 2 -> hoursCoding+=hours;
            case 3 -> hoursTesting+=hours;
            case 4 -> hoursManagement+=hours;
        }
    }

    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }
    public ArrayList<Risk> getRisks() {
        return risks;
    }
    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    /*
     * Add and Remove Methods for Arraylists. The lookup and selection for object removal should be done through the console menu before calling these methods
     */

    public void addTeamMember(String teamMemberName){
        teamMembers.add(teamMemberName);
    }
    public void removeTeamMember(String teamMemberName){
        teamMembers.remove(teamMemberName);
    }
    public void addRisk(Risk newRisk){
        risks.add(newRisk);
    }
    public void removeRisk(Risk deleteRisk){
        risks.remove(deleteRisk);
    }
    public void addRequirement(Requirement newReq){
        requirements.add(newReq);
    }
    public void removeRequirement(Requirement deleteReq){
        requirements.remove(deleteReq);
    }

}
