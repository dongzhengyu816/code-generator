package com.result;
import com.result.DFType;
public enum BCNAV10Field{
    PAGEID(6,DFType.BIT),
    HS(2,DFType.BIT),
    DIF(35,DFType.BIT);
    private int bitsNumber;
    private DFType dfType;
    BCNAV10Field(int bitsNumber, DFType dfType) {
        this.bitsNumber = bitsNumber;
        this.dfType = dfType;
    }

    public DFType getDfType() {
        return dfType;
    }

    public int getBitsNumber() {
        return bitsNumber;
    }
}