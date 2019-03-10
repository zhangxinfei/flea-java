package com.soecode.lyf.util.redis;

import java.io.*;

/**
 * Created by wxl on 2018/7/23.
 * 序列化与反序列化工具
 */
public class SerializeUtils {
    /**
     * 序列化工具
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj){
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();;
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bytes=baos.toByteArray();
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 反序列化工具
     * @param bytes
     * @return
     */
    public static Object deSerialize(byte[] bytes){
        Object obj=null;
        try {
            ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
            ObjectInputStream ois=new ObjectInputStream(bais);
            obj=ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
