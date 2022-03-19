//ICS
//JOSH STALLINGS
//ASSIGNMENT 02

import java.time.*;
import java.math.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class Program 
{
	public static String minuteTime (int minute)
	{
		String minuteTime = "";
		if(minute<10)
		{
			minuteTime = Integer.toString(minute);
			minuteTime = "0"+minuteTime;
		}
		else minuteTime = Integer.toString(minute);
		
		return minuteTime;
	}
	
	public static String secondTime (int second)
	{
		String secondTime = "";
		if(second<10)
		{
			secondTime = Integer.toString(second);
			secondTime = "0"+secondTime;
		}
		else secondTime = Integer.toString(second);
		
		return secondTime;
	}
	
	public static int tilMidnight (int hour, int minute, int second)
	{
		int hourTil = 23 - hour;
		int minuteTil = 60 - minute;
		int secondTil = 60 - second;
		
		hourTil*=(60*60);
		minuteTil*=60;
		
		secondTil+=hourTil + minuteTil;
		
		System.out.println(secondTil + " seconds left until midnight. \n");
		
		return secondTil;
	}
	
	public static void sinceMidnight (int hour, int minute, int second)
	{
		int hourSince = hour*(60*60);
		int minuteSince = minute*60;
		int secondSince = second;
		
		int timeSince = hourSince + minuteSince + secondSince;
		
		System.out.println(timeSince + " seconds since midnight.\n");	
	}
	
	public static void dayLeft (int hour, int minute, int second)
	{
		double secondsPerDay = 86400;
		double secondTil = tilMidnight(hour, minute, second);
		
		double percentLeft = (secondTil / secondsPerDay) * 100;
		
		BigDecimal dayLeft = new BigDecimal(percentLeft);
		MathContext m = new MathContext(4);
		dayLeft = dayLeft.round(m);
		
		System.out.println("There is "+dayLeft+"% of the day left.\n");
	}
		
	public static void showTime()throws InterruptedException
	{
			LocalDateTime now = LocalDateTime.now();
			
			int hour = now.getHour();
			int i = 0;
			if(hour>12)
			{
				i++;
				hour-=12;
			}
			
			String day="";
			if(i==0) day = "am";
			else day = "pm";
			
			int minute = now.getMinute();
			String minuteString = Integer.toString(minute);
			
			int second = now.getSecond();
			String secondString = Integer.toString(second);
			
			System.out.print("It is "+hour+":"+minuteString+":"+secondString+" in the "+day+"! \r");
			Thread.sleep(1);
	}
	
	public static void calendar()
	{
		Calendar cal = 	Calendar.getInstance();
		Date d = new Date();
		int year = d.getYear()+1900;
		int month = cal.get(Calendar.DAY_OF_MONTH);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		String[] week = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday\n"};
		String date = Integer.toString(month);
		
		String stndrdth = "";

		int dateLength = date.length();
		
		if(date.substring(dateLength-1).equals("1")) stndrdth = "st";
		else if(date.substring(dateLength-1).equals("2")) stndrdth = "nd";
		else if(date.substring(dateLength-1).equals("3")) stndrdth = "rd";
		else stndrdth = "th";
		
		System.out.println("It is "+week[day]+" "+month+stndrdth+" "+year);
	}
	public static void alarm() throws Exception
	{
		//FILE THINGS		
		File video = new File("video.mp4");
		Desktop desktop = Desktop.getDesktop();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("What time do you want to wake up? Enter in the form of a 24 hour clock and in the form "+
		"\"Hours:Minutes\" and if either is <10 then type it as 1, not 01 or 05 for example.\np.s. turn the volume UP\n");
		
		String input = scan.nextLine();
		String[] time = input.split(":");
		
		int hour;
		int minute;
		while(true)
		{
			try
			{
				hour = Integer.parseInt(time[0]);
				minute = Integer.parseInt(time[1]);
				break;
			}
		
			catch(Exception e)
			{
				System.out.println("You didn't type in valid value(s)");
				input = scan.nextLine();
				time = input.split(":");
			}
		}
		
		System.out.println("You're alarm goes off @ "+hour+":"+minute);


		while(true)
		{
			LocalDateTime now = LocalDateTime.now();
			int nowHour = now.getHour();
			int nowMinute = now.getMinute();
			showTime();
			if((nowHour==hour)&&(nowMinute==minute))
			{
				desktop.open(video);
				break;
			}
		}
		
		
		//FILE THINGS		
		//File video = new File("video.mp4");
		//Desktop desktop = Desktop.getDesktop();
		//PrintWriter out = new PrintWriter(new FileWriter("time.txt"));
		//desktop.open(video);
	}
	
	public static void ascii()
	{
		System.out.println("                           .* &&&&&*  ,///,. *&@&&&./*                          \n"+
"                      (#@& /            &            (.&&%(                     \n"+
"                  #&&#,               &  %#               ,*&&(.                \n"+
"              .%&#*    &              & .  /             &    /#&%%             \n"+
"            %&%.        &* &          &  &            &          ,%&&           \n"+
"          %&%            , .                          (             @&&.        \n"+
"        %&%              , ,                          #               @&&       \n"+
"      #&#                                                               @&&     \n"+
"     @&#  &  & &  %                                            (/ &  ,&  &&&    \n"+
"    &@.      & &  &                                              &         @&   \n"+
"   %@.       & &  %    &&                                      ,,(&&&       &&  \n"+
"  &&#                    (&&&                              &&&&&.           &&& \n"+
" #&%.                       *&&&                       &&&*               %  @&(\n"+
" %&%                             &&               &%                         %&%\n"+
" %@      %  &                       .&&/(*#&&&                      /         &%\n"+
" &@       &&&                        &&%@&&.                                  @&\n"+
" %&.      &&,                         ..(.&&,                       &%&       @%\n"+
" %&&                                                                         %&%\n"+
" .&@                                                                         %&.\n"+
"  &&&  &                                                                 ,  %@& \n"+
"   &&%        & &                      ,***                     &.         /&&  \n"+
"    &@%      .  /                                              & .        /@&   \n"+
"     &&@                                                         .       #&&    \n"+
"      &&@                                                           %   %&%     \n"+
"        &&@   &                                      &                %@&       \n"+
"          &&&            (             &&%             ,       %    &@&         \n"+
"            @&@&   #    &             &&%&           /&.    (    %@&&           \n"+
"               @&&&                   %  &                    &@@&              \n"+
"                  @&@&&      &                            &&@&&                 \n"+
"                      &&&@&(%                       *,&@&&&                     \n"+
"                            #&&&&@@@&&(/*/#&&@@@&&&&%                           \n\n");
	}
	public static void main (String[] args) throws Exception
	{
		ascii();
		Scanner sc = new Scanner(System.in);
		LocalDateTime now = LocalDateTime.now();
		
		int hour = now.getHour();		
		int minute = now.getMinute();		
		int second = now.getSecond();
		 
		while(true)
		{
			System.out.println("Choose one of the MANY options below :^), just type the number next to the word. \n 1. Calendar"+
			"\n 2. Time (choose this last) \n 3. Time Until Midnight \n 4. Time Since Midnight \n 5. Day Left \n 6. Alarm\n 7. Close \n");
			
			String input = sc.nextLine();
			
			if(input.equals("1")) calendar(); 
			else if(input.equals("2"))
			{
				while(true)
				{
					showTime();
				}
			}
			else if(input.equals("3"))tilMidnight(hour, minute, second); 
			else if(input.equals("4"))sinceMidnight(hour, minute, second); 
			else if(input.equals("5"))dayLeft(hour, minute, second);
			else if(input.equals("6"))alarm();
			else if(input.equals("7"))break;
			else System.out.println("Haha dummy. alls you had to do was type a number. try it again. \n");
		}
		
		
	}
}
                                                                               
//CALENDAR METHODS
//DAY_OF_WEEK, DAY_OF_YEAR, DAY_OF_WEEK_IN_MONTH

//URL THINGS
//URL url = new URL("https://www.youtube.com/watch?v=1vrEljMfXYo");
//desktop.browse(new URI("https://www.youtube.com/watch?v=1vrEljMfXYo"));
