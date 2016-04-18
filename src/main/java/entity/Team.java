package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salman on 4/16/2016.
 */
public class Team {

    private String name;
    private int score;
    private List<TeamStat> stat;

    public Team(String name) {
        this.name = name;
        score = 0;
        stat = new ArrayList<TeamStat>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<TeamStat> getStat() {
        return stat;
    }

    public void setStat(List<TeamStat> stat) {
        this.stat = stat;
    }

    public void addStat(TeamStat newStat) {
        this.stat.add(newStat);
    }

    public void incrementScore() {
        this.score++;
    }
}
