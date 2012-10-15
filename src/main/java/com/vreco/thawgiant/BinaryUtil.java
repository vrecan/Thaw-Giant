package com.vreco.thawgiant;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Util for pulling out the binary data elements from .net BinaryWriter class.
 * @author Ben Aldrich
 */
public class BinaryUtil {

    /**
     * Retrieve and int from the binary format.
     * @param fin
     * @return
     * @throws IOException 
     */
    public static int getInt(final InputStream fin) throws IOException {
        byte[] buffer = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        if (fin.read(buffer) < 0) {
            throw new IOException("EOF");
        }
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.position(0);
        return bb.getInt();
    }

    /**
     * Convert any length of bytes to an integer.
     * @param fin
     * @param byteCount
     * @return
     * @throws IOException 
     */
    public static int getInt(final InputStream fin,final int byteCount) throws IOException {
        byte[] buffer = new byte[byteCount];
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        if (fin.read(buffer) < 0) {
            throw new IOException("EOF");
        }
        bb.position(0);
        int i = 0;
        for (byte b : bb.array()) {
            i += b;
        }
        i = i & 0xff;
        return i;
    }

    /**
     * Get epoch time from a .net Tick
     *
     * @param fin raw input stream.
     * @return
     * @throws IOException
     */
    public static long getEpochFromTick(final InputStream fin) throws IOException {
        byte[] buffer = new byte[8];
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        if (fin.read(buffer) < 0) {
            throw new IOException("EOF");
        }
        bb.position(0);

        /**
         * -621355968000000000L to convert from the different starting date in
         * .net /10000 to convert from ticks to milliseconds /1000 to convert
         * from milliseconds to seconds
         */
        return ((bb.getLong() - 621355968000000000L) / 10000) / 1000;

    }

    /**
     * Get string from binary stream. >So, if len < 0x7F, it is encoded on one
     * byte as b0 = len >if len < 0x3FFF, is is encoded on 2 bytes as b0 = (len
     * & 0x7F) | 0x80, b1 = len >> 7 >if len < 0x 1FFFFF, it is encoded on 3
     * bytes as b0 = (len & 0x7F) | 0x80, b1 = ((len >> 7) & 0x7F) | 0x80, b2 =
     * len >> 14 etc.
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String getString(final InputStream is) throws IOException {
        int val = getStringLength(is);
//        System.out.println("d:" + val);

        byte[] buffer = new byte[val];
        if (is.read(buffer) < 0) {
            throw new IOException("EOF");
        }
        return new String(buffer);
    }

    /**
     * Binary files are encoded with a variable length prefix that tells you
     * the size of the string. The prefix is encoded in a 7bit format where the
     * 8th bit tells you if you should continue. If the 8th bit is set it means
     * you need to read the next byte.
     * @param bytes
     * @return
     */
    public static int getStringLength(final InputStream is) throws IOException {
        int count = 0;
        int shift = 0;
        boolean more = true;
        while (more) {
            byte b = (byte) is.read();
            count |= (b & 0x7F) << shift;
            shift += 7;
            if((b & 0x80) == 0) {
                more = false;
            }
        }
        return count;
    }

    /**
     * Basic encoder for testing.
     *
     * @param len
     * @return
     * @throws IOException
     */
    public static byte[] encodeInt(final int len) throws IOException {
        if (len <= 0x7F) {
            byte[] b = new byte[1];
            b[0] = (byte) len;
            return b;
        } else if (len <= 0x3FFF) {
            byte[] b = new byte[2];
            b[0] = (byte) ((byte) ((byte) len & 0x7F) | 0x80);
            b[1] = (byte) (len >> 7);
            return b;
        } else if (len <= 0x1FFFFF) {
            byte[] b = new byte[3];
            b[0] = (byte) ((len & 0x7F) | 0x80);
            b[1] = (byte) ((len >> 7 & 0x7F) | 0x80);
            b[2] = (byte) (len >> 14);
            return b;
        } else {
            throw new IOException("can't get encoding for bytes");
        }
    }
}
