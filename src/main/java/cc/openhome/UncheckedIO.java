package cc.openhome;

import java.io.IOException;

public interface UncheckedIO<T> {
    T run() throws IOException;
}
