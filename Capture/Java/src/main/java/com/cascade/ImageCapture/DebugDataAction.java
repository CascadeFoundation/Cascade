package com.cascade.ImageCapture;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DebugDataAction
{
    private ImageCapture imageCapture;
    private byte[] commandData;
    private int commandDataLength;

    public DebugDataAction(ImageCapture imageCapture, byte[] commandData, int commandDataLength) {
        this.imageCapture = imageCapture;
        this.commandData = commandData;
        this.commandDataLength = commandDataLength;
    }

    public void process() {
        if (commandDataLength > 0) {
            String debugText = new String(Arrays.copyOfRange(commandData, 1, commandDataLength), StandardCharsets.UTF_8);
            imageCapture.printDebugData(debugText);
        }
    }
}