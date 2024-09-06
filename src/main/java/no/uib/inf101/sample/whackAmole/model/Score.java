package no.uib.inf101.sample.whackAmole.model;

public class Score {
    private static int score = 0;
    
    /**
     * This method increases the score by the parameter "num."
     * @param num
     */
    public static void increaseScore(int num){
        score += num;
    }

    /**
     * this method decreases the score by the param "num"
     * @param num
     */
    public static  void decreaseScore(int num){
        score -= num;
    }

    /**
     * returns the current score
     * @return
     */
    public static  int getScore(){
        return score;
    }

    /**
     * resets the score to 0
     */
    public static  void resetScore(){
        score = 0;
    }
}
