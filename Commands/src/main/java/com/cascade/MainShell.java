package com.cascade;

import com.cascade.Exceptions.FeatureNotAvailableException;
import com.cascade.Exceptions.ShellExitException;
import com.cascade.Exceptions.UnsupportedOperatingSystemException;
import com.cascade.Helpers.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class MainShell {
    public static HashMap<String, Boolean> flags;
    public static void main(String[] args) throws Exception {
        flags = new HashMap<>();
        try (Scanner reader = new Scanner(System.in)) {
            if (
                    OperatingSystem.getOperatingSystemType().equals(OperatingSystems.MacOS) ||
                    OperatingSystem.getOperatingSystemType().equals(OperatingSystems.Other)
            ) {
                throw new UnsupportedOperatingSystemException();
            }
            reader.useLocale(Locale.ENGLISH);
            for (String argument : args) {
                switch (argument) {
                    case "--setupFunctions" -> {
                        System.out.println();
                        switch (OperatingSystem.getOperatingSystemType()) {
                            case Linux -> {
                                ExecuteBashScriptHelper.Run("Linux/Setup Functions.sh", "");
                            }
                            case Windows, MacOS -> {
                                throw new FeatureNotAvailableException();
                            }
                            default -> throw new UnsupportedOperatingSystemException();
                        }
                        System.out.println();
                        Thread.sleep(1000);
                        ShellCommands.ClearTerminal();
                        throw new ShellExitException("Functions Installer Wizard");
                    }
                    case "--installDependenciesEnterprise" -> {
                        System.out.println();
                        switch (OperatingSystem.getOperatingSystemType()) {
                            case Linux -> {
                                ExecuteBashScriptHelper.Run("Linux/Install Dependencies Enterprise.sh", "");
                            }
                            case Windows, MacOS -> {
                                throw new FeatureNotAvailableException();
                            }
                            default -> throw new UnsupportedOperatingSystemException();
                        }
                        System.out.println();
                        Thread.sleep(1000);
                        ShellCommands.ClearTerminal();
                        throw new ShellExitException("Dependencies Installer");
                    }
                    case "--installDependenciesClient" -> {
                        System.out.println();
                        switch (OperatingSystem.getOperatingSystemType()) {
                            case Linux -> {
                                ExecuteBashScriptHelper.Run("Linux/Install Dependencies Client.sh", "");
                            }
                            case Windows, MacOS -> {
                                throw new FeatureNotAvailableException();
                            }
                            default -> throw new UnsupportedOperatingSystemException();
                        }
                        System.out.println();
                        Thread.sleep(1000);
                        ShellCommands.ClearTerminal();
                        throw new ShellExitException("Dependencies Installer");
                    }
                    case "--version" -> {
                        PrintVersionStyled.PrintVersion();
                        throw new ShellExitException("Version Flag");
                    }
                    case "-verbose" -> {
                        flags.put("Verbose", true);
                    }
                    case "-noAsciiArt" -> {
                        flags.put("ASCIIART", false);
                    }
                    case "-safe" -> {
                        flags.put("Safe Mode", true);
                    }
                    default -> {}
                }
            }
            ShellCommands.ClearTerminal();
            if (flags.get("ASCIIART") == null) {
                ASCIIArt.PrintASCIIArtOnTerminal();
            }
            while (true) {
                System.out.print("\u001B[32m" + OperatingSystem.getOperatingSystemType() + "\u001B[0m" + " \u001B[34m@\u001B[0m ");
                System.out.print(System.getProperty("user.dir"));
                System.out.print(" \u001B[31m$\u001B[0m ");
                String command = reader.nextLine();
                CommandHandler.HandleCommand(command);
            }
        } catch (Exception e) {
            System.out.println();
            if (flags.get("Verbose") != null)
                e.printStackTrace();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            System.out.println();
        }
    }
}