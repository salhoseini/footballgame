package entity;

/**
 * Created by Salman on 4/16/2016.
 */
public class TeamStat {

    private Player scorer;
    private int minute;

    public TeamStat(Player scorer, int minute) {
        this.scorer = scorer;
        this.minute = minute;
    }

    public Player getScorer() {
        return scorer;
    }

    public void setScorer(Player scorer) {
        this.scorer = scorer;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
