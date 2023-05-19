public class Card {

    private     String[] cards = new String [52];
    private String suits[] = {"c","d","h","s"};
    private String [] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};


    public Card() {
        assignCard();
    }

    private void assignCard(){
        int counter = 0;
        for(int i =0;i<4;i++){
            for(int j=0;j<13;j++){
                this.cards[counter] = suits[i] + ranks[j];
                counter++;
            }
        }
    }

    public String[] getCards() {
        return cards;
    }
}
