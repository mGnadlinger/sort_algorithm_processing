package at.htlleonding;

import processing.core.PApplet;

import java.util.*;

public class Bsp01 extends PApplet {

    //static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int numberOfArrays = 800;
    static int[][] liste = new int[numberOfArrays][];

    static Map<Integer, int[]> coordinatesSwap = new HashMap<>();
    static Map<Integer, int[]> coordinatesComparison = new HashMap<>();

    public static void main(String[] args) {
        initializeArrays();
        PApplet.main("at.htlleonding.Bsp02", args);
    }

    public void settings() {
        size(liste.length, (liste.length * liste.length) / 1000);
    }

    public void setup() {
        for (int i = 0; i < liste.length; i++) {
            int[] sortedResult = insertionSort(liste[i]);
            coordinatesSwap.put(i, new int[]{liste[i].length, sortedResult[0]});
            coordinatesComparison.put(i, new int[]{liste[i].length, sortedResult[1]});
        }
    }

    public void draw() {
        background(0);

        for (int i = 0; i < coordinatesSwap.size(); i++) {
            int[] swapCoordinate = coordinatesSwap.get(i);
            int[] comparisonCoordinate = coordinatesComparison.get(i);

            float scaleFactor = (float) height / ((liste.length * liste.length) / 15);

            int scaledSwapX = (swapCoordinate[0] * width / liste.length);
            int scaledSwapY = height - (int) (swapCoordinate[1] * scaleFactor);

            int scaledComparisonX = (comparisonCoordinate[0] * width / liste.length);
            int scaledComparisonY = height - (int) (comparisonCoordinate[1] * scaleFactor);

            // Draw the circles
            fill(255, 0, 0);
            circle(scaledSwapX, scaledSwapY, 5); // Draw swap circle

            fill(0, 0, 255);
            circle(scaledComparisonX, scaledComparisonY, 5); // Draw comparison circle
        }
    }

    public static void initializeArrays() {
        for (int i = 0; i < numberOfArrays; i++) {
            liste[i] = new int[random.nextInt(10, 500)]; // noch umschreiben
            for (int j = 0; j < liste[i].length; j++) {
                liste[i][j] = random.nextInt(0, 200);
            }
        }
    }

    public static int[] grundsortierung(int[] numberArray) {

        int swapCounter = 0;
        int comparisonCounter = 0;

        for (int i = 0; i < numberArray.length - 1; i++) {
            for (int j = i + 1; j < numberArray.length; j++) {
                comparisonCounter++;
                if (numberArray[j] < numberArray[i]) {
                    swapCounter++;
                    int temp = numberArray[j];
                    numberArray[j] = numberArray[i];
                    numberArray[i] = temp;

                }
            }
        }

        return new int[]{swapCounter, comparisonCounter};
    }

    public static int[] bubbleSort(int[] numberArray) {

        int swapCounter = 0;
        int comparisonCounter = 0;

        for (int i = 0; i < numberArray.length - 1; i++) {
            for (int j = 0; j < numberArray.length - i - 1; j++) {
                comparisonCounter++;
                if (numberArray[j] > numberArray[j + 1]) {
                    int temp = numberArray[j];
                    numberArray[j] = numberArray[j + 1];
                    numberArray[j + 1] = temp;
                    swapCounter++;
                }
            }
        }

        return new int[]{swapCounter, comparisonCounter};
    }

    public static int[] selectionSort(int[] numberArray) {

        int swapCounter = 0;
        int comparisonCounter = 0;

        for (int i = 0; i < numberArray.length-1; i++) {
            int minPosition = i;
            for (int j = i; j < numberArray.length; j++) {
                comparisonCounter++;
                if (numberArray[minPosition] > numberArray[j]) {
                    minPosition = j;
                }
            }
            comparisonCounter++;
            if (numberArray[i] != numberArray[minPosition]) {
                int temp = numberArray[i];
                numberArray[i] = numberArray[minPosition];
                numberArray[minPosition] = temp;
                swapCounter++;
            }
        }
        System.out.println(Arrays.toString(numberArray));
        return new int[]{swapCounter, comparisonCounter};
    }

    public static int[] insertionSort(int[] numberArray) {

        int swapCounter = 0;
        int comparisonCounter = 0;

        for (int i = 0; i < numberArray.length; i++) {
            int current = numberArray[i];
            int k = i - 1;
            comparisonCounter++;
            while ((k >= 0) && (current < numberArray[k])) {
                numberArray[k + 1] = numberArray[k];
                k--;
                swapCounter++;
            }
            numberArray[k + 1] = current;
        }
        return new int[]{swapCounter, comparisonCounter};
    }
}
