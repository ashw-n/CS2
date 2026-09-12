/* Ashwin Gudiseva
  Eustis Setup
  COP3503 Computer Science 2
  EustisSetup.java
*/

import java.io.File;
import java.util.Scanner;

public class EustisSetup 
{
    String name;
    int level;
    double multiplier;

    //Wouldn't compile b/c of line 14 in the driver so i made this
    public EustisSetup()
    {
        name = null;
        level = 0;
        multiplier = 1;
    }

    public void printMessages()
    {
        System.out.println("The semester started in the month of August.");
        System.out.println("The current year is 2026.");
        System.out.println("I am a CS2 student this semester!");
        System.out.println("I am so excited to learn more algorithms and advanced data structures!");
        System.out.println("Marvel Studios is going to release the movie Avengers: Doomsday in December!");
    }

    public int computeTickets(int gamesWon, double ticketMultiplier, int streakBonus)
    {
        int tickets;
        double beforeBonus = gamesWon * 25 * ticketMultiplier;
        
        //Rounding
        if(beforeBonus - ((int)beforeBonus) >= .5)
        {
            tickets = 1;
        }
        else
        {
            tickets = 0;
        }

        tickets += ((int)beforeBonus) + streakBonus;

        return tickets;
    }

    public EustisSetup(String name, int level, double multiplier)
    {
        this.name = name;
        this.level = level;
        this.multiplier = multiplier;

        if(this.level < 1)
        {
            this.level = 1;
        }
        else if(this.level > 50)
        {
            this.level = 50;
        }

        if(this.multiplier < 0)
        {
            this.multiplier = 0;
        }

    }

    public String toString()
    {
        return "ArcadePlayer{name = '" + name + "', level = " + level + ", multiplier = " + multiplier + "}";
    }

    public void category() 
    {
        //For current
        String name;
        double price;
        double revenue;
        double RPR;

        //For overall
        int numMachines = 0;
        double sumRPR = 0;
        double topRPR = 0;
        String topRPRName = null;
        int needRepairs = 0;
        int lowEarners = 0;
        int steadies = 0;
        int popular = 0;
        int superStars = 0;

        File arcade = new File("arcade.in");

        try(Scanner inputScanner = new Scanner(arcade))
        {
            while(inputScanner.hasNext())
            {
                numMachines++;
                
                name = inputScanner.next();
                price = inputScanner.nextDouble();
                revenue = inputScanner.nextDouble();
                
                RPR = revenue / price;
            
                //Formatting
                name = name.replace('_', ' ');

                sumRPR += RPR;

                if(RPR > topRPR)
                {
                    topRPRName = name;
                    topRPR = RPR;
                }
                
                if(RPR >= 2.5)
                {
                    superStars++;
                }
                else if(RPR >= 1.5)
                {
                    popular++;
                }
                else if(RPR >= 1)
                {
                    steadies++;
                }
                else if(RPR >= .75)
                {
                    lowEarners++;
                }
                else
                {
                    needRepairs++;
                }

                System.out.println("-----------------------------------");
                System.out.printf("Game: %s\nPrice: %.2f\nRevenue: %.2f\nRPR: %.2f\n", name, price, revenue, RPR);
                System.out.println("-----------------------------------");
            }

            
            System.out.println("Machines loaded: " + numMachines);
            System.out.printf("Average RPR: %.2f\n", (sumRPR / numMachines));
            System.out.printf("Top RPR: %s (%.2f)\n", topRPRName, topRPR);
            System.out.println("Needs Repair: " + needRepairs + " | Low Earner: " + lowEarners + " | Steady: " + steadies + " | Popular: " + popular + " | Superstar: " + superStars);


        }
        catch (Exception e)
        {
            System.err.println("File not found :( ");
        }

    }
}