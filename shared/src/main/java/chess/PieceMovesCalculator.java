package chess;

import java.util.Collection;

public interface PieceMovesCalculator {
    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);

    default boolean checkMove(ChessBoard board, ChessGame.TeamColor myColor, ChessPosition position) {
        /* Check for out of bounds */
        int row = position.getRow();
        if (row < 1 || row > 8) { return false; }
        int col = position.getColumn();
        if (col < 1 || col > 8) { return false; }
        /* Check team */
        ChessPiece current_piece = board.getPiece(position);
        return current_piece.getTeamColor() != myColor;
    }
}
