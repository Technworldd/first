import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Match {
    private int day;
    private int matchNumber;
    private String homeTeam;
    private String awayTeam;
    private String matchTime;

    public Match(int day, int matchNumber, String homeTeam, String awayTeam, String matchTime) {
        this.day = day;
        this.matchNumber = matchNumber;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchTime = matchTime;
    }

    public int getDay() {
        return day;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getMatchTime() {
        return matchTime;
    }
}
class RoundRobin {
    private static final String[] TIME = {"Morning", "Afternoon"};
    private static final int MATCHES_PER_DAY = 2;
    private static final List<String> TEAMS = Arrays.asList("Team1", "Team2", "Team3", "Team4", "Team5");

    public static void main(String[] args) {
        List<Match> schedule = new ArrayList<>();

        int matchNumber = 1;
        for (int day = 1; day <= TEAMS.size() - 1; day++) {
            List<String> availableTeams = new ArrayList<>(TEAMS);
            Collections.shuffle(availableTeams);
            for (int match = 1; match <= MATCHES_PER_DAY; match++) {
                String timeSlot = TIME[match-1];
                String homeTeam = availableTeams.remove(0);
                String awayTeam = availableTeams.remove(0);
                schedule.add(new Match(day, matchNumber, homeTeam, awayTeam, timeSlot));
                matchNumber++;
            }
        }

        for (Match match : schedule) {
            System.out.println("Day " + match.getDay()  + " - " + match.getAwayTeam() + " vs " + match.getHomeTeam() + " - " + match.getMatchTime());
        }
    }
}