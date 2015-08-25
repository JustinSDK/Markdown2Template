package cc.openhome;

import java.io.IOException;

/**
 *
 * @author Justin
 */
public interface UncheckedIO<T> {
    T run() throws IOException;
}
