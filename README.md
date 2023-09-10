# amaap-The-Dice-Game
command-line dice game for two players.
# Problem Statement: D
command line application that randomly “throws” five dice for 2 players. Display the values and then, by observing the results, decide who wins based on the following hierarchy of Die values. Any higher combination beats a lower one; for example, five of a kind beats four of a kind.
Five of a kind (Dice values - 6,6,6,6,6)
- Four of a kind (Dice values - 6,6,6,6,2)
- Three of a kind and a pair (Dice values - 6,6,6,2,2)
- Three of a kind (Dice values - 6,6,6,1,2)
- A pair (Dice values - 6,6,1,4,2)
- Highest number (Dice values - 6,5,3,1,2)

When both players have the same combination of dice, the higher value wins. For example, two 6s beats two 5s.

Note - Display value of all dice and combination the dice qualify for both players and the winner.

Sample Output -
Enter name of 1st Player - Raju
Enter name of 2nd Player - Shyam
Below are dice values for Raju -
4, 4, 4, 3, 3
Combination for Raju is Three of a kind and a pair
Below are dice values for Shyam -
5, 5, 5, 5, 1
Combination for Shyam is Four of a kind
Winner is Shyam..!!