package chess;

import boadgame.Board;
import boadgame.Piece;
import boadgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch(){
        board = new Board( 8,8 );
        initialSetup();
    }
    public ChessPiece [][] getPieces(){
        ChessPiece [][] mat = new ChessPiece[board.getRow()][board.getColumn()];
        for (int i = 0; i<board.getRow(); i++){
            for(int j= 0; j<board.getColumn(); j++){
                mat[i][j] = (ChessPiece) board.piece( i, j );
            }
        }
        return mat;
    }
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
       //imprimi posicoes possiveis a partir da posição de origem
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece( position ).possibleMoves();
    }
    public ChessPiece performaceChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturePiece = makeMove(source,target);
        return (ChessPiece)capturePiece;
    }
    private Piece makeMove (Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece= board.removePiece(target);
        board.placePiece(p,target);
        return capturedPiece;
    }
    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");

        }
        if(!board.piece( position ).isThereAnyPossibleMove()){
            throw new ChessException( "There is not possible move for the chosen pieces " );
        }
    }
    private void validateTargetPosition(Position source, Position target){
        //testando se o mov (target) é possivel
        if (!board.piece( source ).possibleMove(target)){
            throw new ChessException("The piece chosen can not move to target position");
        }

    }

    private void placeNewPiece (char column,int row, ChessPiece piece){
        board.placePiece( piece,new ChessPosition( column,row ).toPosition() );
    }
    private void initialSetup (){
        placeNewPiece('c', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('c', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('d', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('e', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('e', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('d', 1, new King(board, Colour.WHITE));

        placeNewPiece('c', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('c', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('d', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('e', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('e', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('d', 8, new King(board, Colour.BLACK));
    }
}
