import java.util.Scanner;

public class ProjectEditor {

    private final Scanner sc;

    public ProjectEditor(Scanner sc) {
        this.sc = sc;
    }

    // Edits basic fields; pressing Enter keeps the current value
    public void editProjectBasics(Project p) {
        System.out.println("Editing project: " + p.getName());

        System.out.print("New name (Enter to keep '" + p.getName() + "'): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            p.setName(newName);
        }

        System.out.print("New description (Enter to keep current): ");
        String newDesc = sc.nextLine().trim();
        if (!newDesc.isEmpty()) {
            p.setDescription(newDesc);
        }

        System.out.print("New manager name (Enter to keep '" + p.getManagerName() + "'): ");
        String newManager = sc.nextLine().trim();
        if (!newManager.isEmpty()) {
            p.setManagerName(newManager);
        }

        /* create array lists editor for the risks and requirements and store them in the new project return
        statement below. I'd recommend using the team members arraylist  in ProjectCreator.java above as a blueprint to accomplish that

        */

        System.out.println("Done editing.");
    }

    public void addRisks(Project p){
        boolean adding = true;
        int repeat = -1;
        String riskName;
        String riskDescription;
        while(adding){
            System.out.println("Choose a name for this risk: ");
            riskName = sc.nextLine().trim();

            System.out.println("Write a description for this risk: ");
            riskDescription = sc.nextLine();

            Risk r = new Risk(riskName,riskDescription);
            p.addRisk(r);
            System.out.println("Risk added. Would you like to add another? \n0) Continue\n1) Return to Main Menu");
            while(repeat != 1 && repeat != 0) {
                try {
                    repeat = sc.nextInt();
                    sc.nextLine(); // consume newline
                } catch (Exception e) {
                    sc.nextLine(); // clear bad input
                }
                System.out.println("Please enter a valid number.");
            }
            switch(repeat) {
                case 0 -> adding = true;
                case 1 -> adding = false;
            }
        }
    }


    public void addRequirements(Project p){
        boolean adding = true;
        int repeat = -1;
        String reqName;
        String reqDescription;
        int boolInput = -1;
        boolean isFunct = false;
        while(adding){
            System.out.println("Choose a name for this requirement: ");
            reqName = sc.nextLine().trim();

            System.out.println("Write a description for this requirement: ");
            reqDescription = sc.nextLine();

            while(boolInput != 1 && boolInput != 0) {
                System.out.println("Is this requirement a functional requirement? \n0) Functional\n1) Nonfunctional");
                try {
                    boolInput = sc.nextInt();
                    sc.nextLine(); // consume newline
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                    sc.nextLine(); // clear bad input
                }
            }
            switch(boolInput) {
                case 0 -> isFunct = true;
                case 1 -> isFunct = false;
            }
            Requirement r = new Requirement(reqName,reqDescription,isFunct);
            p.addRequirement(r);
            System.out.println("Requirement added. Would you like to add another? \n0) Continue\n1) Return to Main Menu");
            while(repeat != 1 && repeat != 0) {
                try {
                    repeat = sc.nextInt();
                    sc.nextLine(); // consume newline
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                    sc.nextLine(); // clear bad input
                }
            }
            switch(repeat) {
                case 0 -> adding = true;
                case 1 -> adding = false;
            }
        }
    }

    public void removeRisks(Project p){
        if(p.getRisks().isEmpty()){
            System.out.println("No Risks to remove.");
            return;
        }
        System.out.println("Which Risk should be removed? ");
        int input = -1;
        int counter = 0;
        for (Risk r : p.getRisks()){
            System.out.println(counter + ") " + r);
            counter++;
        }
        while(input == -1 || input > p.getRisks().size()) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        p.removeRisk(p.getRisks().get(input));
        System.out.println("Risk successfully removed.");
    }

