package javatuning.ch3.nio;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class TestMapBuffer {

    protected static int numOfInts = 4000000;

    @Test
    public void testStreamWrite() throws IOException {
        long begTime = System.currentTimeMillis();
        //DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("./data/temp.tmp")), 16 * 1024 * 1024));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("./data/temp_stream.tmp"))));
        for (int i = 0; i < numOfInts; i++) {
            dos.writeInt(i);
        }
        if (dos != null) {
            dos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStreamWrite:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStreamRead() throws IOException {
        long begTime = System.currentTimeMillis();
        //DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("./data/temp.tmp")), 16 * 1024 * 1024));
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("./data/temp_stream.tmp"))));
        for (int i = 0; i < numOfInts; i++) {
            dis.readInt();
        }
        if (dis != null) {
            dis.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStreamRead:" + (endTime - begTime) + "ms");
    }


    @Test
    public void testMappedWrite() throws IOException {
        long begTime = System.currentTimeMillis();
        FileChannel fc = new RandomAccessFile("./data/temp_mapped.tmp", "rw").getChannel();
        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts * 4).asIntBuffer();
        for (int i = 0; i < numOfInts; i++) {
            ib.put(i);
        }
        if (fc != null) {
            fc.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testMappedWrite:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testMappedRead() throws IOException {
        long begTime = System.currentTimeMillis();
        FileChannel fc = new FileInputStream("./data/temp_mapped.tmp").getChannel();
        IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
        while (ib.hasRemaining()) {
            ib.get();
        }
        if (fc != null) {
            fc.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testMappedRead:" + (endTime - begTime) + "ms");
    }

}
