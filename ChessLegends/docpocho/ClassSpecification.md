# Especificació de classes

## Problem

Classe per representar un problema. Permet la visualització i modificació de les dades, la verificació del mateix i la gestió del ranking de jugadors d'aquell problema.

## GameAbs

Classe per gestionar un joc. Permet veure les peces qeu té la taula i moure-les.

### Game

Classe per gestionar un joc on hi juga minim una persona. Aquesta té en compte el temps de decissió per fer les rankings dels problemes.

### AICompetition

Classe per gestionar pertides entre les IAs. Es juguen K partides i despres es poden adquirir els resultats, nombre de partides gunyades per cada color.

## Player

Classe per representar els jugadors (usuaris). Guarda l'inforamció necessaria per representar un usuari i permet calcular el seu ELO.

### Human

Classe per representar un jugador humà. Aquest permet guardar una password per autenticar-se.

### AI

Classe que conte l'algoritme del jugador maquina.

#### AI1

Classe amb l'algorisme de IA de dificultat fàcil.

## Board

Classe per representar un taulell d'escacs i gestiolar-lo. Permet moure i afegir peces i codificar el contingut de la taula en codificació fen.

## Piece

Classe per representar una peça. Dona informació dels possibles moviments que pot fer.

Té una subclasse per cada tipus de peça i una per representar una peça buida.
Totes les subclasses utilitzen el seu propi moviment per calcular els possibles posicions.

###King

Classe que representa la peça Rey.

###Pawn

Classe que representa la peça Peo
.
###Queen

Classe que representa la peça Reina.

###Rock

Classe que representa la peça Torre.

###Bishop

Classe que representa la peça Alfil.

###nullPiece

Classe que representa una peça buida.

###Knight

Classe que representa la peça Cavall

##CtrlDomainCreator

Controladora que s'encarrega de crear instancies 

##CtrlDomainGame

Controladora que maneja el Game

##chessException

Classe derivada d'Exception la que utilitzem per llançar les nostres propies excepcions personalitzades
