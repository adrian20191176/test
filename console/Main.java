import Controllers.Validation;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static PremierLeagueManager plManager = new PremierLeagueManager();
    static Validation input = new Validation();
    public static void main(String[] args){
    // startConsole();
    plManager.getClubMatches();
        System.out.println("Welcome to Premier League Manager !");
        while (true){
            main();
            int option = input.returnInt("Option");
            switch(option){
                //String clubName,String ground, String location, String coach
                case 1:
                    String choice = null;
                    for(int i=0; i<5 ; i++){
                        System.out.println("Press 'F' | to create a football club");
                        System.out.println("Press 'U' | to create a university football club.");
                        System.out.println("Press 'S' | to create a school football club.");
                        choice = input.returnString("your choice").toLowerCase();
                        if((Arrays.asList("f","u","s")).contains(choice))
                            break;
                        else if(i==4){
                            System.exit(1);
                        }
                        else
                            System.out.println("Please select a valid choice from the below shown options.");
                    }
                    if (!(plManager.getClubList().size()<plManager.CAPACITY)){
                        System.out.println("There is no space for a team in the premier league");
                    }
                    String clubName = input.returnString("name of the club");
                    String ground = input.returnString("ground");
                    String location = input.returnString("location of the club");
                    String coach = input.returnString("name of the coach");
                    FootballClub club = new FootballClub(clubName,ground,location,coach);
                    switch(choice){
                        case "u":
                            String university = input.returnString("university of the club");
                            club = new UniversityFootballClub(clubName,ground,location,coach,university);
                            break;
                        case "s":
                            String school = input.returnString("school of the club");
                            club = new SchoolFootballClub(clubName,ground,location,coach,school);
                            break;
                    }
                    plManager.addFootballClub(club);
                    break;
                case 2:
                    plManager.printPointsTable();
                    break;
                case 3:
                    String displayClub = input.returnString("name of the team to display");
                    plManager.displayFootballClub(displayClub);
                    break;
                case 4:
                    String deleteClub = input.returnString("the club to delete");
                    plManager.deleteFootballClub(deleteClub);
                    break;
                case 5:
                    //(Date matchDate, FootballClub homeTeam, FootballClub awayTeam, int homeTeamGoals, int awayTeamGoals)
                    String date = null;
                    for(int i=0; i<5;i++) {
                        date = ""+input.returnInt("Date (DDMMYYYY)");
                        if(input.validateDate(date)==100) {
                            if(i==4){
                                System.out.println("\nSystem going down due to multiple continuous wrong inputs.");
                                System.exit(1);
                            }
                            System.out.println("There is no such date. Please type a logical date.");
                            continue;
                        }else break;
                    }
                    Date matchDate = new Date(date);
                    String homeTeam = input.returnString("home team");
                    if(!PremierLeagueManager.getClubCheck(homeTeam)){
                        break;
                    }
                    String awayTeam = input.returnString("away team");
                    if(awayTeam.equals(homeTeam)){
                        System.out.println("You have entered the same home team for away team");
                        break;
                    }
                    else if(!PremierLeagueManager.getClubCheck(awayTeam)){
                        break;
                    }
                    int homeTeamGoals = input.returnInt("goals scored by home team");
                    int awayTeamGoals = input.returnInt("goals scored by away team");
                    Match match = new Match(matchDate, PremierLeagueManager.getClub(homeTeam), PremierLeagueManager.getClub(awayTeam),homeTeamGoals,awayTeamGoals);
                    plManager.addMatch(match);
                    break;
                case 6:
                    plManager.printMatches();
                    break;
                case 9:
                    System.exit(1);
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    //Console menu
    public static void main(){
        System.out.println("\nPress 1 | to add a team");
        System.out.println("Press 2 | to view the points table");
        System.out.println("Press 3 | to view a team");
        System.out.println("Press 4 | to delete a team");
        System.out.println("Press 5 | to create a match");
        System.out.println("Press 6 | to print matches");
        System.out.println("Press 9 | to quit");
    }

    //  A method for testing purpose
    public static void startConsole(){
        FootballClub tempOne = new FootballClub("Chelsea","Stamford Bridge","Fulham","Maurizio Sarri");
        FootballClub tempTwo = new SchoolFootballClub("Footers","Kotahena","Colombo","Thansuhan","Bens");
        FootballClub tempThree = new UniversityFootballClub("Asura","President","College Street","Kevin","IIT");
        FootballClub tempFour = new FootballClub("Arsenal","Arsenal Stadium","Islington","Mikel Arteta");
        FootballClub tempFive = new FootballClub("Manchester United","Old Trafford","Manchester","Jose  Mourinho");

        plManager.addFootballClub(tempOne);
        plManager.addFootballClub(tempTwo);
        plManager.addFootballClub(tempFour);
        plManager.addFootballClub(tempThree);
        plManager.addFootballClub(tempFive);
        Date sample = new Date(10,10,2020);

        Match matchOne = new Match(sample,tempOne,tempFour,5,5);
        Match matchTwo = new Match(sample,tempOne,tempFive,5,3);
        Match matchThree = new Match(sample,tempFour,tempFive,4,0);
        plManager.addMatch(matchOne);
        plManager.addMatch(matchTwo);
        plManager.addMatch(matchThree);

        plManager.printMatches();
        plManager.printPointsTable();
    }
}
