public class Player {
    private Card card;
    private     String[] deck = new String [23];

    private     String[] playerOneCard = new String [7];
    private     String[] playerTwoCard = new String [7];
    private     String[] playerThreeCard = new String [7];
    private     String[] playerFourCard = new String [7];
    private     String[] center = new String [5];

    public Player(){
        card = new Card();
    }


    public void resetCards(){
        card = new Card();
    }
    public void assignCardToPayers(){
        for(int j = 0;j<7;j++){
            playerOneCard[j] =  card.getCards()[j];
        }

        int counter = 0;
        for(int j = 7;j<14;j++){
            playerTwoCard[counter] =  card.getCards()[j];
            counter++;
        }

        counter= 0;
        for(int j = 14;j<21;j++){
            playerThreeCard[counter] =  card.getCards()[j];
            counter++;
        }

        counter=0;
        for(int j = 21;j<28;j++){
            playerFourCard[counter] =  card.getCards()[j];
            counter++;
        }
        counter = 0;
        for(int j = 29;j<52;j++){
            deck[counter] =  card.getCards()[j];
            counter++;
        }
        center[0] = card.getCards()[28];
    }

    public String[] getCenter() {
        return center;
    }

    public String[] getDeck() {
        return deck;
    }

    public String[] getPlayerOneCard() {
        return playerOneCard;
    }

    public String[] getPlayerTwoCard() {
        return playerTwoCard;
    }

    public String[] getPlayerThreeCard() {
        return playerThreeCard;
    }

    public String[] getPlayerFourCard() {
        return playerFourCard;
    }

    public String[] getCards() {
        return card.getCards();
    }
}
