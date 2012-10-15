package com.vreco.thawgiant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
 * @author Ben Aldrich
 */
public class BinaryUtilTest extends TestCase {

    public BinaryUtilTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString100() throws Exception {
        System.out.println("e:100");
        String expString = "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789";
        ByteBuffer b = ByteBuffer.allocate(101);
        b.order(ByteOrder.LITTLE_ENDIAN);
        byte[] encodedBytes = BinaryUtil.encodeInt(100);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();
        assertEquals(101, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        assertEquals(expString, string);
    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString127() throws Exception {
        System.out.println("e:127");
        String expString = "0123456789001234567890012345678900123456789001234567890"
                + "0123456789001234567890012345678900123456789001234567890"
                + "01234567890012345";
        ByteBuffer b = ByteBuffer.allocate(128);
        b.order(ByteOrder.LITTLE_ENDIAN);
        byte[] encodedBytes = BinaryUtil.encodeInt(127);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();
        assertEquals(128, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        assertEquals(expString, string);
    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString128() throws Exception {
        System.out.println("e:128");
        String expString = "0123456789001234567890012345678900123456789001234567890"
                + "0123456789001234567890012345678900123456789001234567890"
                + "012345678900123456";

        ByteBuffer b = ByteBuffer.allocate(130);
        b.order(ByteOrder.LITTLE_ENDIAN);
        byte[] encodedBytes = BinaryUtil.encodeInt(128);
        b.put(encodedBytes);

        b.put(expString.getBytes());
        byte[] array = b.array();
//        System.out.println(new String(array));
        assertEquals(130, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        System.out.println("o:" + string);
        assertEquals(expString, string);

    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString254() throws Exception {
        System.out.println("e:254");
        String expString = "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "012345678901234567890123456789012345678901234567890123";

        ByteBuffer b = ByteBuffer.allocate(256);
        b.order(ByteOrder.LITTLE_ENDIAN);
//        byte prefix = 127;
        int len = 254;
        byte[] encodedBytes = BinaryUtil.encodeInt(len);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();
//        System.out.println(new String(array));
        assertEquals(256, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        System.out.println("o:" + string);
        assertEquals(expString, string);

    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString255() throws Exception {
        System.out.println("e:255");
        String expString = "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234";
        ByteBuffer b = ByteBuffer.allocate(257);
        b.order(ByteOrder.LITTLE_ENDIAN);

        int len = 255;
        byte[] encodedBytes = BinaryUtil.encodeInt(len);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();


        assertEquals(257, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        System.out.println("o:" + string);
        assertEquals(expString, string);

    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString256() throws Exception {
        System.out.println("e:256");
        String expString = "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "012345";
        ByteBuffer b = ByteBuffer.allocate(258);
        b.order(ByteOrder.LITTLE_ENDIAN);
//        byte prefix = 127;
        int len = 256;
        byte[] encodedBytes = BinaryUtil.encodeInt(len);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();
//        System.out.println(new String(array));
        assertEquals(258, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
//        System.out.println("o:" +string);
        assertEquals(expString, string);

    }

    /**
     * Test of getString method, of class BinaryUtil.
     */
    public void testGetString2560() throws Exception {
        System.out.println("e:2560");
        String expString = "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678901234"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890123456789012341234567890";

        ByteBuffer b = ByteBuffer.allocate(2562);
        b.order(ByteOrder.LITTLE_ENDIAN);
//        byte prefix = 127;
        int len = 2560;
        byte[] encodedBytes = BinaryUtil.encodeInt(len);
        b.put(encodedBytes);
        b.put(expString.getBytes());
        byte[] array = b.array();
        assertEquals(2562, array.length);


        InputStream is = new ByteArrayInputStream(array);
        String string = BinaryUtil.getString(is);
        System.out.println("o:" + string);
        assertEquals(expString, string);

    }

    /**
     * Test string length of 31k
     */
    public void testGetStringLength() throws IOException {
        int expi = 31000;
        byte[] encodeInt = BinaryUtil.encodeInt(expi);
        int stringLength = BinaryUtil.getStringLength(new ByteArrayInputStream(encodeInt));
        assertEquals(expi, stringLength);
    }
}
