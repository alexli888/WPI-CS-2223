import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int green = 3;
        int yellow = 7;
        int red = 5;
        boolean userTurn;

        System.out.println("Welcome to Double Trouble!");

        // Ask the user who should start
        Scanner scanner = new Scanner(System.in);
        System.out.print("Who should start the game??? (1 for You, 2 for Computer): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            userTurn = true;
            System.out.println("You start the game.");
        } else if (choice == 2) {
            userTurn = false;
            System.out.println("Computer starts the game.");
        } else {
            System.out.println("Invalid choice. You start the game by default.");
            userTurn = true;
        }

        System.out.println("Here's the initial state of the game:");
        displayGameState(green, yellow, red);

        while (true) {
            String currentPlayer = userTurn ? "You" : "Computer";
            int markersToRemove = 0;
            String markerColor = "";

            if (userTurn) {
                // Ask the user to choose the color and the number of markers to remove
                markerColor = getUserColor();
                markersToRemove = getUserMove(currentPlayer, markerColor, green, yellow, red);
            } else {
                // computer will make random moves
                markerColor = getComputerColor(green, yellow, red);
                markersToRemove = getComputerMove(currentPlayer, markerColor, green, yellow, red);
            }

            if (markersToRemove <= 0) {
                System.out.println("Invalid move! You must remove at least one marker.");
                continue;
            }

            if (userTurn) {
                if (markerColor.equals("green")) {
                    green -= markersToRemove;
                } else if (markerColor.equals("yellow")) {
                    yellow -= markersToRemove;
                } else {
                    red -= markersToRemove;
                }
            } else {
                if (markerColor.equals("green")) {
                    green -= markersToRemove;
                } else if (markerColor.equals("yellow")) {
                    yellow -= markersToRemove;
                } else {
                    red -= markersToRemove;
                }
            }

            // Check for negative markers
            if (green < 0 || yellow < 0 || red < 0) {
                System.out.println("Error: Negative number of markers detected. Rerun the Game.");
                break;
            }

            displayGameState(green, yellow, red);

            // Check for a winner
            if (green == 0 && yellow == 0 && red == 0) {
                System.out.println(currentPlayer + " win !!!!!!!!!");
                break;
            }

            // Switch turns
            userTurn = !userTurn;
        }
    }


    //methods
    public static String getUserColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a marker color (green, yellow, red): ");
        return scanner.nextLine().toLowerCase();
    }

    public static int getUserMove(String currentPlayer, String markerColor, int green, int yellow, int red) {
        Scanner scanner = new Scanner(System.in);
        int markersToRemove = 0;

        while (markersToRemove <= 0) {
            System.out.print(currentPlayer + ", enter the number of " + markerColor + " markers to remove: ");
            markersToRemove = scanner.nextInt();
        }

        return markersToRemove;
    }

    public static int getComputerMove(String currentPlayer, String markerColor, int green, int yellow, int red) {
        int markersToRemove = 0;

        // Check if there are markers available for the selected color
        if (markerColor.equals("green") && green > 0) {
            markersToRemove = (int) (Math.random() * (green + 1));
        } else if (markerColor.equals("yellow") && yellow > 0) {
            markersToRemove = (int) (Math.random() * (yellow + 1));
        } else {
            markersToRemove = (int) (Math.random() * (red + 1));
        }

        // Ensure at least one marker is removed
        if (markersToRemove <= 0) {
            markersToRemove = 1;
        }

        System.out.println(currentPlayer + " removes " + markersToRemove + " " + markerColor + " markers.");
        return markersToRemove;
    }
    public static String getComputerColor(int green, int yellow, int red) {
        List<String> availableColors = new ArrayList<>();

        if (green > 0) {
            availableColors.add("green");
        }
        if (yellow > 0) {
            availableColors.add("yellow");
        }
        if (red > 0) {
            availableColors.add("red");
        }

        if (availableColors.isEmpty()) {
            // If there are no colors with markers left, return an empty string
            return "";
        }

        return availableColors.get((int) (Math.random() * availableColors.size()));
    }

    public static void displayGameState(int green, int yellow, int red) {
        System.out.println("Green Markers: " + green);
        System.out.println("Yellow Markers: " + yellow);
        System.out.println("Red Markers: " + red);
        System.out.println();
    }
}
