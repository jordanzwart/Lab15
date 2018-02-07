import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Countires {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String directoryString = "Country";
		createDirectory(directoryString);
		createFile(directoryString, "Countires.txt");
		//writeToFile(directoryString, "Countires.txt");
		
		int noYes = 1;
		while(noYes == 1){
			System.out.println("Welcome to the Countries Maintenance App!");
			System.out.println("Select from below");
			System.out.println("1. See lists of Countires");
			System.out.println("2. Add a country");
			System.out.println("3. Exit\n");
	
			int num = scan.nextInt();
			switch (num) {
			case 1:
				readFromFile(directoryString, "Countires.txt");
				System.out.println("\n");
				break;
			case 2:
				writeToFile(directoryString, "Countires.txt");
				readFromFile(directoryString, "Countires.txt");
				System.out.println("This Country has been saved!\n");
				break;
			case 3:
				System.out.println("You sure? 1(No) 2(Yes)");
				noYes = scan.nextInt();
				break;
			}
			
			
		} 
		System.out.println("Goodbye");

	}
	public static void createDirectory(String dirString) {
		Path dirPath = Paths.get(dirString);

		if (Files.notExists(dirPath)) {
			try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Im not sure what happened. Contact customer service.");
			}
		}
	}

	
	public static void createFile(String dirString, String fileString) {
		Path filePath = Paths.get(dirString, fileString);

		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("Your file was created successfully");
			} catch (IOException e) {
				System.out.println("Somthing went wrong with file creation");
				e.printStackTrace();
			}

		}

	}
	
	
	public static void writeToFile(String dirString, String fileString){
		
		Path writeFile = Paths.get(dirString, fileString);
		
		File file = writeFile.toFile();//the toFile() converts the path to a file object
		
		try {
			//PrintWriter printOut = new PrintWriter(new FileOutputStream(file));//this will overwrite the file each time
			PrintWriter printOut = new PrintWriter(new FileOutputStream(file,true));//this will appended the files each time
			
			
			System.out.println("Enter name:");
			Scanner scan = new Scanner(System.in);
			String name = scan.next();
			CountriesTextFile country = new CountriesTextFile(name);
			printOut.println(country);
			printOut.close();//closing flushes out data and closes the object(PrintWriter)
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void readFromFile(String dirString, String filePath){
		Path readFile = Paths.get("Country/Countires.txt");//the hard coded value can changed to use the method parameters
		
		File file = readFile.toFile();
		
		try {
			FileReader fr = new FileReader(file);
			//the benefit of using the buffer is to help us store a block of memory that we can go back to a read from later
			//this is more efficient than the scanner
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while(line != null){
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();//this flushes the buffer and closes it
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Somthing went wrong with this");
			e.printStackTrace();
		}
		
	}
}
