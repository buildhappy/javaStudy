package com.buildhappy.nio.fileOpt.bigFile;

import java.nio.ByteBuffer;
/**
 * 针对逗号分隔的任何文本格式，编写一个TextRowDecoder类。
 * 接收的参数是每行字段的数量和一个字段分隔符，返回byte的二维数组。
 * TextRowDecoder可以被操作不同字符集的特定格式解码器重复利用。
 * @author buildhappy
 *
 */
public class TextRowDecoder implements Decoder<byte[][]> {
	private static final byte LF = 10;
	private final int numFields;
	private final byte delimiter;//字符分隔符

	public TextRowDecoder(int numFields, byte delimiter) {
		this.numFields = numFields;
		this.delimiter = delimiter;
	}

	public byte[][] decode(ByteBuffer buffer) {
		
		int lineStartPos = buffer.position();
		int limit = buffer.limit();
		while (buffer.hasRemaining()) {
			byte b = buffer.get();
			if (b == LF) { // reached line feed so parse line
				
				int lineEndPos = buffer.position();
				System.out.println("lineEndPos:" + lineEndPos);
				// set positions for one row duplication
				if (buffer.limit() < lineEndPos + 1) {
					buffer.position(lineStartPos).limit(lineEndPos);
				} else {
					buffer.position(lineStartPos).limit(lineEndPos + 1);
				}
				byte[][] entry = parseRow(buffer.duplicate());
				if (entry != null) {
					// reset main buffer
					buffer.position(lineEndPos);
					buffer.limit(limit);
					// set start after LF
					lineStartPos = lineEndPos;
				}
				return entry;
			}
		}
		buffer.position(lineStartPos);
		return null;
	}

	public byte[][] parseRow(ByteBuffer buffer) {
		int fieldStartPos = buffer.position();
		int fieldEndPos = 0;
		int fieldNumber = 0;
		byte[][] fields = new byte[numFields][];
		while (buffer.hasRemaining()) {
			byte b = buffer.get();
			if (b == delimiter || b == LF) {
				fieldEndPos = buffer.position();
				// save limit
				int limit = buffer.limit();
				// set positions for one row duplication
				buffer.position(fieldStartPos).limit(fieldEndPos);
				fields[fieldNumber] = parseField(buffer.duplicate(),
						fieldNumber, fieldEndPos - fieldStartPos - 1);
				fieldNumber++;
				// reset main buffer
				buffer.position(fieldEndPos);
				buffer.limit(limit);
				// set start after LF
				fieldStartPos = fieldEndPos;
			}
			if (fieldNumber == numFields) {
				return fields;
			}
		}
		return null;
	}

	private byte[] parseField(ByteBuffer buffer, int pos, int length) {
		byte[] field = new byte[length];
		for (int i = 0; i < field.length; i++) {
			field[i] = buffer.get();
		}
		System.out.println("field:" + new String(field));
		return field;
	}
}