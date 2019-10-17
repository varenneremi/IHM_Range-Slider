# IHM_Range-Slider
VARENNE Rémi & SOUCHON Loic

En nous inspirant du modèle du BoundedRangeModel, nous avons fait notre rangeSlider avec un modèle MVC. 

Nous avond fonc créer une interface nommée IRangeSliderModel qui est ensuite implémentée dans la classe qui nous sert de Model RangeSliderModelImpl. Nous retrouverons dans cette classe les modifications du modèle faite par le controller RangeSlider. 

Dans cette classe là seront fait les changements sur le modèle en fonction des informations données par les listeners se trouvant dans la vue RangeSliderUI. 

Les modifications du modèle se repercuteront sur la modification de la vue.


Pour le rangeSlider, nous avons choisi de créer un modèle qui ressemble ce que nous trouvont facilement sur les interfaces que nous utilisons. 

Il existe 2 possibilités, soit on clique sur un des deux boutons, soit on clique sur la barre. 
Si on clique sur la barre, ce sera le bouton se trouvant le plus près qui se deplacera sur ce point là. 
Si on clique sur un des deux boutons, il suffira de glisser vers la gauche ou vers la droite pour le déplacer. 
À noter qu'il sera pas possible de le déplacer plus loin que l'autre bouton, le bouton gauche restera tout le temps le bouton gauche. 
