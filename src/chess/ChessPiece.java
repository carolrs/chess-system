package chess;

import boadgame.Board;
import boadgame.Piece;
import boadgame.Position;

public abstract class ChessPiece extends Piece {

    private Colour colour;

    public ChessPiece(Board board,Colour colour) {
        super( board );
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }
    protected boolean isThereOpponentPiece (Position position){
        ChessPiece p=(ChessPiece) getBoard().piece(position);
        return p!= null && p.getColour() != colour;
    }

}
