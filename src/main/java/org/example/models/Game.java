package org.example.models;

import org.example.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerMoveIndex;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public Game(int dimension , List<Player> players){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row<0 || row >= board.getSize() || col<0 || col >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove(Game game) throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        //move that current player wants to make
        Move move = currentPlayer.makeMove(board);

        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid Move by" + currentPlayer.getName());
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);


        Move finalMove = new Move(cellToChange,currentPlayer);

    }


}
