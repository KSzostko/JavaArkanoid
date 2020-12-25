package com.company;

import javax.swing.table.AbstractTableModel;

public class RankingAdapter extends AbstractTableModel {
    private final String[] columnNames = { "Pos.", "Username", "Score" };
    private Ranking ranking;

    public void setTableData(Ranking ranking) {
        this.ranking = ranking;
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        if(ranking == null) return 0;

        return ranking.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return rowIndex + 1;
        else if(columnIndex == 1) return ranking.getScore(rowIndex).getUsername();
        else return ranking.getScore(rowIndex).getPoints();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
