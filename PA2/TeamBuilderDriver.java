import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TeamBuilderDriver
{
    public static void main(String[] args) throws Exception
    {
        if (args.length != 1)
        {
            System.out.println("Usage: java TeamBuilderDriver <test_case_number>");
            return;
        }

        int testCase = Integer.parseInt(args[0]);

        Scanner input = new Scanner(new File("tc" + testCase + ".in"));

        int n = input.nextInt();
        int teamSize = input.nextInt();
        double minTotalGPA = input.nextDouble();

        Student[] students = new Student[n];
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++)
        {
            String name = input.next();
            String skill = input.next();
            double gpa = input.nextDouble();
            String standing = input.next();

            students[i] = new Student(name, skill, gpa, standing);
            indexMap.put(name, i);
        }

        int numConflicts = input.nextInt();
        int[][] conflicts = new int[numConflicts][2];

        for (int i = 0; i < numConflicts; i++)
        {
            String s1 = input.next();
            String s2 = input.next();

            conflicts[i][0] = indexMap.get(s1);
            conflicts[i][1] = indexMap.get(s2);
        }

        input.close();

        ArrayList<ArrayList<String>> solutions = TeamBuilder.buildTeams(students, teamSize, minTotalGPA, conflicts);

        System.out.println("Total Solutions: " + solutions.size());
        System.out.println();

        for (int i = 0; i < solutions.size(); i++)
        {
            System.out.print("Solution " + (i + 1) + ": ");

            ArrayList<String> team = solutions.get(i);
            for (int j = 0; j < team.size(); j++)
            {
                if (j > 0)
                    System.out.print(" ");
                System.out.print(team.get(j));
            }
            // System.out.println();
            System.out.println();
        }
    }
}