import java.io.Serializable;

public class Requirement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reqName;
    private String reqDescription;
    private boolean isFunctional;
    private boolean reqCompletedStatus;
    private double reqHours;

    /**
     * Overloaded constructors again as there might be cases where no description is given. Always starts as not completed and with no hours inputted.
     * @param name The name of the requirement
     * @param description a description of the requirement
     * @param isFunctional whether the requirement is functional or nonfunctional
     */
    public Requirement(String name, String description, boolean isFunctional){
        reqName = name;
        reqDescription = description;
        this.isFunctional = isFunctional;
        reqCompletedStatus = false;
        reqHours = 0.0;
    }

    public Requirement(String name, boolean isFunctional){
        reqName = name;
        reqDescription = "No description";
        this.isFunctional = isFunctional;
        reqCompletedStatus = false;
        reqHours = 0.0;
    }

    /*
     * Getters and Setters
     */

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public boolean isReqCompletedStatus() {
        return reqCompletedStatus;
    }

    public void setReqCompletedStatus(boolean reqCompletedStatus) {
        this.reqCompletedStatus = reqCompletedStatus;
    }

    public double getReqHours() {
        return reqHours;
    }

    public void setReqHours(double reqHours) {
        this.reqHours = reqHours;
    }
    /**
     * Method to increment time spent on
     * @param increment
     */
    public void updateReqHours(double increment) {
        this.reqHours += increment;
    }

    /**
     * Simple toString to display class information
     * @return a string containing the name, description, whether its functional, hours logged, and completion status
     */
    public String toString(){
        if(isFunctional){
            return (reqName + ": " + reqDescription + ", (Functional), Hours: " + reqHours + ", Completed: " + reqCompletedStatus);
        }
        else {
            return (reqName + ": " + reqDescription + ", (Non-Functional), Hours: " + reqHours + ", Completed: " + reqCompletedStatus);
        }
    }
}
