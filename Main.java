import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProjectDatabase db = new ProjectDatabase("projects.db");
        //main loop starts here
        while (true) {
            System.out.println("Welcome to the Project Manager Application");
            System.out.println("Please make a selection");
            System.out.println("1) Create a Project");
            System.out.println("2) Edit a Project");
            System.out.println("3) View a Project");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear bad input
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.println("Do you want to make a simple project (name only) or full project (all attributes)");
                    System.out.println("1) For Simple Project");
                    System.out.println("2) For Full Project");
                    System.out.print("Choose: ");

                    int type = sc.nextInt();
                    sc.nextLine(); // consume newline

                    ProjectCreator creator = new ProjectCreator(sc);
                    Project p;

                    switch (type) {
                        case 1:
                            p = creator.createProjectSimple();
                            db.saveProject(p);
                            System.out.println("Project saved!");
                            break;
                        case 2:
                            p = creator.createProjectFull();
                            db.saveProject(p);
                            System.out.println("Project saved!");
                            break;
                        default:
                            System.out.println("Invalid selection.");
                            break;
                    }

                    break;
                }


                case 2: {
                    ArrayList<Project> projects = db.loadProjects();
                    if (projects.isEmpty()) {
                        System.out.println("No projects found. Create one first.");
                        break;
                    }

                    // list projects with indexes
                    System.out.println("Projects:");
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println((i + 1) + ") " + projects.get(i).getName());
                    }

                    System.out.print("Which project number do you want to edit? ");
                    int idx;
                    try {
                        idx = sc.nextInt();
                        sc.nextLine(); // consume newline
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                        break;
                    }

                    if (idx < 1 || idx > projects.size()) {
                        System.out.println("That project number does not exist.");
                        break;
                    }

                    Project selected = projects.get(idx - 1);

                    // edit using ProjectEditor
                    System.out.print("Would you like to edit basic project information, add risks, add requirements, or log hours?\n0) Edit Project Info\n1) Add Risks\n2) Add Requirements\n3) Remove Risks\n4) Remove Requirements\n5) Log Risk Hours\n6) Log Requirement Hours\nChoose: ");
                    int editChoice;
                    try {
                        editChoice = sc.nextInt();
                        sc.nextLine(); // consume newline
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                        break;
                    }
                    ProjectEditor editor = new ProjectEditor(sc);

                    switch (editChoice) {
                        case 0 -> editor.editProjectBasics(selected);
                        case 1 -> editor.addRisks(selected);
                        case 2 -> editor.addRequirements(selected);
                        case 3 -> editor.removeRisks(selected);
                        case 4 -> editor.removeRequirements(selected);
                        case 5 -> editor.logRiskHours(selected);
                        case 6 -> editor.logRequirementHours(selected);
                        default -> System.out.println("Invalid input.");
                    }

                    // overwrite DB with updated list
                    db.saveAllProjects(projects);

                    System.out.println("Project updated!");
                    break;
                }


                case 3: {
                    ArrayList<Project> projects = db.loadProjects();
                    if (projects.isEmpty()) {
                        System.out.println("No projects found.");
                        break;
                    }

                    System.out.println("Projects:");
                    for (int i = 0; i < projects.size(); i++) {
                        Project p = projects.get(i);
                        System.out.println();
                        System.out.println("[" + (i + 1) + "]");
                        System.out.println("Name: " + p.getName());
                        System.out.println("Description: " + p.getDescription());
                        System.out.println("Manager: " + p.getManagerName());
                        System.out.println("--Segment Times--");
                        System.out.println("Requirements Analysis: " + p.getSectionTime(0) + " hours");
                        System.out.println("Design: " + p.getSectionTime(1)+ " hours");
                        System.out.println("Coding: " + p.getSectionTime(2)+ " hours");
                        System.out.println("Testing: " + p.getSectionTime(3)+ " hours");
                        System.out.println("Project Management: " + p.getSectionTime(4)+ " hours");
                        System.out.println("Team Members: ");
                        for (String member : p.getTeamMembers()){
                            System.out.println("\t" + member);
                        }
                        System.out.println("Risks: ");
                        for (Risk r : p.getRisks()){
                            System.out.println("\t" + r);
                        }
                        System.out.println("Requirements: ");
                        for (Requirement req : p.getRequirements()){
                            System.out.println("\t" + req);
                        }
                    }
                    System.out.println();
                    break;
                }


                case 0: {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            System.out.println();
        }
    }
}
