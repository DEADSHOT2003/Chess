package chess;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WQ;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BQ;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    @Override
    public PieceFile getPieceFile() {
        return pieceFile;
    }

    @Override
    public int getPieceRank() {
        return pieceRank;
    }

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite, char promotionPiece) {

        if (newRank == pieceRank && newFile == pieceFile) {
            return false;
        }

        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

        int rankDirection = Integer.compare(newRank, pieceRank);
        int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());

        if (rankDifference == 0 || fileDifference == 0 || rankDifference == fileDifference) {
            int currentRank = pieceRank + rankDirection;
            PieceFile currentFile = PieceFile.values()[pieceFile.ordinal() + fileDirection];

            while (currentRank != newRank || currentFile != newFile) {
                int i = 0;
                while (i < piecesOnBoard.size()) {
                    if (piecesOnBoard.get(i).pieceRank == currentRank && piecesOnBoard.get(i).pieceFile == currentFile) {
                        return false;
                    }
                    i++;
                }
                currentRank += rankDirection;
                currentFile = PieceFile.values()[currentFile.ordinal() + fileDirection];
            }

            int j = 0;
            while (j < piecesOnBoard.size()) {
                if (piecesOnBoard.get(j).pieceRank == newRank && piecesOnBoard.get(j).pieceFile == newFile) {
                    if (piecesOnBoard.get(j).pieceType.toString().charAt(0) != pieceType.toString().charAt(0)) {
                        if (piecesOnBoard.get(j).pieceType.toString().charAt(0) == 'W' && !playerWhite) {
                            if (piecesOnBoard.get(j).pieceType.toString().charAt(1) != 'K') {
                                capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                            }
                            return true;
                        } else {
                            if (piecesOnBoard.get(j).pieceType.toString().charAt(0) == 'B' && playerWhite) {
                                if (piecesOnBoard.get(j).pieceType.toString().charAt(1) != 'K') {
                                    capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                                }
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                    return false;
                }
                j++;
            }
            return true;
        }
        return false;
    }
}
