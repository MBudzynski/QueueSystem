package com.example.queuesystemcore.common.util;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface FileManagement {

    String loadFile(String fileName) throws FileNotFoundException;
}
