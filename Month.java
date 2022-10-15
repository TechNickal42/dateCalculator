package nl;

public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    /**
     * Return the month's number (JANUARY=1)
     * @return
     */
    public int number()
    {
        return ordinal()+1;
    }
    /**
     * Return the month with specified number (JANUARY=1)
     * @param number
     * @return month
     */
    public static Month month(int number)
    {
        return values()[number-1];
    }
}
