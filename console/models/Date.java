package models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Date implements Serializable,Comparable<Date> {
    private int day;
    private int month;
    private int year;
    private static final List<Integer> KNUCKLE_MONTHS = Arrays.asList(4, 6, 9, 11);

    public Date(int day, int month, int year) {
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    public Date(String date) {
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4, 8));
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException("Basically a month cannot hold more than 31 days.");
        } else if ((this.year % 4 == 0) && (this.month == 2) && (day > 29)) {
            throw new IllegalArgumentException("29 Days in February Leap year.");
        } else if ((this.year % 4 != 0) && (this.month == 2) && (day > 28)) {
            throw new IllegalArgumentException("28 days a Basic February.");
        } else if (KNUCKLE_MONTHS.contains(this.month) && day > 30) {
            throw new IllegalArgumentException("This month doesn't hold 31 days.");
        } else {
            this.day = day;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("Enter a valid month.");
        } else {
            this.month = month;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 2000 || year > 2099) {
            throw new IllegalArgumentException("Year should be within 2000 to 2099");
        } else {
            this.year = year;
        }
    }

    @Override
    public String toString() {
        String days = (day < 10) ? "0" + day : "" + day;
        String months = (month < 10) ? "0" + month : "" + month;
        return days + "/" + months + "/" + year;
    }

    @Override
    public int compareTo(Date date) {
        int dateOne = date.getDay() + date.getMonth() * 30 + date.getYear() * 365;
        int dateTwo = day +month * 30 + year * 365;
        if (dateOne == dateTwo)
            return 0;
        else if (dateTwo > dateOne)
            return 1;
        else
            return -1;
    }

    @Override
    public boolean equals(Object checkDate) {
        if (this == checkDate) return true;
        if (checkDate == null || getClass() != checkDate.getClass()) return false;
        Date date = (Date) checkDate;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
