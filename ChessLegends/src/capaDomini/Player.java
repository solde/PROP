package capaDomini;

/**
 *
 * @author Daniel Palomo
 */
public abstract class Player {

    protected static final double DEFAULT_ELO = 1000.0;

    protected String id;
    protected int wins;
    protected int loses;
    protected double ELO;
    protected double OP_rating;
    protected String password;
    
    /**
     * Retruns the id on player
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sests the id on player
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retruns the wins for player
     *
     * @return
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the wins of player
     *
     * @param wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Returns the loses of player
     *
     * @return int
     */
    public int getLoses() {
        return loses;
    }

    /**
     * Sets the loses of player
     *
     * @param loses
     */
    public void setLoses(int loses) {
        this.loses = loses;
    }

    /**
     * increments the loses of player
     */
    public void incLoses() {
        this.loses += 1;
    }

    /**
     * increments the wins on plalyer
     */
    public void incWins() {
        this.wins += 1;
    }

    /**
     * Retruns the elo variable on player
     *
     * @return
     */
    public double getELO() {
        return ELO;
    }

    /**
     * Sets the elo variable on player
     *
     * @param ELO
     */
    public void setELO(double ELO) {
        this.ELO = ELO;
    }

    /**
     * Retruns the op_rating of player
     *
     * @return float
     */
    public double getOP_rating() {
        return OP_rating;
    }

    /**
     * Restarts the elo of player to 1000
     */
    public void restartELO() {
        this.ELO = DEFAULT_ELO;
    }

    /**
     * Sets the op_rating of player
     *
     * @param OP_rating
     */
    public void setOP_rating(double OP_rating) {
        this.OP_rating = OP_rating;
    }

    /**
     * Increments OP_rating by the number passed as parameter
     *
     * @param OP_rating
     */
    public void incOP_rating(double OP_rating) {
        this.OP_rating += OP_rating;
    }

    /**
     * This function calcualtes the Elo of player based on this wins, loses, and
     * the Oponents elo
     */
    public void calcualteELO() {
        double new_elo = (OP_rating + (400.0 * (wins + (double) loses))) / ((double) wins + (double) loses);
        this.ELO = new_elo;
    }

    /**
     * Returns all player's information via String
     *
     * @return String
     */
    public String getPlayerInfo() {
        String ret = "";

        ret = ret.concat(this.id);
        ret = ret.concat(" ");
        ret = ret.concat(String.valueOf(ELO));
        ret = ret.concat(" ");
        ret = ret.concat(String.valueOf(wins));
        ret = ret.concat(" ");
        ret = ret.concat(String.valueOf(loses));

        return ret;
    }
    
    abstract String getPassword();
    
    public Board makeMove(Board b, boolean color, int depth) throws Exception {  
        try{return null;}
        catch (Exception e){
            throw new chessException(e.getMessage());
        }
        
    }
    
}
