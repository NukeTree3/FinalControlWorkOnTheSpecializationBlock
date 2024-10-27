package com.controllwork.javaprogramm.view;

import java.io.IOException;
import java.sql.SQLException;

public interface View {
    void start() throws IOException, ClassNotFoundException, SQLException;
    void printText(String text);
}
