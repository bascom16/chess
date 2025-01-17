package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int[] twoTileShift = {-2, 2};
        int[] oneTileShift = {-1, 1};
        for (int i : twoTileShift) {
            for (int j : oneTileShift) {
                ChessPosition nextPosition = new ChessPosition(myRow + i, myCol + j);
                if (checkMove(board, myColor, nextPosition)) {
                    moveList.add(new ChessMove(position, nextPosition, null));
                }
            }
        }
        for (int i : oneTileShift) {
            for (int j : twoTileShift) {
                ChessPosition nextPosition = new ChessPosition(myRow + i, myCol + j);
                if (checkMove(board, myColor, nextPosition)) {
                    moveList.add(new ChessMove(position, nextPosition, null));
                }
            }
        }
        return moveList;
    }
}
