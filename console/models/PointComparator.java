package models;

import java.util.Comparator;

public class PointComparator implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub clubOne, FootballClub clubTwo) {
        int check = clubTwo.getPoints();
        if (clubOne.getPoints() == check) {
            check = clubTwo.getGoalsDifference();
            if (clubOne.getGoalsDifference() == check)
                return 0;
            else if (clubOne.getGoalsDifference() > check)
                return -1;
            else
                return 1;
        }
        else if (clubOne.getPoints() > check)
            return -1;
        else
            return 1;
    }
}
