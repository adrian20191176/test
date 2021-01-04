package models;

public class UniversityFootballClub extends FootballClub{
    //inherits footballClub
    private String university; //name or the university which the club belongs to

    public UniversityFootballClub(String clubName, String ground, String location, String coach, String university) {
        super(clubName, ground, location, coach);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return   "=====================================\n"
                +"  "+this.getClubName()+" | "+ university +" | University\n"
                +"=====================================\n"
                +"\nHome ground     : "+this.getGround()
                +"\nCoach Name      : "+this.getCoach();
    }
}
