package chess.pieces;

import boadgame.Board;
import chess.ChessPiece;
import chess.Colour;

public class King extends ChessPiece {

    public King(Board board, Colour colour) {
        super( board, colour );
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat= new boolean[getBoard().getRow()][getBoard().getColumn()];
        return mat;
    }
}
