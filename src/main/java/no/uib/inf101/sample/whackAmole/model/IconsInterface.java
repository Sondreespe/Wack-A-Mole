package no.uib.inf101.sample.whackAmole.model;

public interface IconsInterface {

    /**
    * This methods starts the timer of the icon. 
    * When the timer starts it calls on hideIcon and showIcon with
    * a certain amount of milliseconds between
    */
    void startTimer();
    
    /**
    * This Methods stop the timer of the Icon. Also stopping the showing and
    * hiding of the icon
    */
    void stopTimer();

    /**
     * Gets a random index from randomIndex() and checks if the index is occupied
     * , if it is then it changes the index. when the index is "free" it adds this index to the set of occupied indexes
     *  and then shows the icon on the button with the index
     */
    void showIcon();
    
    /**
     * Goes through every index in occupiedIndexes and sets the button with index
     * to holeIcon. Then it clears the list og occupied indexes
     */
    void hideIcon();

    /**
     * Checks if the button with index index is occupied
     * @param index
     * @return True if it is occupied. False if it is free
     */
    boolean isOccupied(int index);

    /**
     * Generates a ramndom Index between between 0 and the number of tiles on the board
     * @return
     */
    int randomIndex();

    
}
