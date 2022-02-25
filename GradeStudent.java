
import java.util.Scanner;
// student rating assessment
// input weight, score, shifted score => output GPA and comment
public class GradeStudent {
    public static final int TOTAL_POINT_MAX = 100;
    public static final int A_SECTION_POINT_VALUE = 5;
    public static final int SECTION_POINT_MAX  = 30;
    public static final int TOTAL_ASSIGNMENT_MAX = 150;
    public static int weightMax  = 100;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //print introduce program
        begin();

        // calculate midTerm of subject
        double midTerm = midTerm(input);

        // calculate finalTerm of subject
        double finalTerm = finalTerm(input);

        // calculate homeWork of subject
        double homeWork = homeWork(input);

        // calculate GPA and print comment corresponding to GPA
        reports(midTerm, finalTerm, homeWork);

    }
    //print introduce program
    public static void begin() {
        System.out.print("This is program reads exam/homework scores and reports your overall course grade.");
    }
    // calculate midTerm of subject
    public static double midTerm(Scanner input) {
        System.out.println();
        int weight;
        int scoreShifted;
        int shiftAmount = 0;
        System.out.println("Midterm: ");
        //input weight
        do {
            System.out.print("Weight (0-100) ? ");
            weight = input.nextInt();
        }
        while (weight < 0 || weight > 100);
        // calculate WeightMax again
        weightMax = weightMax - weight;
        // input Score
        System.out.print("Score earned? ");
        int score = input.nextInt();
        // add Shifted
        do {
            System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
            scoreShifted = input.nextInt();
        }
        while (scoreShifted != 1 && scoreShifted != 2);
        if (scoreShifted == 1) {
            System.out.print("Shift amount: ");
            shiftAmount = input.nextInt();
        }
        // calculate Total point <=100
        int totalPoint = score + shiftAmount;
        if (totalPoint > TOTAL_POINT_MAX) totalPoint = TOTAL_POINT_MAX;
        // calculate weightScore
        double weightedScore = ((double)totalPoint/TOTAL_POINT_MAX) * weight;
        weightedScore = (double)Math.round(weightedScore * 10) / 10;
        // print information Total point and weightScore
        System.out.println("Total point = " + totalPoint + "/" + TOTAL_POINT_MAX);
        System.out.println("Weighted score = " + weightedScore + "/" + weight);

        return weightedScore;
    }

    // calculate finalTerm of subject
    public static double finalTerm(Scanner input) {
        System.out.println();
        int shiftAmount = 0;
        int weight;
        System.out.println("Final: ");
        // input weight
        int m = 0;
        do {
            if (m != 0) System.out.println("Because you have inputted data of Weight in midTerm so Weight has to < " + weightMax);
            System.out.print("Weight (0-100) ? ");
            weight = input.nextInt();
            m++;
        }
        while(weight < 0 || weight > weightMax - weight);
        //calculate again global WeightMax
        weightMax = weightMax - weight;
        //input Score
        System.out.print("Score earned? ");
        int score = input.nextInt();
        // add shifted
        System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
        int scoreShifted = input.nextInt();
        while (scoreShifted != 1 && scoreShifted != 2) {
            System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
            scoreShifted = input.nextInt();
        }
        if (scoreShifted == 1) {
            System.out.print("Shift amount: ");
            shiftAmount = input.nextInt();
        }
        // calculate Total point
        int totalPoint = score + shiftAmount;
        if (totalPoint > TOTAL_POINT_MAX) totalPoint = TOTAL_POINT_MAX;
        // calculate WeightScore
        double weightScore = ((double)totalPoint / TOTAL_POINT_MAX) * weight;
        weightScore = (double)Math.round(weightScore * 10) / 10;
        // print information Total point and WeightScore
        System.out.println("Total point = " + totalPoint + "/" + TOTAL_POINT_MAX);
        System.out.println("Weighted score = " + weightScore + "/" + weight);

        return weightScore;
    }

    // calculate homeWork of subject
    public static double homeWork(Scanner input) {
        System.out.println();
        int score = 0;
        int max = 0;
        int weight;
        System.out.println("Homework: ");
        //input weight
        int m = 0;
        do {
            if (m != 0) {
                System.out.println("Because you have inputted Weight data in midTerm and finalTerm so Weight has to = " + weightMax + " to make sure sum of three Weight = 100");
            }
            System.out.print("Weight (0-100 ? ");
            weight = input.nextInt();
            m++;
        }
        while(weight != weightMax); // make sure sum of three weight = 100

        //input how many assignment and Score, Max
        System.out.print("Number of assignments? ");
        double scoreFinal = input.nextDouble();
        do {
            for (int i = 0; i < scoreFinal; i++) {
                System.out.print("Assignment " + (i + 1) + " score and max? ");
                score += input.nextInt();
                max += input.nextInt();
            }
        } while (score > max);
        // check condition of score
        if (score > TOTAL_ASSIGNMENT_MAX) score = TOTAL_ASSIGNMENT_MAX;
        //input sections
        System.out.print("How many sections did you attend? ");
        int sections = input.nextInt();
        // calculate TotalSectionPoint (1 section = 5), TotalSectionPoint < SECTION_POINT_MAX
        int totalSectionPoint = sections * A_SECTION_POINT_VALUE;
        if (totalSectionPoint > SECTION_POINT_MAX) totalSectionPoint = SECTION_POINT_MAX;
        // calculate Total point
        int totalPoint = score + totalSectionPoint;
        int totalPointMax = max + SECTION_POINT_MAX;
        // calculate WeightedScore
        double weightedScore = ((double)totalPoint / totalPointMax) * weight;
        weightedScore = (double) Math.round(weightedScore * 10) / 10;
        // print information Section point, Total point, Weight score
        System.out.println("Section points = " + totalSectionPoint + "/" + SECTION_POINT_MAX);
        System.out.println("Total points = " + totalPoint + "/" + totalPointMax);
        System.out.println("Weight score = " + weightedScore);

        return weightedScore;
    }

    // calculate GPA of student
    public static void reports( double midTermScore, double FinalScore, double homeWorkScore){
        System.out.println();
        double overallPercentage = midTermScore + FinalScore + homeWorkScore;
        double GPA;
        if (overallPercentage >= 85) {
            GPA = 3.0;
        } else if (overallPercentage >= 75) {
            GPA = 2.0;
        } else if (overallPercentage >= 60) {
            GPA = 1.0;
        } else GPA = 0.0;
        System.out.println("Overall percentage = " + overallPercentage);
        System.out.println("Your grade will be at least = " + GPA);
        GPA_Comment(GPA);
    }
    // output comment corresponding to GPA
    public static void GPA_Comment(double GPA){
        if (GPA == 3.0) System.out.println("Excellent");
        else if (GPA == 2.0) System.out.println("Very Good");
        else if (GPA == 1.0) System.out.println("Good");
        else System.out.println("Fail");

    }
}

