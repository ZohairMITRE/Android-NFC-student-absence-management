# NfcProject
  
# II- PRESENTATION DU PROJET :

  En tant qu'étudiants on souhaite etre contact continu avec notre établissement, afin de suivre les actualités et l'état de notre dossier scolaire que ça soit pour le planning proposé, les notes ou meme les absences.
  Et c'est la raison pour laquelle on a décidé de mettre en place une application mobile permettant le suivie de l'absence des étudiants en se basant sur leur matricule qui sera scanné utilisant les tags NFC.
  Cette application pour récupérer chaque information en relation avec l'étudiant ou ses absences va communiquer avec une API spring qu'on a mit en place (l'architecture ci-dessous).

# III- l'ARCHITECTURE DE L'API :
<p align="center">
<img src="https://user-images.githubusercontent.com/77898496/211672393-9617d4f3-d118-4e01-ba99-80ef2ed5b5f8.png" width="50%">
</p>
# VI- SI VOUS SOUHAITEZ TESTER LE CODE :

## Il suffit de suivre les étapes suivantes :<br>

  - Cloner l'API : ``  git clone -b main https://github.com/MBDS-MARRAKECH/ProjetNFC_Groupe4.git ``
  - Ouvrez l'api appartir de n'importe quel IDE, exécuter la commande suivante `` mvn clean install -U `` et puis lancer le serveur <br>
  - Cloner le projet android : ``  git clone -b android-app https://github.com/MBDS-MARRAKECH/ProjetNFC_Groupe4.git ``
  - Changer l'@ ip au niveau des deux fichiers details du repertoire src/app/java/org.mbds.nfctag/read/details.java et src/app/java/org.mbds.nfctag/read/studentdetails.java par l @ ip de votre machine <br>

# V- SCREENSHOT DE L'APPLICATION
<p align="center">
<img src="https://user-images.githubusercontent.com/77898496/213821582-7955a360-9135-4ecb-9fba-7344655f1a96.png" width="30%">
<img src="https://user-images.githubusercontent.com/77898496/213821594-4f9f747d-1864-45e7-9b5e-505f135cba30.png" width="30%">
<img src="https://user-images.githubusercontent.com/77898496/213821619-b8041c2f-6e5b-456f-94c2-4d07bb3ccbad.png" width="30%">
</p>

# V- VIDEO ILUSTRANT L'EXECUTION

<a href="https://www.youtube.com/watch?v=SV5VvsPedho&feature=youtu.be" target="_blank"  >Click here to show the video</a>

## BONNE RECEPTION 😊 
