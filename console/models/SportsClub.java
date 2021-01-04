package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {
        private String clubName; // Name of the sports club.
        private String location; // City where the club is situated
        private String coach;    // HeadCoach who is in charge of the team

        public SportsClub(String clubName, String location, String coach){
                this.clubName = clubName;
                this.location = location;
                this.coach = coach;
        }

        public String getClubName() {
                return clubName;
        }

        public void setClubName(String clubName) {
                this.clubName = clubName;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getCoach() {
                return coach;
        }

        public void setCoach(String coach) {
                this.coach = coach;
        }

        @Override
        public String toString() {
                return "Name of the club: " +clubName;
        }

        @Override
        public boolean equals(Object club) {
                if (this == club){
                        return true;
                }
                if (club == null || getClass() != club.getClass()){
                        return false;
                }
                SportsClub that = (SportsClub) club;
                return Objects.equals(clubName, that.clubName) &&
                        Objects.equals(location, that.location) &&
                        Objects.equals(coach, that.coach);
        }

        @Override
        public int hashCode() {
                return Objects.hash(clubName, location, coach);
        }
}
