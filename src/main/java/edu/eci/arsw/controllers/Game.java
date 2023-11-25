package edu.eci.arsw.controllers;

import edu.eci.arsw.entities.Player;
import edu.eci.arsw.model.GameMode;
import edu.eci.arsw.model.PlayerInteraction;

public class Game {

    public GameMode gameMode;
    
    public Board board;

    Player player1;
    Player player2;
    Player player3;
    Player player4;
    private static Game gameObject;
    private boolean flagBomb;
                                              
    public Game() {
    }

    

    public void orchest(GameMode gameMode){
        board = new Board();
        switch (gameMode) {
            case SINGLE_PLAYER:
                player1 = new Player(1, 1, "FixedName1", false);
                player1.setBoard(board);
                break;
        
            case MULTI_PLAYER:
                player1 = new Player(1, 1, "FixedName1", false);
                player2 = new Player(1, 10, "FixedName2", false);
                player3 = new Player(10, 1, "FixedName3", false);
                player4 = new Player(10, 10, "FixedName4", false);

                player1.setBoard(board);
                player2.setBoard(board);
                player3.setBoard(board);
                player4.setBoard(board);
                break;
        }
    }

    public Board getBoard(){
        return this.board;
    }

    public void calculate(PlayerInteraction pi){
        if (pi.getKey().equals(' ')){
            flagBomb = true;
            bombX = ;
            bombPosY = positionY;
        }

          // Esto se asegura que el ciclo del sprite sea correcto
          setCount((prevCount) => (prevCount < 0 ? prevCount + 1 : -2));

          // Esto se hace para redibujar el sprite en cada celda a la que se mueve
          currentCells[positionX][positionY] = <Cell row={positionX} column={positionY} />;
          prevAnimationX = positionX;
          positionX =
            e.key === 's'
            ? (positionX + 1)
            : e.key === 'w'
            ? (positionX - 1)
            : positionX;
          prevAnimationY = positionY;
          positionY =
            e.key === 'd'
            ? (positionY + 1)
            : e.key === 'a'
            ? (positionY - 1)
            : positionY;

          // Esta condición previene que el jugador atraviese entidades del mapa
          if (entitys[positionX][positionY] === 1){
            positionX = prevAnimationX;
            positionY = prevAnimationY;
          }

          currentCells[positionX][positionY] = <Cell row={positionX} column={positionY} content={player} />;

          if (flagBomb && (bombPosX !== positionX || bombPosY !== positionY)){
            currentCells[bombPosX][bombPosY] = <Cell row={bombPosX} column={bombPosY} content={<Bomb />} />;
            flagBomb = false;
          }

          setCellsContent([...currentCells]); // Actualiza el estado de las celdas
    }
}
