package chess.pieces;

import boadgame.Board;
import boadgame.Position;
import chess.ChessPiece;
import chess.Colour;

public class Rook extends ChessPiece {

    public Rook(Board board, Colour colour) {
        super( board, colour );
    }

    @Override
    public String toString() {
        return "R";

    }
    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat= new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow()-1,position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //left
        p.setValues(position.getRow(),position.getColumn() -1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setColumn(p.getColumn()-1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //right
        p.setValues(position.getRow(),position.getColumn() +1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setColumn(p.getColumn()+1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //bellow
        p.setValues(position.getRow()+1,position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }



        return mat;
    }
}
