package com.buildhappy.nio.fileOpt.bigFile;

import java.nio.ByteBuffer;

public interface Decoder<T> {
       public T decode(ByteBuffer buffer);
}