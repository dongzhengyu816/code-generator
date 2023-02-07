package com.result;
import com.result.DFType;
import com.result.util.BitUtil;
import lombok.Data;
public class BCNAV10Decode{
    public static BCNAV10Entity getEntity(byte[] msg){
        BCNAV10Entity entity = new BCNAV10Entity();
        int offset = 0;
        entity.setPageId((int)BitUtil.bytesDecodeBit(msg,offset,BCNAV10Field.PAGEID.getBitsNumber()));
        offset += BCNAV10Field.PAGEID.getBitsNumber();
        entity.setHs((int)BitUtil.bytesDecodeBit(msg,offset,BCNAV10Field.HS.getBitsNumber()));
        offset += BCNAV10Field.HS.getBitsNumber();
        entity.setDif(BitUtil.bytesDecodeBit(msg,offset,BCNAV10Field.DIF.getBitsNumber()));
        offset += BCNAV10Field.DIF.getBitsNumber();
        return entity;
    }
}