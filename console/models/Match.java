package models;

import java.io.Serializable;
import java.util.Objects;

public class Match implements Serializable,Comparable<Match>{
    private Date matchDate;
    private FootballClub homeTeam;
    private FootballClub awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;

    public Match(Date matchDate, FootballClub homeTeam, FootballClub awayTeam, int homeTeamGoals, int awayTeamGoals) {
        this.matchDate = matchDate;
        setHomeTeamGoals(homeTeamGoals);
        setAwayTeamGoals(awayTeamGoals);
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public FootballClub getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FootballClub homeTeam) {
        this.homeTeam = homeTeam;
        this.homeTeam.updateClub(homeTeamGoals,awayTeamGoals);
    }

    public FootballClub getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FootballClub awayTeam) {
        this.awayTeam = awayTeam;
        this.awayTeam.updateClub(awayTeamGoals,homeTeamGoals);
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        if(awayTeamGoals<0){
            throw new IllegalArgumentException("Goal can not be negative");
        }
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        if(awayTeamGoals<0){
            throw new IllegalArgumentException("Goal can not be negative");
        }
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public String toString() {
        String homeTeam = this.homeTeam.getClubName();
        String awayTeam = this.awayTeam.getClubName();
        return matchDate + "| "+homeTeam+" "+homeTeamGoals+ " - " + awayTeamGoals + " "+awayTeam;
    }

    @Override
    public boolean equals(Object matchObject) {
        if (this == matchObject) {
            return true;
        }
        if (matchObject == null || getClass() != matchObject.getClass()){
            return false;
        }
        Match match = (Match) matchObject;
        return homeTeamGoals == match.homeTeamGoals &&
                awayTeamGoals == match.awayTeamGoals &&
                Objects.equals(matchDate, match.matchDate) &&
                Objects.equals(homeTeam, match.homeTeam) &&
                Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchDate, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals);
    }

    @Override
    public int compareTo(Match match) {
        return this.getMatchDate().compareTo(match.getMatchDate());
    }
}
