# l2s4-projet-2022

# Author

- Mohammed Kertiou

# Sujet/Subject

[Le sujet 2022](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2022.pdf) (Subject in french)
# Utilisation du jeu/ How to use ?
La commande ```make all```,a pour but de génerer les documentations, les compilations des fichiers java et la génération d'un fichier jar.
Si vous ne voulez pas tout généré voici les commandes possibles :
- ```make docs``` : qui a pour but de générer la documentation
- ```make cls``` : qui a pour but de compiler tout le projet
- ```make game.jar``` : qui a pour but de générer l'archive java avec le jeu complet,avec choix de la quête au hasard et choix de la taille du labyrinthe
- ```make clean``` : qui a pour but d'effacer tous les fichiers générer ci-dessus

Une fois le fichier jar acquis : lancer la commande  ```java -jar game.jar X Y Z ``` X et Y sont la taille du labyrinthe et le Z est a remplacé par le choix du labyrinthe 1 ou 2.
1 pour le Deep First Search Backtraking et 2 pour le SideWinder


The purpose of the "make all" command is to generate documentation, compile java files and generate a jar file.
If you do not want everything generated here are the possible commands:
- ``make docs``: which is intended to generate documentation
- ``make cls``: which aims to compile the whole project
- ``make game.jar``: which aims to generate the java archive with the full game, with random quest selection and choice of maze size
- ``make clean``: which aims to erase all the generated files above

Once the jar file is acquired, run the command  ``java -jar game.jar X Y Z ``X and Y are the maze size and the Z is replaced by the choice of maze 1 or 2.
1 for Deep First Search Backtraking and 2 for SideWinder
