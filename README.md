# Application of sample online game for players stats and ranking analysis

*Project developed as a part of Large-scale Enterprise Applications course at 6th semester of Data Engineering studies.*
<br><br>

## How to run the application?
The application can be compiled from IDE, or from the command line by locating yourself in the „src” folder, running "javac com/domin/*java" command, followed by "java com/domin/OnlineGameApp" or „java com/domin/Test” deciding which main class you want to execute.
<br><br>

## Description and basic assumptions
The idea behind the game is that Player is limited to single Hero, which he choses at the beginning of the game. Every Hero has specific unique limitations. Player collects items throughout the game, which give his hero specified bonuses. His items can level up, which increases the bonus. <br>

Player is able to duel with other players, gaining score and possibility to level up his items. <br>
Score of the player is calculated as a balance of won and lost duels, multiplied by numer of duels player took part in to enlarge the impact of amount of duels played and the difference between them, obtained result is increased by hero’s damage, armour, attack range and attack speed points, and finally the numer of won duels is multiplied by 100 and added to give the final score. <br>

Scoring system is slightly different in case Player, that hasn’t played any duels yet, but in general it is univeral, but can change later on during the development process. <br>
Player’s score will be used to calculate and create ranking in the further process of app development. 
<br><br>

## Application architecture 

The application consists of several classes which are: 
- Player – class containing fields describing single player of the game, containing method used to 
display information about the player, as well as necessary getters and setters, 
- Hero – abstract class containing common fields of all the specific heroes which are Mage, Hunter 
and Warrior; consists of methods handling changes in the inventory such as adding and removing 
items, as well as changing the Damage, Armour etc. fields values afterwards, 
- Mage – class of Hero parent class; specific Hero having individual attribute among other heroes 
which is inventory capacity 
- Hunter – class of Hero parent class; specific Hero having individual attribute among other heroes 
which is maximal attack range, which in case exceeds given level is not increased anymore, but stays 
at the same value equal to the specified maximum 
- Warrior – class of Hero parent class; specific Hero having individual attribute among other heroes 
which is maximal damage, which in case exceeds given level is not increased anymore, but stays at 
the same value equal to the specified maximum 
- Item – class cointaining fields describing singe item being part of the Hero’s inventory, each item 
has unique name, weight which impacts hero’s attack speed (the heavier inventory, the lower the 
attack speed) and bonuses given to Damage, Armour or Attack Range; every Item has also its level, 
by which the bonus is multiplied 
- Statistics – class containing fields describing Player’s won and lost duels, as well as calculated 
overall score 
- Test – main class used for testing, where sample data is initialized, testing implemented methods 
- OnlineGameApp – main class where all the logic of the app will be located, building up the project in the further laboratories 

