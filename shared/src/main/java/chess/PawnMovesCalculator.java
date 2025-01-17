package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMovesCalculator implements PieceMovesCalculator{
    private boolean checkPawnMove(ChessBoard board,
                                  ChessPosition myPosition,
                                  ChessGame.TeamColor myColor,
                                  ChessPosition nextPosition) {
        /* Check for out of bounds */
        int nextCol = nextPosition.getColumn();
        int nextRow = nextPosition.getRow();
        if (nextRow < 1 || nextRow > 8) { return false; }
        if (nextCol < 1 || nextCol > 8) { return false; }
        int myCol = myPosition.getColumn();
        ChessPiece nextPiece = board.getPiece(nextPosition);
        /* For straight ahead */
        if (myCol == nextCol) {
            int myRow = myPosition.getRow();
            if (Math.abs(myRow - nextRow) == 2) {
                /* Initial 2-space jump */
                int direction = (myColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
                ChessPosition inBetweenPosition = new ChessPosition(myRow + direction, myCol);
                ChessPiece inBetweenPiece = board.getPiece(inBetweenPosition);
                return inBetweenPiece == null && nextPiece == null;
            } else {
                return nextPiece == null;
            }
        }
        /* For capture */
        if (nextPiece == null) {
            return false;
        } else {
            return nextPiece.getTeamColor() != myColor;
        }
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int direction = (myColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int[] colDirection = {-1, 0, 1};
        /* Check for promotion */
        if ((myRow == 7 && direction == 1) || (myRow == 2 && direction == -1)) {
            for (int j : colDirection) {
                ChessPosition nextPosition = new ChessPosition(myRow + direction, myCol + j);
                if (checkPawnMove(board, position, myColor, nextPosition)) {
                    for (ChessPiece.PieceType promotionPiece : ChessPiece.PieceType.values()){
                        if (    promotionPiece != ChessPiece.PieceType.KING &&
                                promotionPiece != ChessPiece.PieceType.PAWN) {
                            moveList.add(new ChessMove(position, nextPosition, promotionPiece));
                        }
                    }
                }
            }
            return moveList;
        }
        /* Standard three spaces ahead */
        for (int j : colDirection) {
            ChessPosition nextPosition = new ChessPosition(myRow + direction, myCol + j);
            if (checkPawnMove(board, position, myColor, nextPosition)) {
                moveList.add(new ChessMove(position, nextPosition, null));
            }
        }
        /* Check for initial jump */
        if (    (myColor == ChessGame.TeamColor.WHITE && myRow == 2) ||
                (myColor == ChessGame.TeamColor.BLACK && myRow == 7)) {
            ChessPosition twoTilePosition = new ChessPosition(myRow + direction + direction, myCol);
            if (checkPawnMove(board, position, myColor, twoTilePosition)) {
                moveList.add(new ChessMove(position, twoTilePosition, null));
            }
        }
        return moveList;
    }
}
