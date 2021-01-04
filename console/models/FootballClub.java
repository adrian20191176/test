package models;

public class FootballClub extends SportsClub implements Comparable<FootballClub>{
    //can create objects out of this classes. (Premier league teams, Arsenal).
    private String ground; // Home ground of the team.
    private int noOfWins;  // Number of Matches won
    private int noOfLoses; // Number of Matches lost
    private int noOfDraws; // Number of Matches drawn
    private int goalsFor;  // Total Number of goals scored throughout the season
    private int goalsAgainst; //Total Number of goals conceded by a team
    //Number of points can be calculated, with noOfWins*3, and noOfDraws*1.
    //Likewise number of matches can be calculated by adding noOfWins, noOfLoses and noOfDraws.

    //At the time of creating no need put values about the season progress
    public FootballClub(String clubName,String ground, String location, String coach) {
        super(clubName, location, coach);
        this.ground = ground;
//      double a = Math.random()*(max-min+1)+min ; Reference https://www.javatpoint.com/how-to-generate-random-number-in-java
//        noOfWins= (int) (Math.random()*(10-1+1)+1); // Only for testing purpose, points are generated automatically with matches
        noOfWins=0;noOfLoses=0;noOfDraws=0;goalsFor=0;goalsAgainst=0;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public int getNoOfWins() {
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins) {
        this.noOfWins = noOfWins;
    }

    public int getNoOfLoses() {
        return noOfLoses;
    }

    public void setNoOfLoses(int noOfLoses) {
        this.noOfLoses = noOfLoses;
    }

    public int getNoOfDraws() {
        return noOfDraws;
    }

    public void setNoOfDraws(int noOfDraws) {
        this.noOfDraws = noOfDraws;
    }

    public int getGoalsFor(){
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    //Derived attribute Points
    public int getPoints(){
        return noOfWins*3 + noOfDraws;
    }

    //Derived attribute - Goal Difference
    public int getGoalsDifference(){
        return goalsFor - goalsAgainst;
    }

    //Derived attribute Number of matches
    public int getNoOfMatches(){
        return noOfDraws+noOfLoses+noOfWins;
    }

    // A method update the team stats after a match
    public void updateClub (int goalsFor,int goalsAgainst){
        if(goalsFor>goalsAgainst) {
            noOfWins++;
        }
        else if(goalsFor==goalsAgainst){
            noOfDraws++;
        }
        else {
            noOfLoses++;
        }
        this.goalsFor +=goalsFor;
        this.goalsAgainst += goalsAgainst;
    }

    @Override
    public String toString() {
        return   "=====================================\n"
                +"  "+this.getClubName()+"\n"
                +"=====================================\n"
                +"Number of wins  : "+this.getNoOfWins()
                +"\nNumber of loses : "+this.getNoOfLoses()
                +"\nNumber of draws : "+this.getNoOfDraws()
                +"\nPlayed matches  : "+this.getNoOfMatches()
                +"\nPoints Obtained : "+this.getPoints()
                +"\nHome ground     : "+this.getGround()
                +"\nCoach Name      : "+this.getCoach();
    }

    @Override
    public int compareTo(FootballClub club) {
        return this.getClubName().compareTo(club.getClubName());
    }

}
