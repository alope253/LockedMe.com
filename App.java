package LockedMe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class App 
{

    final static String FOLDER = "C:\\LockedmeFiles";

    static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) throws InvalidPathException, IOException
    {
        showWelcomeScreen();
        showMainMenu();

    }
    
    private static void showWelcomeScreen() {
    	// This is the welcome screen the user will see
    	
        System.out.println("===========================");
        System.out.println("Welcome to LockedMe.com");
        System.out.println("By: Alfredo Lopez");
        System.out.println("===========================\n");
    }

    private static void showMainMenu() throws InvalidPathException, IOException {
    	// Main Menu is displayed here with the 3 possible selections
    	
        System.out.println("======== MAIN MENU ========");
        System.out.println("1.) Show files in ascending order");
        System.out.println("2.) Perform file operations");
        System.out.println("3.) Close the application");
        System.out.println("===========================\n");
        collectMainMenuOption();
    }
 
    private static void collectMainMenuOption() throws InvalidPathException, IOException {
        // This is where the user selects which of the 3 possible options they can select
    	
    	System.out.println("Select 1, 2 or 3:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                showFilesInAscendingOrder();
                break;
            case "2":
                showFileOperations();
            case "3":
                System.out.println("Thank you for using LockedMe.com");
                System.out.println("Closing application...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input provided. Select 1, 2 or 3.");
        }
        showMainMenu();
    }
    
    private static void showFilesInAscendingOrder() {
        System.out.println("------------------");
        System.out.println("Showing files in ascending order:\n");
        File[] files = new File(FOLDER).listFiles();
        Set<String> sorted = new TreeSet<>();
        for (File file: files) {
            if (!file.isFile()) {
                continue;
            }
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("------------------");
    }

    private static void showFileOperations() throws InvalidPathException, IOException {
        // This is the file operations menu that the user will see after selecting option 2
    	
    	System.out.println("--------------");
        System.out.println("1.) Add a file");
        System.out.println("2.) Delete a file");
        System.out.println("3.) Search for a file");
        System.out.println("4.) Back to main menu");
        System.out.println("--------------");
        collectFileOperation();
    }
    
    private static void collectFileOperation() throws InvalidPathException, IOException {
        System.out.println("Please choose 1, 2, 3 or 4:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                addAFile();
                break;
            case "2":
            	deleteAFile();
                break;
            case "3":
            	searchForAFile();
                break;
            case "4":
            	returnToMainMenu();
                break;
        }
        showFileOperations();
    }

    private static void addAFile() throws InvalidPathException {
        // This allows the user to provide a file path that will add a new file to directory
    	
    	System.out.println("Please provide a file path:");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File does not exist");
            return;
        }

        String newFilePath = FOLDER + "/" + path.getFileName();
        int inc = 0;
        while (Files.exists(Paths.get(newFilePath))) {
            inc++;
            newFilePath = FOLDER + "/" + inc + "_" + path.getFileName();
        }
        System.out.println("File added successfully");
        try {
            Files.copy(path, Paths.get(newFilePath));
        } catch(IOException e) {
            System.out.println("Unable to copy file to " + newFilePath);
        }

    }
    
    private static void deleteAFile() throws InvalidPathException, IOException {
    	// This is where the user deletes a file in the directory
    	
    	System.out.println("Please provide a file name to delete:");
        String fileName = scanner.nextLine();
        String fileDelete = FOLDER + "/" + fileName;
        Path deleteFile = Paths.get(fileDelete);
        
        boolean isDeleted = Files.deleteIfExists(deleteFile);
        if(isDeleted) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("File was not found");
        }
    }
    
    private static void searchForAFile() throws InvalidPathException, IOException {
    	//This is where the user searches for a file in the directory
    	
    	System.out.println("Enter file you want to search:");
        String fileName = scanner.nextLine();
        String fileToSearch = FOLDER + "/" + fileName;
        Path searchFile = Paths.get(fileToSearch);
        
        if (!Files.exists(searchFile)) {
            System.out.println("File was not found!");
        } else {
        	System.out.println("File was found");
        }
        
    }
    
    private static void returnToMainMenu() throws InvalidPathException, IOException {
    	////This is where the user can return to the main menu
    	
    	System.out.println("Returning to main menu...\n");
    	showMainMenu();
    	
    }


}