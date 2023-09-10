import java.util.*;

public class DiceGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Get players' names
        System.out.print("Enter name of 1st Player: ");
        String player1Name = sc.nextLine();
        System.out.print("Enter name of 2nd Player: ");
        String player2Name = sc.nextLine();

        Random random = new Random();
        // Roll dice for both players       
        int[] player1Dice = throwDice(random);
        int[] player2Dice = throwDice(random);
        // Display dice player 1 rolls
        System.out.println("Below are dice values for " + player1Name + " - ");
        printDice(player1Dice);
        // Find out what combination each player has (e.g., "Four of a kind", "A pair", etc.)
        String player1Combination = evaluateCombination(player1Dice);
        System.out.println("Combination for " + player1Name + " is " + player1Combination);
        // Display dice player 1 rolls
        System.out.println("\nBelow are dice values for " + player2Name + " - ");
        printDice(player2Dice);
        String player2Combination = evaluateCombination(player2Dice);
        System.out.println("Combination for " + player2Name + " is " + player2Combination);
        // Determine the winner based on their dice combination
        String winner = determineWinner(player1Dice, player2Dice, player1Combination, player2Combination, player1Name, player2Name);
        if (winner == "It's a tie") {
            System.out.println(winner);
        }
        else {
            System.out.println("\nWinner is " + winner + "..!!");
        }
    }
    // This function rolls 5 dice for the player
    public static int[] throwDice(Random random) {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }
    // this function Print the dice num
    public static void printDice(int[] dice) {
        System.out.println(Arrays.toString(dice));
    }
    // This function figures out the combination of the dice (like if they have a pair, three of a kind, etc.)
    public static String evaluateCombination(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }

        if (hasCount(counts, 5)) return "Five of a kind";
        if (hasCount(counts, 4)) return "Four of a kind";
        if (hasCount(counts, 3) && hasCount(counts, 2)) return "Three of a kind and a pair";
        if (hasCount(counts, 3)) return "Three of a kind";
        if (getNumberOfPairs(counts) == 2) return "Two pairs";
        if (hasCount(counts, 2)) return "A pair";
        return "Highest number";
    }
    //Check if a particular number count exists in the dice roll.
    private static boolean hasCount(int[] counts, int count) {
        for (int c : counts) {
            if (c == count) return true;
        }
        return false;
    }
    // Return the number of pairs in the dice roll.
    private static int getNumberOfPairs(int[] counts) {
        int pairs = 0;
        for (int c : counts) {
            if (c == 2) pairs++;
        }
        return pairs;
    }
    //Determine the winner based on their dice combination and values.
    public static String determineWinner(int[] player1Dice, int[] player2Dice, String player1Combination, String player2Combination, String player1Name, String player2Name) {
        // Order of combinations from lowest to highest
        List<String> combinations = Arrays.asList("Highest number", "A pair", "Two pairs", "Three of a kind", "Three of a kind and a pair", "Four of a kind", "Five of a kind");
        int p1Index = combinations.indexOf(player1Combination);
        int p2Index = combinations.indexOf(player2Combination);
        // Compare combinations
        if (p1Index < p2Index) {
            return player2Name;
        } else if (p2Index < p1Index) {
            return player1Name;
        } else {
            if (player1Combination.equals("A pair")) {
                int pair1Value = getPairHighestValue(player1Dice);
                int pair2Value = getPairHighestValue(player2Dice);
                if(pair1Value > pair2Value) return player1Name;
                if(pair2Value > pair1Value) return player2Name;
            } else if (player1Combination.equals("Three of a kind and a pair")) {
                int three1Value = getThreeOfAKindValue(player1Dice);
                int three2Value = getThreeOfAKindValue(player2Dice);

                if(three1Value > three2Value) return player1Name;
                if(three2Value > three1Value) return player2Name;

                int pair1Value = getPairHighestValue(player1Dice);
                int pair2Value = getPairHighestValue(player2Dice);

                if(pair1Value > pair2Value) return player1Name;
                if(pair2Value > pair1Value) return player2Name;
            } else if (player1Combination.equals("Two pairs")) {
                int[] pairs1 = getTwoPairsValues(player1Dice);
                int[] pairs2 = getTwoPairsValues(player2Dice);

                if(pairs1[0] > pairs2[0]) return player1Name;
                if(pairs2[0] > pairs1[0]) return player2Name;

                if(pairs1[1] > pairs2[1]) return player1Name;
                if(pairs2[1] > pairs1[1]) return player2Name;

                int remainingDice1 = getRemainingDiceValue(player1Dice, pairs1);
                int remainingDice2 = getRemainingDiceValue(player2Dice, pairs2);

                if(remainingDice1 > remainingDice2) return player1Name;
                if(remainingDice2 > remainingDice1) return player2Name;
            }
            if (player1Combination.equals("Three of a kind")) {
                int threeOfAKind1 = getThreeOfAKindValue(player1Dice);
                int threeOfAKind2 = getThreeOfAKindValue(player2Dice);
                
                if (threeOfAKind1 > threeOfAKind2) return player1Name;
                if (threeOfAKind2 > threeOfAKind1) return player2Name;
            }

            if (player1Combination.equals("Four of a kind")) {
                int fourOfAKind1 = getFourOfAKindValue(player1Dice);
                int fourOfAKind2 = getFourOfAKindValue(player2Dice);
                
                if (fourOfAKind1 > fourOfAKind2) return player1Name;
                if (fourOfAKind2 > fourOfAKind1) return player2Name;
            }

            int max1 = Arrays.stream(player1Dice).max().getAsInt();
            int max2 = Arrays.stream(player2Dice).max().getAsInt();
            if (max1 > max2) return player1Name;
            if (max2 > max1) return player2Name;
        }
        return "It's a tie";
    }

     // Below are helper methods to extract specific combination values from a dice roll.
    // For example, if a player has two pairs, the getTwoPairsValues method will return the values of those pairs.

    public static int getPairHighestValue(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 2) {
                return i + 1; 
            }
        }
        return -1; 
    }

    public static int getThreeOfAKindValue(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 3) {
                return i + 1; 
            }
        }
        return -1; 
    }

    public static int[] getTwoPairsValues(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }

        int[] pairs = new int[2];
        int index = 0;

        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 2) {
                pairs[index++] = i + 1;  
            }
        }

        return pairs;
    }

     public static int getFourOfAKindValue(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 4) {
                return i + 1; // +1 because array is 0-indexed and dice values are 1-indexed
            }
        }
        return -1; // should not reach here if it's confirmed that there's four of a kind
    }

    public static int getRemainingDiceValue(int[] dice, int[] pairs) {
        for (int die : dice) {
            if (die != pairs[0] && die != pairs[1]) {
                return die;
            }
        }
        return -1;  
    }
}
