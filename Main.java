import java.util.Scanner;

/**
 * This program calculates a person's blood alcohol concentration (BAC)
 * according to user-inputs including: weight, gender, number of drinks, and hours drinking.
 * Program later outputs whether current BAC is below or above legal limit.
 * @author Rheanne Pranata
 * @since 2024-10-18
 * @version 1.0
 */

public class Main {
    /**
     * Method calculateBAC takes in arguments for
     * user's weight, gender, number of drinks, and hours drinking.
     * Then, method computes appropriate conversions to synthesize
     * arguments together into the BAC formula.
     * @param weight user weight in pounds
     * @param gender user gender, M for male and F for female
     * @param drinks number of standard drinks user drank
     * @param hours number of hours user has been drinking
     * @return user BAC, expressed as a decimal value
     */
    public static double calculateBAC(int weight, String gender, int drinks, double hours) {
        final double MALE_CONSTANT = 0.68;
        final double FEMALE_CONSTANT = 0.55;
        final double GRAMS_PER_POUND = 454; // Use grams for calculation

        double weightInGrams = weight * GRAMS_PER_POUND; // Convert weight to grams
        double gramsOfAlcohol = drinks * 14; // Calculate grams of alcohol consumed

        double distributionRatio = MALE_CONSTANT;
        if (gender.equalsIgnoreCase("F")) {
            distributionRatio = FEMALE_CONSTANT;
        }

        double initialBAC = gramsOfAlcohol / (weightInGrams * distributionRatio) * 100;
        double metabolizedAmount = 0.015 * hours;
        return initialBAC - metabolizedAmount;

    }

    /**
     * Method returning a boolean value to
     * indicate if user's BAC level is above or below legal limit.
     * @param userBAC user BAC expressed as a decimal value
     * @return true if below legal limit, false if at or above legal limit
     */
    public static boolean legalLimit(double userBAC) {
        return userBAC < 0.08;
    }

    public static void main(String[] args) {
        // TODO 1: Get inputs from the user
        Scanner scnr = new Scanner(System.in);

        System.out.print("What is your weight in pounds? No decimal points. Weight: ");
        int userWeight = scnr.nextInt();

        System.out.print("Enter 'M' for Male, 'F' for Female. Gender: ");
        String userGender = scnr.next();

        System.out.print("How many drinks did you consume? Drinks: ");
        int userDrinks = scnr.nextInt();

        System.out.print("How long have you been drinking? You can use decimal points. Hours: ");
        double userHours = scnr.nextDouble();


        // TODO 2: Invoke the calculateBAC() method
        double userBAC = calculateBAC(userWeight, userGender, userDrinks, userHours);


        // TODO 3. Check if BAC is above/below legal limit
        boolean underLegalLimit = legalLimit(userBAC);

        System.out.printf("\nYour BAC is: %.4f.\n", userBAC);
        if (underLegalLimit) {
            System.out.println("You are below the legal limit. You can safely drive.");
        }
        else if (!underLegalLimit) {
            System.out.println("You are above the legal limit! You cannot drive yet.");
        }

        scnr.close();

    }
}