# Board Game Organizer | Read Me File!
### This project includes the following files: </br>
#### Java Files:
- [Game.java](src/main/java/com/teamproject/project/Game.java)
- [Database.java](src/main/java/com/teamproject/project/Database.java)
- [ProjectMain.java](src/main/java/com/teamproject/project/ProjectMain.java)
- [createGameController.java](src/main/java/com/teamproject/project/createGameController.java)
- [deleteGameController.java](src/main/java/com/teamproject/project/deleteGameController.java)
- [menuController.java](src/main/java/com/teamproject/project/menuController.java)
- [modifyGameController.java](src/main/java/com/teamproject/project/modifyGameController.java)
- [viewGamesController.java](src/main/java/com/teamproject/project/viewGamesController.java)

#### FXML Files:
- [addNewGame.fxml](src/main/resources/com/teamproject/project/addNewGame.fxml)
- [deleteGame.fxml](src/main/resources/com/teamproject/project/deleteGame.fxml)
- [menu.fxml](src/main/resources/com/teamproject/project/menu.fxml)
- [modifyGame.fxml](src/main/resources/com/teamproject/project/modifyGame.fxml)
- [viewGames.fxml](src/main/resources/com/teamproject/project/viewGames.fxml)

#### SQL and DB Files
- [database.sql](db/database.sql)
- [database.db](database.db)
- [database.sqbpro](database.sqbpro) (to access through DB Browser)

---
### Instructions to run the file:
1. Open the [Project]() in IntelliJ
2. Run the main File: [ProjectMain.java](src/main/java/com/teamproject/project/ProjectMain.java)
---
### Instructions to Add a Game to the DataBase:
1. After running the [ProjectMain.java](src/main/java/com/teamproject/project/ProjectMain.java) file you will be welcomed by a menu.
2. Click the "Add a Game" button
3. Enter the relevant Info (Game Name, Year Published, Minutes Played, etc)
4. After entering all the info, click the "Add Game" button to add the game to the DataBase
5. Exit the window (if you don't have any more Games to add)
---
### Instructions to View the Games in the DataBase:
1. After running the [ProjectMain.java](src/main/java/com/teamproject/project/ProjectMain.java) file you will be welcomed by a menu.
2. Click the "Display Games" button
3. View the Table containing all the Database contents.
---
### Instructions to Modify a Game's data/info:
1. In the Display Window, click the Game you want to modify from the Table
2. Click the "Modify Selected" Button
3. In the new Window, modify all the info that you want to change (ID is unchangable)
4. Exit the "modify a game" window
---
### Instructions to Delete a Game from the DataBase
1. In the menu you get after running the main file ([ProjectMain.java](src/main/java/com/teamproject/project/ProjectMain.java)), Click the "Delete Stats" Button
2. In the List Select the Game you want to delete.
3. click the "Delete selected" button
4. The table will auto update to the current database content (after deletion)
