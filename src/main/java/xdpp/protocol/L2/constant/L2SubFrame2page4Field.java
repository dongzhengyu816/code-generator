package xdpp.protocol.L2.constant;
import java.util.HashMap;

public enum L2SubFrame2page4Field{
    PAGEID(true,1.1,15.4545,"pageId",Math.pow(2,-4),6,DFType.BIT),
    HS(false,3.1212,154545,"hs",Math.pow(1,0),10,DFType.INTS),
    DIF(false,3.1212,154545,"dif",Math.pow(10,-1),40,DFType.BIT);
    private boolean isExistRange;
    private double rangeMinNumber;
    private double rangeMaxNumber;
    private String fieldKey;
    private double radio;
    private int bitsNumber;
    private DFType dfType;

    L2SubFrame2page4Field(boolean isExistRange,double rangeMinNumber, double rangeMaxNumber,String fieldKey,double radio,int bitsNumber, DFType dfType) {
        this.isExistRange = isExistRange;
        this.rangeMinNumber = rangeMinNumber;
        this.rangeMaxNumber = rangeMaxNumber;
        this.fieldKey = fieldKey;
        this.radio = radio;
        this.bitsNumber = bitsNumber;
        this.dfType = dfType;
    }

    public static HashMap<String,L2SubFrame2page4Field>  dataFieldMap = new HashMap<>();
    static {
        for (L2SubFrame2page4Field dataField : L2SubFrame2page4Field.values()){
            dataFieldMap.put(dataField.getFieldKey(),dataField);
        }
    }

    public double getRangeMinNumber() {
        return rangeMinNumber;
    }

    public double getRangeMaxNumber() {
        return rangeMaxNumber;
    }

    public boolean isExistRange() {
        return isExistRange;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public double getRadio() {
        return radio;
    }

    public DFType getDfType() {
        return dfType;
    }

    public int getBitsNumber() {
        return bitsNumber;
    }
}