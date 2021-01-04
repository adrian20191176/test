package models;

public class SchoolFootballClub extends FootballClub {
    //inherits FootballClub
    private String school; //name of the school which the team belongs to.

    public SchoolFootballClub(String clubName, String ground, String location, String coach, String school) {
        super(clubName, ground, location, coach);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "=====================================\n"
                +"  "+this.getClubName()+" | "+ school +" | School\n"
                +"=====================================\n"
                +"\nHome ground     : "+this.getGround()
                +"\nCoach Name      : "+this.getCoach();
    }

}
