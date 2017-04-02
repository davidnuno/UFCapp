package com.example.android.ufcapp;

/**
 * This class is used to describe a current UFC fighter.
 */

public class Fighter {

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The nickname name. */
    private String nickname;

    /** Number of wins. */
    private int wins;

    /** Number of losses. */
    private int losses;

    /** Number of draws. */
    private int draws;

    /** Is he/she champion is division. */
    private boolean isChampion;

    /**
     * The constructor.
     *
     * @param firstName The first name of the fighter.
     * @param theLast  The last name of the fighter.
     * @param wins  The number of wins of the fighter.
     * @param losses  The number of losses of the fighter.
     * @param draws The number of draws of the fighter.
     * @param isChampion Is the fighter a current champion in his/her weight division.
     * @param age The age of the fighter.
     * */
    public Fighter (String firstName, String theLast, String nickname, int wins, int losses, int draws, boolean isChampion) {

        this.firstName   = firstName;
        this.lastName    = theLast;
        this.wins        = wins;
        this.nickname    = nickname;
        this.losses      = losses;
        this.draws       = draws;
        this.isChampion  = isChampion;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLosses() {
        return losses;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDraws() {
        return draws;
    }

    public void setChampion(boolean isChampion) {
        this.isChampion = isChampion;
    }

    public boolean isChampion() {
        return isChampion;
    }
}
