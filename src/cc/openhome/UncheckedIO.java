/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cc.openhome;

import java.io.IOException;

/**
 *
 * @author Justin
 */
public interface UncheckedIO<T> {
    T run() throws IOException;
}
