import java.util.ArrayList;
import java.util.List;

public class TeamBuilder
{
    public static ArrayList<ArrayList<String>> buildTeams(Student[] students, 
        int teamSize, double minTotalGPA, int[][] conflicts)
    {
        int n = students.length;
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

        //Adjacency List to check for conflicts
        ArrayList<List<Integer>> conflictList = new ArrayList<List<Integer>>();

        for(int i = 0; i < n; i++)
        {
            conflictList.add(new ArrayList<Integer>());
        }

        for(int[] pair : conflicts)
        {
            int a = pair[0];
            int b = pair[1];
            conflictList.get(a).add(b);
            conflictList.get(b).add(a);
        }

        boolean[] selected = new boolean[n];
        ArrayList<String> currTeam = new ArrayList<String>();

        //Calling helper
        backtrack(students, teamSize, minTotalGPA, conflictList, selected, currTeam, 0, 0,
                0, 0, 0, 0, 0, 0, 0.0, results);
 
        return results;

    }

    //Backtracking helper
    //Too many parameters? prolly 
    // but it works >:)
    private static void backtrack(Student[] students, int teamSize, double minTotalGPA, 
        ArrayList<List<Integer>> conflictList, boolean[] selected, ArrayList<String> currTeam, 
        int ind, int currSize, int aiCount, int softCount, int hwCount, int cyberCount, int juniorCount, 
        int seniorCount, double totalGPA, ArrayList<ArrayList<String>> results)
    {
        int n = students.length;

        //Base case
        if(currSize == teamSize)
        {
            if(aiCount >= 1 && softCount >= 1 && hwCount >= 1 && cyberCount >= 1 && juniorCount >= 1 && seniorCount >= 1 && totalGPA >= minTotalGPA)
            {
                results.add(new ArrayList<String>(currTeam));
            }
            
            return;
        }

        //Reached last student, but team not full
        if(ind == n)
        {
            return;
        }

        int remainingSpots = teamSize - currSize;
        int remainingStudents = n - ind;

        //Pruning 
        if(remainingStudents < remainingSpots)
        {
            return;
        }

        //Counting num of each time we still need
        int neededSkillCount = (aiCount == 0 ? 1 : 0) + (softCount == 0 ? 1 : 0) + (hwCount == 0 ? 1 : 0) + (cyberCount == 0 ? 1 : 0);
        int neededStandCount = (juniorCount == 0 ? 1 : 0) + (seniorCount == 0 ? 1 : 0);

        if(remainingSpots < neededSkillCount || remainingSpots < neededStandCount)
        {
            return;
        }

        //If no conflict
        boolean hasConflict = false;
        for(int c : conflictList.get(ind))
        {
            if(selected[c])
            {
                hasConflict = true;
                break;
            }
        }

        //Include current student
        if(!hasConflict)
        {
            selected[ind] = true;
            currTeam.add(students[ind].getName());

            int nextAi = aiCount;
            int nextSoft = softCount;
            int nextHw = hwCount;
            int nextCyber = cyberCount;
            int nextJunior = juniorCount;
            int nextSenior = seniorCount;

            String skill = students[ind].getSkill();

            if (skill.equals("AI"))
            {
                nextAi++;
            }
            else if (skill.equals("Software"))
            {
                nextSoft++;
            }
            else if (skill.equals("Hardware"))
            {
                nextHw++;
            }
            else if (skill.equals("Cybersecurity"))
            {
                nextCyber++;
            }
 
            String standing = students[ind].getStanding();
            if (standing.equals("Junior"))
            {
                nextJunior++;
            }
            else if (standing.equals("Senior"))
            {
                nextSenior++;
            }

            double nextGpaTot = totalGPA + students[ind].getGpa();

            //Recursive call
            backtrack(students, teamSize, minTotalGPA, conflictList, selected, currTeam, ind + 1, currSize + 1,
                    nextAi, nextSoft, nextHw, nextCyber, nextJunior, nextSenior, nextGpaTot, results);

            //Actual backtracking part
            currTeam.remove(currTeam.size() - 1);
            selected[ind] = false;

        }

        //Else, exclude current student 
        backtrack(students, teamSize, minTotalGPA, conflictList, selected, currTeam, ind + 1, currSize,
                aiCount, softCount, hwCount, cyberCount, juniorCount, seniorCount, totalGPA, results);

    }
}