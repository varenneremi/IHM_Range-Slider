# TP2_IHM2_Polytech
Souchon Loïc 
Varenne Rémi

## TP2 IHM Avancée - INFO5 Polytech - 2019-2020

But du TP : Implémenter un menu circulaire avec des techniques d'interaction spécifiques : le marking menu.

**Deadline de remise du TP :** 15 Novembre 2019 à *10h*

**Modalités de remise du TP :** Partagez le dépôt Git de votre groupe avec les enseignants pour que nous puissions accéder à votre travail. Nous récupérerons vos projets directement sur ces dépôts juste après la deadline. 
Pensez à ajouter un Readme dans votre dépôt avec le nom des membres du groupe et une courte explication de ce que vous avez effectué : quels sont vos choix de conceptions, quels éléments ont été implémentés, quelles touches utiliser pour interagir avec votre solution, etc. Aucun rapport de TP n'est attendu.

**Choix de conceptions :** On utilise deux panel, un pour le dessin des formes et un pour le rendu du marking menu.  
Pour l'architecture du marking menu, nous avons choisi une architecture MVC. La vue s'occupe de l'affichage du panel du menu ainsi que des listeners. Les events sont transmis au controller qui s'occupe de mettre à jour le modele en fonction des events.  
Concernant l'organisation des menus, on a cree une classe Menu qui est étendue à SubMenu et à Tool. La classe Tool est une classe abstraire qui permet d'être étendue aux différents outils existants tels que les outils pour la couleurs et les outils pour la formes. Le panel où sont dessinées les formes est visible même quand on utilise le marking menu car ce dernier n'est pas opaque. Le nombre de bloc dans le menu s'adapte au nombre de menu selectionnable.  
Nous avons ajouté la possibilite de revenir en arriere dans le menu. Ainsi qu'un bouton permettant de selectionner le mode expert qui n'affiche plus le menu.

**Éléments implémentés et touches à utiliser:** Pour utiliser le marking menu, il faut presser le bouton droit de la souris. A partir de là, plusieurs solutions s'offrent à vous. Soit vous relachez le bouton et aucune modification n'est faite. Si vous vous décalez mais restez dans le cercle et que vous relachez, même résultat. A partir du moment ou vous allez vous décalez en dehors du cercle, cela va mettre à jour le marking menu, et s'il n'y a pas de sous menu, mettre à jour la couleur ou la forme en fonction.  
Etant donné que nous sommes sur un menu complètement cirulaire et non avec des rectangles, sortir du cercle fait changer la couleur ou la forme. On ne peut donc pas ajouter des fonctionnalités en dessous du cercle mais, en compensation, on ne limite pas à 8 le nombre de fonctionalités sur le menu circulaire. 
