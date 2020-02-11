import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Main {
    public static String readInputFileName(Scanner in) {
        System.out.println("Filename: ");
        String inputFileName = in.nextLine();
        return inputFileName;
    }

    public static int readInputDistance(Scanner in) {
        System.out.println("Neighbours distance: ");
        String inputDistance = in.nextLine();
        return Integer.parseInt(inputDistance);
    }

    public static int readInputMaxError(Scanner in) {
        System.out.println("Max error: ");
        String inputMaxError = in.nextLine();
        return Integer.parseInt(inputMaxError);
    }

    public static Dot makeDotFromInputLine(String inputLine) {
        String[] arrayOfInputValues = inputLine.split(",");
        return new Dot(arrayOfInputValues);
    }

    public static boolean checkIfTwoDotsAreNeighbours(Dot firstDot, Dot secondDot, int distance) {
        int x1 = firstDot.getX_coordinate();
        int x2 = secondDot.getX_coordinate();
        int y1 = firstDot.getY_coordinate();
        int y2 = secondDot.getY_coordinate();
        if (pow((x1 - x2), 2) + pow((y1 - y2), 2) <= pow(distance, 2)) {
            return true;
        }
        return false;
    }

    public static void checkSensorsFromFile(String fileName, int distance, int maxError) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String line = new String();
        Path path = Paths.get(fileName);
        int lineCount = (int) Files.lines(path).count();
        Dot[] sensors = new Dot[lineCount];
        int[] sensorsToCheck = new int[lineCount];
        int dotNumber = 0;
        while (sc.hasNext()) {
            dotNumber++;
            line = sc.nextLine();
            Dot dot = makeDotFromInputLine(line);
            sensors[dotNumber - 1] = dot;
            for (int i = 0; i < dotNumber - 1; i++) {   // for each dot before the current one
                if (checkIfTwoDotsAreNeighbours(sensors[i], dot, distance)  //check for broken sensors
                        && abs(dot.getValue() - sensors[i].getValue()) > maxError) {
                    sensorsToCheck[i] = 1;
                    sensorsToCheck[dotNumber - 1] = 1;
                    break;   //we do not need to check for more dots, we have already 2 neighbours with different values
                }
            }
        }
        if (dotNumber == 0) {
            System.out.println("No sensors to be checked! Please, add sensors to the file!");
            return;
        }
        int numberOfBrokenSensors = 0;
        System.out.print("Please check sensors at: ");
        for (int i = 0; i < sensorsToCheck.length; i++) {
            if (sensorsToCheck[i] == 1) {
                if (numberOfBrokenSensors > 0) {
                    System.out.print(", ");
                }
                sensors[i].printDotInfo();
                numberOfBrokenSensors++;
            }
        }
        if (numberOfBrokenSensors == 0) {
            System.out.println("All sensors seem to be correct!");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String fileName = readInputFileName(in);
        int distance = readInputDistance(in);
        int maxError = readInputMaxError(in);
        checkSensorsFromFile(fileName, distance, maxError);
    }
}
