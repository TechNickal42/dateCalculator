package nl;

import java.util.Scanner;

public class Easter
{

    static boolean isLeapYear(int year)
    {
        if(year%4==0 && year%100!=0){
            return true;


        }
        else if (year%100==0 && year%400!=0) {
            return false;

        }
        else if (year%400==0) {
            return true;

        }
        else{
            return false;

        }
    }

    static int numberOfDaysInMonth(int year, Month month)
    {
        int monthNumber = month.number();

        switch (monthNumber){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(isLeapYear(year)){
                    return 29;
                }
                else{
                    return 28;
                }
            default:
                return 30;
        }


    }

    static Month easterMonth(int year)
    {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = ( b + 8 ) / 25;
        int g = (b-f+1)/3;
        int h = ( 19 * a + b - d - g + 15 ) % 30;
        int i = c / 4;
        int k = c % 4;
        int L = ( 32 + 2* e + 2 * i - h - k ) % 7;
        int m = ( a + 11 * h + 22 * L) / 451;
        int month = ( h + L - 7 * m + 114 ) / 31;
        return Month.month(month);

    }

    static int easterDay(int year)
    {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = ( b + 8 ) / 25;
        int g = (b-f+1)/3;
        int h = ( 19 * a + b - d - g + 15 ) % 30;
        int i = c / 4;
        int k = c % 4;
        int L = ( 32 + 2* e + 2 * i - h - k ) % 7;
        int m = ( a + 11 * h + 22 * L) / 451;
        int day = ( ( h + L - 7 * m + 114 ) % 31 ) + 1;
        return day;

    }

    static int dayNumberInYear(int day, Month month, int year)
    {

        boolean leapYear= isLeapYear(year);
        int numberMonth= month.number() -1 ;
        int previousMonths=0;
        while(numberMonth>0){

            if(numberMonth<8 && numberMonth%2!=0 && numberMonth!=2){
                previousMonths+=31;
            }

            else if (numberMonth<8 && numberMonth%2==0 && numberMonth!=2) {

                previousMonths+=30;
            }

            else if (numberMonth==2) {
                if(leapYear){
                    previousMonths+=29;
                }
                else{
                    previousMonths+=28;
                }
            }

            else if (numberMonth>7 && numberMonth%2!=0) {
                previousMonths+=30;

            }
            else if (numberMonth>7 && numberMonth%2==0){
                previousMonths+=31;
            }

            numberMonth-=1;
        }
        int DayNumber= day+ previousMonths;
        return DayNumber;
    }

    static Month monthInYearOfDayNumber(int dayNumber, int year)
    {
        int numberMonth=0;
        boolean leapYear= isLeapYear(year);
        int daysPassed=0;
        while(daysPassed<dayNumber){
            numberMonth+=1;
            if(numberMonth<8 && numberMonth%2!=0 && numberMonth!=2){
                daysPassed+=31;
            }

            else if (numberMonth<8 && numberMonth%2==0 && numberMonth!=2) {

                daysPassed+=30;
            }

            else if (numberMonth==2) {
                if(leapYear){
                    daysPassed+=29;
                }
                else{
                    daysPassed+=28;
                }
            }

            else if (numberMonth>7 && numberMonth%2!=0) {
                daysPassed+=30;

            }
            else if (numberMonth>7 && numberMonth%2==0){
                daysPassed+=31;
            }


        }
        return Month.month(numberMonth);


    }

    static int dayInMonthOfDayNumber(int dayNumber, int year)
    {
        int currentDay= 0;
        boolean leapYear= isLeapYear(year);
        Month theMonth= monthInYearOfDayNumber(dayNumber, year);
        int numberMonth= theMonth.number();
        for(int i=1; i<numberMonth+1; i++){
            if(i<8 && i%2!=0 && i!=2){
                currentDay+=31;
            }

            else if (i<8 && i%2==0 && i!=2) {

                currentDay+=30;
            }

            else if (i==2) {
                if(leapYear){
                    currentDay+=29;
                }
                else{
                    currentDay+=28;
                }
            }

            else if (i>7 && i%2!=0) {
                currentDay+=30;

            }
            else if (i>7 && i%2==0){
                currentDay+=31;
            }
        }
        int daysInMonth = numberOfDaysInMonth(year,theMonth);
        return daysInMonth-(currentDay-dayNumber);

    }

