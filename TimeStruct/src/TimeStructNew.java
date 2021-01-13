/**
 * Created by Zver on 29.04.2020.
 */
public class TimeStructNew {
    private final static int COUNT_HOURS = 24;
    private final static int COUNT_SECONDS_IN_HOUR = 3600;
    private final static int COUNT_MINUTES_AND_SECONDS = 60;
    private final static int COUNT_SECONDS_IN_DAY = 86400;

    private int hour;
    private int minutes;
    private int seconds;

    public TimeStructNew(int hour, int minutes, int seconds) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    private void changeHour(int count) {
        changeTime(count * COUNT_SECONDS_IN_HOUR);
    }

    private void changeMinutes(int count) {
        changeTime(count * COUNT_MINUTES_AND_SECONDS);
    }

    private void changeSeconds(int count) {
        changeTime(count);
    }

    private int check(int second) {
        if (second > 0) {
            return totalSeconds() + second;
        }
        if (second < 0 && second+totalSeconds()>0) {
            return  second + totalSeconds();
        } else
            return COUNT_SECONDS_IN_DAY + totalSeconds() + second;
    }

    private void changeTime(int second) {
        int totalCountSeconds = check(second);
        setSeconds(giveUpBalance(totalCountSeconds));
        int secondsAfterTakingSeconds = totalCountSeconds - giveUpBalance(totalCountSeconds);
        if (giveUpInteger(secondsAfterTakingSeconds) < COUNT_MINUTES_AND_SECONDS) {
            setMinutes(giveUpInteger(secondsAfterTakingSeconds));
;
        } else {
            setMinutes(giveUpBalance(giveUpInteger(secondsAfterTakingSeconds)));
            if (secondsAfterTakingSeconds / COUNT_SECONDS_IN_HOUR < COUNT_HOURS) {
                setHour(giveUpInteger(secondsAfterTakingSeconds / COUNT_MINUTES_AND_SECONDS));
            } else setHour((secondsAfterTakingSeconds / COUNT_SECONDS_IN_HOUR) % COUNT_HOURS);
        }

    }

    private int giveUpInteger(int count) {
        return count / COUNT_MINUTES_AND_SECONDS;
    }

    private int giveUpBalance(int count) {
        return count % COUNT_MINUTES_AND_SECONDS;
    }

    private int totalSeconds() {
        int totalSeconds = getHour() * COUNT_SECONDS_IN_HOUR
                + getMinutes() * COUNT_MINUTES_AND_SECONDS
                + getSeconds();
        return totalSeconds;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "TimeStruct_new{" +
                "hour=" + hour +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }

    public static void main(String[] args) {
        TimeStructNew timeStruct_new = new TimeStructNew(0, 0, 0);
        System.out.println(timeStruct_new);

        timeStruct_new.changeSeconds(10800);
        timeStruct_new.changeMinutes(530);
//        timeStruct_new.changeHour(5);
        System.out.println(timeStruct_new);
        System.out.println(timeStruct_new.totalSeconds());

    }

}
