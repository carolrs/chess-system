package chess;

import boadgame.Board;
import boadgame.Piece;
import boadgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private int turn;
    private Colour currentPlayer;
    private Board board;

    public ChessMatch(){
        board = new Board( 8,8 );
        turn = 1;
        currentPlayer = Colour.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }


    public Colour getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece [][] getPieces(){
        ChessPiece [][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i<board.getRows(); i++){
            for(int j= 0; j<board.getColumns(); j++){
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
        nextTurn();
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
        if (currentPlayer != ((ChessPiece)board.piece( position )).getColour ()){
            //downcast para acessar o Colour. get colour chesspiece.
            //ira testar se a cor atual é diferente do "currentplayer" = peça do advers.
            throw new ChessException("The chosen piece is not yours.");
        }
    }
    private void validateTargetPosition(Position source, Position target){
        //testando se o mov (target) é possivel
        if (!board.piece( source ).possibleMove(target)){
            throw new ChessException("The piece chosen can not move to target position");
        }
    }
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Colour.WHITE)?Colour.BLACK : Colour.WHITE;
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
