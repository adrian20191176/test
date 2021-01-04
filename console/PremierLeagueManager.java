import Controllers.Validation;
import models.Date;
import models.FootballClub;
import models.Match;
import models.PointComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PremierLeagueManager implements LeagueManager {
    private static List<FootballClub> clubList;
    private static List<Match> matchList;
    private static final File clubFile  = new File("clubs.dat");
    private static final File matchFile = new File("matches.dat");
    public static final int CAPACITY = 20; // maximum amount of teams in a premier league.

    public PremierLeagueManager(){
        clubList = new ArrayList<>();
        matchList = new ArrayList<>();
    }

    @Override
    public void addFootballClub(FootballClub club) {
        if(clubList.contains(club)){ //Checks whether club already exists.
            System.out.println("This club is already added in the league.");
            return;
        }
        clubList.add(club);
        saveClubMatches(clubFile,clubList);
        System.out.println(club.getClubName()+" club has been added successfully.");
        System.out.println("Total number of current clubs in the league : "+clubList.size());
    }

    @Override
    public void deleteFootballClub(String clubName) {
        for (FootballClub club: clubList){
            if (club.getClubName().equals(clubName)){ //checking for the club to delete.
                clubList.remove(club);
                System.out.println(club.getClubName()+" club has been deleted successfully.");
                System.out.println("Total number of current clubs in the league : "+clubList.size());
                saveClubMatches(clubFile,clubList);
                return;
            }
        }
        System.out.println("Please a enter a club which exists in the league.");
    }

    @Override
    public void displayFootballClub(String clubName) {
        getClubMatches();
        if(clubList.size()==0){ //If the footballClub list empty, then no point of printing.
            System.out.println("Currently there are no club in the league. Please add a league first.");
            return;
        }
        for (FootballClub club: clubList){ //checks for the club in the league.
            if (club.getClubName().equals(clubName) ){
                System.out.println(club);
                System.out.println();
                return;
            }
        }
        System.out.println("Please a enter a club which exists in the league.");
    }

    @Override
    public void printPointsTable() {
        getClubMatches();
        if(clubList.size()==0){ //checks whether the league has teams.
            System.out.println("Currently there are no club in the league. Please add a league first.");
            return;
        }
        sortList(1); // sorted according points in desc.
        int count = 1; int large = setLarge();
        System.out.println("      "+ Validation.clubNameWithSpace("",large-1)+"| MP |  W  |  D  |  L  | GF | GA | GD | PTS");
        for(FootballClub club: clubList){
            if(club.getClass().toString().equals("class models.FootballClub")) {
                String clubStats = Validation.zeroChecker(count)
                        + "|" + Validation.clubNameWithSpace(club.getClubName(), large)
                        + "|" + Validation.zeroChecker(club.getNoOfMatches())
                        + "|" + Validation.zeroChecker(club.getNoOfWins())
                        + " |" + Validation.zeroChecker(club.getNoOfDraws())
                        + " |" + Validation.zeroChecker(club.getNoOfLoses())
                        + " |" + Validation.zeroChecker(club.getGoalsFor())
                        + "|" + Validation.zeroChecker(club.getGoalsAgainst())
                        + "|" + Validation.zeroChecker(club.getGoalsDifference())
                        + "|" + Validation.zeroChecker(club.getPoints());
                System.out.println(clubStats);
            }
            count++;
        }
        System.out.println();
    }

    //Adding Match
    @Override
    public void addMatch(Match match){
        matchList.add(match);
        saveClubMatches(matchFile,matchList); //Matches are saved
        saveClubMatches(clubFile,clubList); //Every time matches are updated, teams will be updated as well.
        System.out.println("Entry entered successfully!");
        System.out.println("Total number of matches : "+matchList.size());
    }

    @Override
    public void printMatches() {
        getClubMatches();
        if(matchList.size()==0){
            System.out.println("Currently no matches took place to be displayed.");
            return;
        }
        for(Match match : matchList)
            System.out.println(match);
    }

    public List<FootballClub> getClubList() {
        return clubList;
    }

    // A simple method to check the existence of a club
    public static FootballClub getClub(String clubName) {
        try {
            FootballClub returnClub = null;
            for (FootballClub club : clubList) {
                if (club.getClubName().equals(clubName)) {
                    returnClub = club;
                }
            }
            return returnClub;
        }catch (NullPointerException e){
            System.out.println("There is no such club in the leagues");
        }
        return null;
    }

    public  List<Match> getMatchList() {
        return matchList;
    }

    //A common static method to save both matches and club
    public static <T> void  saveClubMatches(File storage,List<T> list) {
        try (FileOutputStream fos = new FileOutputStream(storage);
             ObjectOutputStream ous = new ObjectOutputStream(fos))
        {
            ous.writeObject(list);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load the previous state!");
        } catch (IOException e) {
            System.out.println("IO error occurred");
        }
    }

    //Method read the previous state.
    public static <T> List<T> readFile(File storage){
        List<T> list = null;
        try (FileInputStream fis = new FileInputStream(storage);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            list = (List<T>) ois.readObject();
        } catch (IOException e) {
            System.out.println("IO error occurred");
        } catch (ClassNotFoundException e) {
            System.out.println("Class was not found.");
        }
        return list;
    }

    // a non static created for to be used in the main method.
    public void getClubMatches(){
        clubList = readFile(clubFile);
        matchList = readFile(matchFile);
    }

    // Option one for points sorting
    //Option 2 will sort according team Name
    public void sortList(int option) {
        switch(option) {
            case 1:
                Collections.sort(clubList, new PointComparator());
                break;
            case 2:
                Collections.sort(clubList);
                break;
        }
    }

    public int setLarge(){
        int large = 0;
        for(FootballClub club: clubList) {
            int newLarge = club.getClubName().length();
            if (newLarge > large)
                large = newLarge;
        }
        return large;
    }

    public static boolean getClubCheck(String clubName){
        if(clubList.size()<2){ // Checking whether enough clubs to create a match.
            System.out.println("At least two clubs should be added in the league, before creating a match.");
            return false;
        }
        for(FootballClub club : clubList) {
            if (club.getClubName().equals(clubName)&& club.getClass().toString().equals("class models.FootballClub")) {
                return true;
            }else if(club.getClubName().equals(clubName)){
                System.out.println("Only football clubs can play a match. Neither university nor school club can play.");
                return false;
            }
        }
        System.out.println("There is no such club in the league\n");
        return false;
    }
}


