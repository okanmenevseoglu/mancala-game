# Mancala Game

This is a recreation of a game called Mancala with Java/Spring Boot and Angular.

To read more about the game, visit https://en.wikipedia.org/wiki/Mancala.

## 1. Game Rules

The PDF version of the rules can be found under:
```{PROJECT_ROOT_DIR}/TechAssignment_2021.pdf```

### Board Setup

Each of the two players has his six pits in front of him. To the right of the six pits, each player has a larger pit. At
the start of the game, there are six stones in each of the six round pits .

### Game Play

The player who begins with the first move picks up all the stones in any of his own six pits, and sows the stones on to
the right, one in each of the following pits, including his own big pit. No stones are put in the opponents' big pit. If
the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's
the other player's turn.

### Capturing Stones

During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player
captures his own stone and all stones in the opposite pit (the other player’s pit) and puts them in his own
(big or little?) pit.

### The Game Ends

The game is over as soon as one of the sides runs out of stones. The player who still has stones in his pits keeps them
and puts them in his big pit. The winner of the game is the player who has the most stones in his big pit.

## 2. Dependencies (Tools & Libraries)

There are several tools and libraries that are used by the project:

### Backend Dependencies

* Java 15 - As the core language.
* Spring Boot 2.4.5 - For building the REST API.
* Lombok - For reducing the boilerplate code.
* Springfox Swagger for Spring Boot 3.0.0 - For creating API documentation.
* Maven 3.8.1 - As a dependency manager.

### Frontend Dependencies

* HTML/CSS/Boostrap 5 - For creating the UI.
* Angular v12 - As a JavaScript framework.

### Common Dependencies

* Docker/Docker Compose - For building and running the applications in containers.

### Tests

The project has 39 unit and integration tests and 100% class, 90% method coverage written with JUnit 5, Mockito and
AssertJ.

## 3. How to run?

Running the project is fairly simple. After installing the Docker and Docker Compose on the local environment, the
following can be run under ```{PROJECT_ROOT_DIR}```:

* ```start.sh``` to start

* ```stop.sh``` to stop the application .

Alternatively,

* ```docker-compose up -d``` to start

* ```docker-compose down``` to stop the application.

### Running the Backend Locally (Optional)

The backend of the project is a Java 15/Spring Boot/Maven project. To be able to start it you should install at least
Java 15 and Maven 3.8.1. After the installation run the following command
under ```{PROJECT_ROOT_DIR}/mancala-api```:

* ```mvn clean install spring-boot:run```

to run all tests and start the application on the ```localhost:8082```.

**Important Note:** Make sure nothing is running under 8082 or change the configuration.

### Running the Frontend Locally (Optional)

The frontend of the project is an Angular project. To be able to run it you should install Node 14 or later along with
NPM. After the installation run the following command under ```{PROJECT_ROOT_DIR}/mancala-ui```:

* ```ng serve```

to start the application on the ```localhost:4200```.

**Important Note:** Make sure nothing is running under 4200 or change the configuration.

## 4. How to play?

### Playing from the UI

The game is very straight forward. After running the application, the steps are as the following:

1. Visit ```localhost:4200``` to access the UI.
2. Click on ```New Game``` to start a new game.
3. Click on the pits of the current player to sow the stones.
4. If the game is over, winner message will be displayed.

### Playing from the API

The steps to play from the API are also similar to the UI. After running the application;

1. Send a get request to ```localhost:8082/game/new``` to create a new board.
2. Send a post request to ```localhost:8082/game/sow``` with a ```pitIndex``` from 0 to 13. The game board design is a
   counter-clockwise array. 0-6 indexed pits belong to Player 1 where 6 is the home pit of Player 1. 7-13 indexed pits
   belong to Player 2 where 13 is the home pit of Player 2.
3. Send a get request to ```localhost:8082/game/board``` to get the current board status.

## 5. Swagger

More information about the APIs are documented in the Swagger. It can be accessed from:
```http://localhost:8082/swagger-ui/```