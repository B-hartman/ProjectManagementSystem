import java.io.Serializable;

public class Risk implements Serializable {
    private static final long serialVersionUID = 1L;


    private String riskName;
    private String riskDescription;
    private boolean riskCompletedStatus;
    private double riskHours;

    /**
     * Overloaded constructors to make with or without descriptions. Alternatively this responsibility of creating a default description input could be placed in the user input handling
     * @param name the name of the risk
     * @param description a short description of the risk
     */
    public Risk(String name, String description){
        riskName = name;
        riskDescription = description;
        riskCompletedStatus = false;
        riskHours = 0.0;
    }

    public Risk(String name){
        riskName = name;
        riskDescription = "No Description";
        riskCompletedStatus = false;
        riskHours = 0.0;
    }

    /*
     * Getters and Setters
     */

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public double getRiskHours() {
        return riskHours;
    }

    public boolean isRiskStatus() {
        return riskCompletedStatus;
    }

    public void setRiskStatus(boolean riskCompletedStatus) {
        this.riskCompletedStatus = riskCompletedStatus;
    }

    public void setRiskHours(double riskHours) {
        this.riskHours = riskHours;
    }

    /**
     * Method to increment time spent on this risk
     * @param increment the value to add to the current hours value
     */
    public void updateRiskHours(double increment) {
        this.riskHours += increment;
    }

    /**
     * Simple toString to display class information
     * @return a string containing the name, description, hours logged, and completion status
     */
    public String toString(){
        return (riskName + ": " + riskDescription + ", Hours: " + riskHours + ", Completed: " + riskCompletedStatus);
    }

}
