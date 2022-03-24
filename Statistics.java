import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Statistics program reads a file and then displays
 * the median, mean and mode of the integers in the file.
 *
 * @author Ketia Gaelle Kaze
 * @version 1.0
 * @since 2022-03-15
 */

class Statistics {
    /**
     * Creating private constructor due to
     * public/default constructor error.
     *
     * @throws IllegalStateException
     *
     */
    private Statistics() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Display integers to the console.
     *
     * @param arrayOfNums passed in
     *
     * @return mean
     */

    public static double calcMean(int[] arrayOfNums) {

        int sum = 0;
        double average = 0;

        for (int counter = 0; counter < arrayOfNums.length; counter++) {
            sum += arrayOfNums[counter];
        }
        average = (double) sum / arrayOfNums.length;
        return average;
    }

    /**
     * Display integers to the console.
     *
     * @param arrayOfNums
     *
     * @return median
     */
    public static double calcMedian(int[] arrayOfNums) {

        Arrays.sort(arrayOfNums);
        int midNum = arrayOfNums.length / 2;
        int median = 0;

        if (arrayOfNums.length % 2 == 0) {
            median = (arrayOfNums[midNum] + arrayOfNums[midNum - 1]) / 2;
        } else {
            median = arrayOfNums[midNum];
        }
        return median;
    }

    /**
     * Display integers to the console.
     *
     * @param arrayOfNums
     *
     * @return mode
     */
    public static List<Integer> calcMode(int[] arrayOfNums) {

        List<Integer> modeList = new ArrayList<Integer>();
        int maxCount = 0;
        int count = 0;
        int value = 0;
        for (int counter = 0; counter < arrayOfNums.length; counter++) {
            if (arrayOfNums[counter] == value) {
                count++;
            } else {
                count = 0;
            }

            if (count > maxCount) {
                maxCount = count;
                modeList.removeAll(modeList);
                modeList.add(value);
            } else if (count == maxCount) {
                modeList.add(arrayOfNums[counter]);
            }
            value = arrayOfNums[counter];
        }
        return modeList;
    }

    /**
     * Main entry into the statistics program.
     *
     * @param args nothing passed in
     *
     * @throws IOException if no file is passed in
     */

    public static void main(String[] args)
            throws IOException {

        List<String> listOfStrings = new ArrayList<String>();

        // buffered reader to read the text file
        BufferedReader bf = null;
        // catch error if no text file is entered
        try {

            if (null != args[0] && args[0].length() > 4
                     && args[0].endsWith(".txt")) {
                bf = new BufferedReader(new FileReader(args[0]));
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        String line = bf.readLine();

        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        bf.close();
        // get the size of the array
        int[] intArray = new int[listOfStrings.size()];

        for (int counter = 0; counter < listOfStrings.size(); counter++) {
            intArray[counter] = Integer.parseInt(listOfStrings.get(counter));
        }
        // Display the mode, median and mean to the console

        System.out.println(listOfStrings);
        double listMean = calcMean(intArray);
        System.out.println("The average is " + listMean);

        double listMedian = calcMedian(intArray);
        System.out.println("The median is " + listMedian);

        List<Integer> listMode = new ArrayList<Integer>();
        listMode = calcMode(intArray);
        System.out.println("The mode(s) is/are " + listMode);
    }
}
