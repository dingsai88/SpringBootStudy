package com.ding.study.jvm;

/**
 * @author daniel 2019-3-28 0028.
 */
public class P292ClassModifier {
    /**
     * class文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * constant_tuf8 info 常量的tag标志
     */
    private static final int CONSTANT_UTF8_INFO = 1;

    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1=1;
    private static final int u2=2;
    private  byte[] classByte;

    public P292ClassModifier(byte[] classByte){
        this.classByte=classByte;
    }

    public byte[] modifyUTF8Constant(String oldStr,String newStr){
        int cpc=getConstantPollCount();
        int offset=CONSTANT_POOL_COUNT_INDEX+u2;
        for(int i=0;i<cpc;i++){
            int tag=P294ByteUtils.bytes2Int(classByte,offset,u1);
            if(tag==CONSTANT_UTF8_INFO){
                int len=P294ByteUtils.bytes2Int(classByte,offset+u1,u2);
                offset+=(u1+u2);
                String str=P294ByteUtils.bytes2String(classByte,offset,len);
                if(str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes=P294ByteUtils.string2Bytes(newStr);
                    byte[] strLen=P294ByteUtils.int2Bytes(newStr.length(),u2);
                    classByte=P294ByteUtils.bytesReplace(classByte,offset-u2,u2,strLen);
                    return classByte;
                }else {
                    offset+=len;
                }
            }else {
                offset+=CONSTANT_ITEM_LENGTH[tag];
            }
        }

        return classByte;
    }


    public  int getConstantPollCount(){
        return P294ByteUtils.bytes2Int(classByte,CONSTANT_POOL_COUNT_INDEX,u2);
    }

}
