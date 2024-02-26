package chess;

import java.util.ArrayList;

public abstract class Piece extends ReturnPiece {

    public boolean isWhite;
    public int pieceMoveCount;

    public Piece(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(null, pieceFile, pieceRank); // Calling superclass constructor
        this.pieceType = isWhite ? getWhitePieceType() : getBlackPieceType();
        this.isWhite = isWhite;
    }

    public abstract PieceType getWhitePieceType();

    public abstract PieceType getBlackPieceType();

    public abstract PieceFile getPieceFile();

    public abstract int getPieceRank();

    public abstract boolean getIsWhite();

    public abstract boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite, char promotionPiece);

    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank,
            ArrayList<ReturnPiece> piecesOnBoard) {

        int i = 0;
        while (i < piecesOnBoard.size()) {
            if (piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0)
                    && piecesOnBoard.get(i).pieceRank == takeRank) {
                piecesOnBoard.remove(i);
            } else {
                i++;
            }
        }
    }
}
