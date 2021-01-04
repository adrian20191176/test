import models.FootballClub;
import models.Match;

public interface LeagueManager {
    void addFootballClub(FootballClub club);
    void deleteFootballClub(String clubName);
    void displayFootballClub(String clubName);
    void printPointsTable();
    void addMatch(Match match);
    void printMatches();
}
