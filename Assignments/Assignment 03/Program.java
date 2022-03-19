//ICS
//JOSH STALLINGS
//ASSIGNMENT 03

import java.time.*;
import java.util.*;

public class Program
{
	public static void seconds(int num, String[] choices)
	{
		double days = num/86400.0;
		double daysRemainder = days%1;
		int dayx = (int)(days - daysRemainder);
		
		
		double hours = daysRemainder*24;
		double hoursRemainder = hours%1;
		int hourx = (int) (hours - hoursRemainder);
		
		double minutes = hoursRemainder*60;
		double minutesRemainder = minutes%1;
		int minutex = (int)(minutes - minutesRemainder);
		
		double seconds = minutesRemainder*60;
		double secondsRemainder = seconds%1;
		seconds = seconds - secondsRemainder;
		if(secondsRemainder>0.5) seconds++;
				
		System.out.println(dayx+" day(s) "+hourx+" hour(s) "+minutex+" minute(s) and "+seconds+" second(s) in a total of "+num+" "+choices[1]+"\n");
		
	}
	public static void days(int num, String[] choices)
	{
		int days = num;
		int seconds = days*86400;
		System.out.println("There's "+seconds+" seconds in "+days+" "+choices[1]+"\n");
		
	}
	public static int yearsToSeconds(String dob, int year)
	{
		int secondsPerYear = 86400 * 365;
		int yearOfBirth = Integer.valueOf(dob);
		int difference = year - yearOfBirth;
		

		int leapYears = yearOfBirth/year;
		int seconds = (secondsPerYear*difference)+(86400*leapYears);
		return seconds;
	}	
	
	public static int monthsToSeconds(String mob, int month)
	{
		int seconds = 0;
		int birthMonth = Integer.valueOf(mob)-1;
		int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		for(int i=0;i<months.length;i++)
		{
			seconds+=(months[i]*86400);
		}
		
		return seconds;
	}
	public static void main(String[] arg)
	{
		while(true)
		{
			System.out.println("Type in a number and whether it's seconds or days. It DOES have to bea whole day or second. \"10 Days\" for instance. \'Close\' will close this, otherwise it loops.\n");
			Scanner sc = new Scanner(System.in);		
			
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("close")) break;
			String[] choices = input.split(" ");
			
			while(true)
			{
				if(choices.length<2)
				{
					System.out.println("You did not enter a value for time or a value for the amount of time. Retype both, correctly.\n");
					input = sc.nextLine();
					choices = input.split(" ");
				}
				
				else break;
			}
			
			while(true)
			{
				if(choices[1].equalsIgnoreCase("days") || choices[1].equalsIgnoreCase("seconds") || choices[1].equalsIgnoreCase("second") || choices[1].equalsIgnoreCase("day")) break;
				
				else
				{
					System.out.print("What you typed for days or seconds was invalid. Type in days or seconds, correctly:.\n");
					choices[1] = sc.nextLine();
				}

			}
			
			int num;
			while(true)
			{
				try
				{
					num = Integer.parseInt(choices[0]);
					break;	
				}
				catch(Exception e)
				{
					System.out.println("You entered an incorrect value. Type in the number of "+choices[1]+"correctly: \n");
					choices[0] = sc.nextLine();
				}
			}
			
			if(choices[1].equalsIgnoreCase("days") || choices[1].equalsIgnoreCase("day")) days(num, choices);
			else seconds(num, choices);
			
		}
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the date of your birth in the form 'MM-DD-YYYY': ");
		String input = sc.nextLine();
		
		String[] dob = input.split("-");
		
		while(true)
		{
			if(dob[2].length()==4) break;
			else
			{
				System.out.print("Retype a value for the year you were born. It has to be four digits, such as 2012: ");
				dob[2] = sc.nextLine();
			}
		}
		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		int yearsToSeconds = yearsToSeconds(dob[2], year);
		int monthsToSeconds = monthsToSeconds(dob[0], month);
		int totalSecondsAlive = yearsToSeconds + monthsToSeconds;
		
		System.out.println("You have been alive for "+totalSecondsAlive+" seconds!");
	}
}
