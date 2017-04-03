package com.example.android.ufcapp;

/**
 * This class is used to store information of a current UFC fighter such as name,
 * wins, losses, and image.
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

    /** Fighter thumbnail. */
    private String thumbnail;

    /**
     * The constructor.
     *
     * @param firstName The first name of the fighter.
     * @param theLast  The last name of the fighter.
     * @param wins  The number of wins of the fighter.
     * @param losses  The number of losses of the fighter.
     * @param draws The number of draws of the fighter.
     * @param isChampion Is the fighter a current champion in his/her weight division.
     * @param thumbnail The fighter thumbnail.
     * */
    public Fighter (String firstName, String theLast, String nickname, int wins, int losses, int draws, boolean isChampion, String thumbnail) {

        this.firstName  = firstName;
        this.lastName   = theLast;
        this.wins       = wins;
        this.nickname   = nickname;
        this.losses     = losses;
        this.draws      = draws;
        this.isChampion = isChampion;
        this.thumbnail  = thumbnail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public boolean isChampion() {
        return isChampion;
    }

    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public String getThumbnail() { return thumbnail; }
}
