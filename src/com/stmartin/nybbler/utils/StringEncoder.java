package com.stmartin.nybbler.utils;

//import java.io.ByteArrayOutputStream;

public class StringEncoder {
	public static byte[] encode(String s) {
		/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for(char c : s.toCharArray()) {
			if(0 < c && c < 0x80) {
				bos.write(c & 0b00111111);
			}
			else if(c == 0 || (c >= 0x80 && c < 0x800)) {
				bos.write(0b11000000 | ((c >> 6) & 0x1f));
				bos.write(0b10000000 | (c & 0x3f));
			}
			else if(c >= 0x800 && c < 0x10000) {
				bos.write(0b11100000 | ((c >> 12) & 0xf));
				bos.write(0b10000000 | ((c >> 6) & 0x3f));
				bos.write(0b10000000 | (c & 0x3f));
			}
			else {
				bos.write(0b11101101);
				bos.write(0b10100000 | (((c >> 16) - 1) & 0xf));
				bos.write(0b10000000 | ((c >> 10) & 0x3f));
				bos.write(0b11101101);
				bos.write(0b10110000 | ((c >> 6) & 0xf));
				bos.write(0b10000000 | (c & 0x3f));
			}
		}
		return bos.toByteArray();*/
		return s.getBytes();
	}
}