    static void showHolyDays(int year)
    {
        System.out.println("easter is on: " + easterDay(year) + " " + easterMonth(year));

        int carnivalDayStart= dayNumberInYear(easterDay(year) -49, easterMonth(year), year);
        int carnivalDayEnd= dayNumberInYear(easterDay(year) -47, easterMonth(year), year);

        System.out.println("carnival starts on: " + dayInMonthOfDayNumber(carnivalDayStart,year) + " " + monthInYearOfDayNumber(carnivalDayStart,year) + " and it ends on: " +dayInMonthOfDayNumber(carnivalDayEnd,year) + " " + monthInYearOfDayNumber(carnivalDayEnd,year));

        int goodFriday = dayNumberInYear(easterDay(year) -2, easterMonth(year), year);

        System.out.println("Good Friday is on: " + dayInMonthOfDayNumber(goodFriday,year) + " " + monthInYearOfDayNumber(goodFriday,year));

        int whitsuntideDay = dayNumberInYear(easterDay(year) +49, easterMonth(year), year);
        System.out.println("Whitsuntide is on: " + dayInMonthOfDayNumber(whitsuntideDay,year) + " " + monthInYearOfDayNumber(whitsuntideDay,year));

        int ascensionDay= dayNumberInYear(easterDay(year) +39, easterMonth(year), year);
        System.out.println("Ascension Day is on: " + dayInMonthOfDayNumber(ascensionDay,year) + " " + monthInYearOfDayNumber(ascensionDay,year));


    }

    static int doomsdayAlgorithm(int year){
        int doomsdayCenturie=0; //this integer references a day of the week.
        int yearDivided= year/100;
        switch ((yearDivided)%4){
            case 0:
                doomsdayCenturie=2;
                break;
            case 1:
                doomsdayCenturie=0;
                break;
            case 2:
                doomsdayCenturie=5;
                break;
            case 3:
                doomsdayCenturie=3;
                break;
        }

        int yearAfterCenturie= year%100;
        int leapYearsPassed= yearAfterCenturie/4;

        int doomsday= (doomsdayCenturie + yearAfterCenturie + leapYearsPassed) %7;
        return doomsday;


    }

    static void makeCalender(int year){
        //to determine which day the calender starts I am going to use something called the doomsday algorithm
        int doomsday= doomsdayAlgorithm(year); // There are certain dates in a year called doomsdays who all share the same day of the week

        int beginningOfMonth=0;
        if(isLeapYear(year)){
            beginningOfMonth=7+(doomsday-3); //the 4th of january is a doomsday in a leapyear. If you know on what day the 4th of January is you also know the day of the first day of the year
        }
        else{
            beginningOfMonth= 7+(doomsday-2); //for non leap years it is 3rd of january.
        }

        int daysLeftInweek=6-beginningOfMonth;

        System.out.println(year);
        for(int i=1; i<13; i++){
            int daysInMonth= numberOfDaysInMonth(year,Month.month(i));

            System.out.println(" ");
            System.out.println(Month.month(i));
            System.out.println("Su Mo Tu We Th Fr Sa");


            beginningOfMonth=6-daysLeftInweek;

            while(beginningOfMonth>0){
                System.out.print("   ");
                beginningOfMonth-=1;
            }
            int daysPutInMonth=1;


            while(daysPutInMonth<daysInMonth+1){

                while(daysLeftInweek>-1 && daysPutInMonth<daysInMonth+1){

                    System.out.print(daysPutInMonth + " ");

                    if (daysPutInMonth<10){
                        System.out.print(" ");
                    }

                    daysPutInMonth+=1;
                    daysLeftInweek-=1;

                }
                System.out.println(" ");
                if(daysPutInMonth<daysInMonth+1){
                    daysLeftInweek=6;
                }


            }


        }
        System.out.println(" ");

    }

    public static void main(String[] arguments)
    {
        System.out.println("What is your year?");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();


        showHolyDays(year);
        System.out.println(" ");
        System.out.println(" ");
        makeCalender(year);



    }


}