    public void removeRequirements(Project p){
        if(p.getRequirements().isEmpty()){
            System.out.println("No Requirements to remove.");
            return;
        }
        System.out.println("Which Requirement should be removed? ");
        int input = -1;
        int counter = 0;
        for (Requirement r : p.getRequirements()){
            System.out.println(counter + ") " + r);
            counter++;
        }
        while(input == -1 || input > p.getRequirements().size()) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        p.removeRequirement(p.getRequirements().get(input));
        System.out.println("Risk successfully removed.");
    }


    public void logRiskHours(Project p){
        if(p.getRisks().isEmpty()){
            System.out.println("No risks added to project.");
            return;
        }

        System.out.println("Logging Hours for project: " + p.getName());

        System.out.println("Choose which risk you would like to log hours for: ");
        for(int i = 0; i < p.getRisks().size(); i++){
            System.out.println(i + ") " + p.getRisks().get(i).getRiskName() + " " + p.getRisks().get(i).getRiskDescription());
        }
        int riskIndex = -1;
        while(riskIndex == -1|| riskIndex>p.getRequirements().size()) {
            try {
                riskIndex = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                sc.nextLine(); // clear bad input
            }
            System.out.println("Please enter a valid number.");
        }

        Risk riskChoice = p.getRisks().get(riskIndex);

        System.out.print("How many hours would you like to log for this risk? ");
        double riskHours = -1.0;
        while(riskHours <= 0) {
            try {
                riskHours = sc.nextDouble();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }

        riskChoice.updateRiskHours(riskHours);

        System.out.println("Choose what section this risk has impacted: \n0) Analysis\n1) Design\n2) Coding\n3) Testing\n4) Management");
        int section = -1;
        while(section == -1 || section > 4) {
            try {
                section = sc.nextInt();
                sc.nextLine(); // consume newline
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        p.updateSectionTime(section,riskHours);

        System.out.println("Has this risk been completed?: \n0) Yes\n1) No");
        int completed = -1;
        while(completed != 1 && completed != 0) {
            try {
                completed = sc.nextInt();
                sc.nextLine(); // consume newline
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        switch(completed){
            case 0 -> riskChoice.setRiskStatus(true);
            case 1 -> riskChoice.setRiskStatus(false);
        }


        System.out.println("Done logging hours.");
    }


    public void logRequirementHours(Project p){
        if(p.getRequirements().isEmpty()){
            System.out.println("No requirements added to project.");
            return;
        }

        System.out.println("Logging Hours for project: " + p.getName());

        System.out.println("Choose which requirement you would like to log hours for: ");
        for(int i = 0; i < p.getRequirements().size(); i++){
            System.out.println(i + ") " + p.getRequirements().get(i).getReqName()+ " Functional:(" + p.getRequirements().get(i).isFunctional() + ") " + p.getRequirements().get(i).getReqDescription());
        }
        int reqIndex = -1;
        while(reqIndex == -1 || reqIndex>p.getRequirements().size()) {
            try {
                reqIndex = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }

        Requirement requirementChoice = p.getRequirements().get(reqIndex);

        System.out.print("How many hours would you like to log for this requirement? ");
        double reqHours = -1.0;
        while(reqHours <= 0) {
            try {
                reqHours = sc.nextDouble();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }

        requirementChoice.updateReqHours(reqHours);

        System.out.println("Choose what section this risk has impacted: \n0) Analysis\n1) Design\n2) Coding\n3) Testing\n4) Management");
        int section = -1;
        while(section < 0 || section > 4) {
            try {
                section = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        p.updateSectionTime(section,reqHours);
        System.out.println("Has this requirement been completed?: \n0) Yes\n1) No");
        int completed = -1;
        while(completed != 1 && completed != 0) {
            try {
                completed = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
            }
        }
        switch(completed){
            case 0 -> requirementChoice.setReqCompletedStatus(true);
            case 1 -> requirementChoice.setReqCompletedStatus(false);
        }


        System.out.println("Done logging hours.");
    }

}
