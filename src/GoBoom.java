import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoBoom {

    private Player player;
    private     int[] turn = new int[4];
    private     int[]  card_number = new int[4];
    private     int round = 0;
    private     int incrementer = 0;
    private     int determine_player = 0;
    private     int[] score =  new int[4];
    private     String[] playing = new String[4];
    private     int num = 0;
    private     int winner = 0;

    public GoBoom(){
        player = new Player();
    }



    public void reshuffleCards(){
        List<String> shuffle_cards  = Arrays.asList(player.getCards());
        Collections.shuffle(shuffle_cards);
        shuffle_cards.toArray(player.getCards());
    }





    public int firstPlayerDetermine(String lead_card){
        if((lead_card.contains("A")) || (lead_card.contains("5")) || (lead_card.contains("9")) || (lead_card.contains("k"))){
            round =  1;
            return 1;
        }
        else if((lead_card.contains("2")) || (lead_card.contains("6")) || (lead_card.contains("10"))){
            round =  1;
            return 2;
        }
        else if((lead_card.contains("3")) || (lead_card.contains("7")) || (lead_card.contains("j"))){
            round =  1;
            return 3;
        }
        else if((lead_card.contains("4")) || (lead_card.contains("8")) || (lead_card.contains("Q"))){
            round =  1;
            return 4;
        }
        else{
            round =  1;
            return 1;
        }

    }




    public int nextPlayer(int turn){
        if(turn < 4){
            turn = turn + 1;
            round = round + 1;
            return turn;
        }
        else if(turn == 4){
            round = round + 1;
            return 1;
        }
        else{
            round = round + 1;
            return 1;
        }

    }



    public String acceptsCard(){
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        String card = sc.next();
        return card;

    }




    public void validateCard(String played_card, int playerPosition){
        boolean  check_center = false;
        boolean  check_rank  = false;
        boolean check_player = false;
        int rank = 0;
        String rank_str = "";
        String played_str = played_card.substring(1);
        char  suitch = player.getCenter()[0].charAt(0);
        int played_card_rank = getValue(played_str);


        for(int i = 4; i >= 0 ;i--){
            if(player.getCenter()[i] != " " && player.getCenter()[i] != null){
                rank_str = player.getCenter()[i].substring(1);
                break;
            }
        }
        rank = getValue(rank_str);

        if(suitch == (played_card.charAt(0))){
            check_center = true;
        }

        if(played_card_rank > rank){
            check_rank = true;
        }
        for(int i = 0;i<7;i++){
            switch(playerPosition){
                case 1:
                    if(player.getPlayerOneCard()[i].contains(played_card)){
                        player.getPlayerOneCard()[i] = " ";
                        check_player = true;
                    }
                    break;
                case 2:
                    if(player.getPlayerTwoCard()[i].contains(played_card)){
                        player.getPlayerTwoCard()[i] = " ";
                        check_player = true;
                    }
                    break;
                case 3:
                    if(player.getPlayerThreeCard()[i].contains(played_card)){
                        player.getPlayerThreeCard()[i] = " ";
                        check_player = true;
                    }
                    break;
                case 4:
                    if(player.getPlayerFourCard()[i].contains(played_card)){
                        player.getPlayerFourCard()[i] = " ";
                        check_player = true;
                    }
                    break;
                default:
                    check_player = false;
            }
        }
        if( (check_player == true) && (check_center == true) || (check_rank == true)){

            if(check_center == true){
                playing[num] = played_card;
                num++;
            }
            else{
                playing[num] = "n";
                num++;
            }

            for(int i = 0;i<5;i++){
                if(player.getCenter()[i] == "" || player.getCenter()[i] == null){
                    player.getCenter()[i] = played_card;
                    break;
                }
            }
        }
        else{
            playing[num] = "n";
            num++;
        }
    }






    public void  getCardNumber(){
        String[] remove_suit = new String[4];
        for(int i=0;i<4;i++){
            if((playing[i] != "n")){
                remove_suit[i] = playing[i].substring(1);
                card_number[i] =  getValue(remove_suit[i]);
            }
            else{
                card_number[i] = 0;
            }
        }
    }



    public int getValue(String input){
        if(input.length() == 1){
            char character = input.charAt(0);
            int number = getNumberFromCharacter(character);
            return number;
        }
        else if(input.length() == 2 ){
            char firstChar = input.charAt(0);
            char secondChar = input.charAt(1);
            int tens = getNumberFromCharacter(firstChar);
            int ones = getNumberFromCharacter(secondChar);
            if(tens != -1 && ones != -1){
                int number = tens* 10 + ones;
                return number;
            }
        }
        return -1;
    }


    public int getNumberFromCharacter(char character){
        switch(character){
            case 'A':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case '1':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            default:
                return -1;
        }
    }


    public void determineWinner(){
        int highest_card = 0;
        int winner_index = 0;
        for(int i=0;i<4;i++){
            if(card_number[i] > highest_card){
                highest_card = card_number[i];
            }
        }
        for(int i=0;i<4;i++){
            if(highest_card == card_number[i]){
                winner_index = i;
                break;
            }
        }

        System.out.print("Center: [");
        for(int i=0;i<5;i++){
            if(player.getCenter()[i] == "" || player.getCenter()[i] == null){
            }
            else{
                System.out.print(player.getCenter()[i]+" ");
            }
        }
        System.out.println("]");

        winner = turn[winner_index];
        switch(winner){
            case 1:
                score[0] = score[0]+1;
                break;
            case 2:
                score[1]  = score[1]+1;
                break;
            case 3:
                score[2] = score[2]+1;
                break;
            case 4:
                score[3] = score[3]+1;
                break;
        }
        System.out.println("The winner of the round is player: " + winner);
        System.out.println("Score: Player1 = " + score[0] + "| Player2 = " + score[1] + " | Player3 = " + score[2] + " | Player4 = " + score[3]);
    }



    public void printInterface(){
        int trick = incrementer+1;
        System.out.println();
        System.out.println("Trick#"+ trick);
        System.out.print("Player1:  ");
        System.out.print("[");
        for(int j = 0;j<7;j++){
            if(player.getPlayerOneCard()[j] != " " && player.getPlayerOneCard()[j] != null){
                System.out.print(player.getPlayerOneCard()[j]+ " ");
            }
        }
        System.out.print("]");

        System.out.println( );
        System.out.print( "Player2: ");
        System.out.print("[");
        for(int j = 0;j<7;j++){
            if(player.getPlayerTwoCard()[j] != " " && player.getPlayerTwoCard()[j] != null){
                System.out.print(player.getPlayerTwoCard()[j]+ " ");
            }
        }
        System.out.print("]");


        System.out.println();
        System.out.print( "Player3: ");
        System.out.print("[");
        for(int j = 0;j<7;j++){
            if(player.getPlayerThreeCard()[j] != " " && player.getPlayerThreeCard()[j] != null){
                System.out.print(player.getPlayerThreeCard()[j]+ " ");
            }
        }
        System.out.print("]");

        System.out.println( );
        System.out.print("Player4: ");
        System.out.print("[");
        for(int j = 0;j<7;j++){
            if(player.getPlayerFourCard()[j] != " " && player.getPlayerFourCard()[j] != null){
                System.out.print(player.getPlayerFourCard()[j]+ " ");
            }
        }
        System.out.print("]");
        System.out.println( );

        System.out.print("Center: [");
        for(int i=0;i<5;i++){
            if(player.getCenter()[i] == "" || player.getCenter()[i] == null){
            }
            else{
                System.out.print(player.getCenter()[i]+" ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.print("Deck:[");

        for(int i= 0;i<23;i++){
            System.out.print(player.getDeck()[i]+ " ");
        }
        System.out.print("]");
        System.out.println();
        if( round == 0 && winner != 0){
            determine_player = winner;
            round = round + 1;
        }
        else{
            if(round == 0){
                determine_player = firstPlayerDetermine(player.getCenter()[0]);
            }
            else{
                determine_player = nextPlayer(determine_player);
            }
        }

        turn[incrementer] = determine_player;
        incrementer++;

        System.out.println("Score: Player1 = " + score[0] + "| Player2 = " + score[1] + " | Player3 = " + score[2] + " | Player4 = " + score[3]);
        System.out.println("Turn: Player"+ determine_player);
        String playing_card = acceptsCard();
        validateCard(playing_card,determine_player);

    }



    public void resetValues(){
        incrementer = 0;
        num = 0;
        for(int i=0;i<5;i++){
            player.getCenter()[i] = null;
            if(i < 4){
                turn[i] = 0;
                card_number[i] = 0;
            }
        }

        player.resetCards();
        reshuffleCards();
        player.assignCardToPayers();
    }

    public void display(){
        reshuffleCards();
        player.assignCardToPayers();
        for(int j=0;j<4;j++){
            printInterface();
            System.out.println( );
        }
        getCardNumber();
        determineWinner();
        resetValues();
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println( );
        System.out.println("Select one of the menu ");
        System.out.println("1.  Start a new game");
        System.out.println("2.  Exit the game");
        int  selection = sc.nextInt();
        if(selection == 1){
            display();
        }
        else if(selection == 2){
            System.exit(0);
        }
        else{
            System.exit(0);
        }
    }
    
    public static void main(String[]args){
        System.out.println( );
        System.out.print("Welcome To Go Boom Game");
        GoBoom bg = new GoBoom();
        boolean check = true;
        do{
            bg.menu();
        }
        while(check == true);

    }
}