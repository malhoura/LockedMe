package com.malhoura;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  static int menuItem, subMenuItem;
  static String searchedFile;

  public static void displayMainMenu() {
    System.out.println(""
        + "1) Display Files"
        + "\n2) Search/Add/Delete Files"
        + "\n3) Close");
  }

  public static void displaySubMenu() {
    System.out.println(""
        + "1) Search file name"
        + "\n2) Add file"
        + "\n3) Delete file"
        + "\n4) Main menu");
  }

  public static File createDirectoryIfNotExist(String directoryName){
    File directory = new File(directoryName);
    boolean success = false;
    if (directory.exists()) {
      System.out.println("Directory already exists ...");
    } else {
      System.out.println("Directory not exists, creating now");
      success = directory.mkdir();
      if (success) {
        System.out.printf("Successfully created new directory : %s%n", directory);
      } else {
        System.out.printf("Failed to create new directory: %s%n", directory);
      }
    }
    return directory;
  }

  public static void createfile() throws IOException {
    Scanner reader = new Scanner(System.in);
    boolean success = false;
    System.out.println(
        "Enter path of directory to create. Enter nothing if you don't wish to create a new directory.");
    String dirName = reader.nextLine();
    // Creating new directory in Java, if it doesn't exists
    if (!dirName.isEmpty()) {
      createDirectoryIfNotExist(dirName);
    }

    // Creating new file in Java, only if not exists
    System.out.println("Enter file name to be created ");
    String filename = reader.nextLine();

    //create file in specific directory
    System.out.println("Which Directory would you like to create this file in");
    String targetDir = reader.nextLine();
    createDirectoryIfNotExist(targetDir);

    File f = new File(targetDir, filename);
    if (f.exists()) {
      System.out.println("File already exists");
    } else {
      System.out.println("No such file exists, creating now");
      success = f.createNewFile();
      if (success) {
        System.out.printf("Successfully created new file: %s%n", f);
      } else {
        System.out.printf("Failed to create new file: %s%n", f);
      }
    }
  }

  public static void searchFile() {
    System.out.print("Enter file name"
        + "\n");
    Scanner reader = new Scanner(System.in);
    String fileName = reader.nextLine();
    File fileToSearch = getFile(fileName);
    System.out.println("File found: " + fileToSearch.getName());
  }

  public static File getFile(String fileName) {
    File root = new File(".");
    try {
      File[] files = root.listFiles();
      for (File f : files) {
        if (f.getName().startsWith("product")) {
          for (File childFile : f.listFiles()) {
            if (childFile.getName().equalsIgnoreCase(fileName)) {
              return childFile;
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void deleteFile() {
    System.out.print("Enter file name to delete"
        + "\n");
    Scanner reader = new Scanner(System.in);
    String fileName = reader.nextLine();
    File fileToDelete = getFile(fileName);
    fileToDelete.delete();
    System.out.println("Deleted File: " + fileToDelete.getName());
  }

  public static void listFiles() {
    File directory = new File(".");
    // Get all files from a directory.
    File[] fList = directory.listFiles();
    for (File f : fList) {
      if (f.getName().startsWith("product")) {
        System.out.println("Directory Name => " + f.getName());
        for (File childFile : f.listFiles()) {
          System.out.println("File => " + childFile.getName());
        }
      }
    }

  }


  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to LockedMe");
    System.out.println("Developer: Mazen Al Hourani");
    final Scanner sc = new Scanner(System.in);

    boolean proceedMainMenu = true;
    while (proceedMainMenu) {
      System.out.println("What would you like to do:");
      displayMainMenu();
      menuItem = sc.nextInt();
      switch (menuItem) {
        case 1:
          //display all file
          listFiles();
          break;
        case 2:
          displaySubMenu();
          subMenuItem = sc.nextInt();
          switch (subMenuItem) {
            case 1:
              //search file name
              searchFile();
              break;
            case 2:
              //add file
              createfile();
              break;
            case 3:
              //delete file
              deleteFile();
              break;
            case 4:
              //back to main menu
              break;
          }
          break;
        case 3:
          proceedMainMenu = false;
          break;
      }
    }
  }
}
