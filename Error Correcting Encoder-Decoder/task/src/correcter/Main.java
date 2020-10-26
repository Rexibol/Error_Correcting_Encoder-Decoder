package correcter;

import java.io.*;
import java.util.Scanner;

public class Main {


    //TODO: three modes: encode, send and decode

    /**
     * At first we should double each byte.
     * encode =
     */
    public static void main(String[] args) throws IOException {
        String sendFilePath = "C:\\Users\\Konrad\\IdeaProjects\\Error Correcting Encoder-Decoder\\Error Correcting Encoder-Decoder\\task\\src\\correcter\\send.txt";
        // sendFilePath = "send.txt";
        String encodedFilePath = new String("C:\\Users\\Konrad\\IdeaProjects\\Error Correcting Encoder-Decoder\\Error Correcting Encoder-Decoder\\task\\src\\correcter\\encoded.txt");
        // encodedFilePath = "encoded.txt";
        String receivedFilePath = new String("C:\\Users\\Konrad\\IdeaProjects\\Error Correcting Encoder-Decoder\\Error Correcting Encoder-Decoder\\task\\src\\correcter\\received.txt");


        Correcter correcter = new Correcter(sendFilePath, encodedFilePath, receivedFilePath);
        correcter.readFileContent();

        System.out.print("Write a mode: ");
        Scanner scanner = new Scanner(System.in);
        correcter.performOperation(scanner.nextLine());
        correcter.saveEncoded();


    }
}

class Correcter {

    private static String SEPARATOR = " ";

    private String inputFilePath;
    private String outputFilePath;
    private String receivedFilePath;
    private byte[] bytes;

    enum Mode {
        ENCODE, SEND, DECODE;
    }

    public Correcter(String inputFilePath, String outputFilePath, String receivedFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.receivedFilePath = receivedFilePath;
    }


    public void performOperation(String input) {
        Mode mode = Mode.valueOf(input.toUpperCase());
        switch (mode) {
            case ENCODE:
                System.out.println(inputFilePath + ":");
                System.out.println("hex view: " + getHexView());
                System.out.println("bin view: " + getBinaryView());
                System.out.println("expand: " + expandBinary());
                System.out.println("parity: " + parity());
                break;
            case SEND:
                break;
            case DECODE:
                break;
        }
    }


    private String getHexView() {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString(b)).append(SEPARATOR);
        }
        return sb.toString().trim();
    }

    private String getBinaryView() {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
            sb.append(SEPARATOR);
        }
        return sb.toString().trim();
    }

    private String expandBinary() {
        String[] binaryView = getBinaryView().split(SEPARATOR);
        for (int i = 0; i < binaryView.length; i++) {
            StringBuilder doubledBinary = new StringBuilder();
            for (String s : binaryView[i].split("")) {
                doubledBinary.append(s).append(s);
            }
            binaryView[i] = doubledBinary.toString();
        }
        return format(binaryView);
    }


    private String parity() {
        String parity = expandBinary().replace(SEPARATOR, "");
        StringBuilder sb = new StringBuilder();
        int parityNumber = 0;
        int parityCounter = 0;
        String chunk = new String();
        for (int i = 2; i <= parity.length(); i += 2) {
            chunk = parity.substring(i - 2, i);
            sb.append(chunk);
            parityNumber += Integer.parseInt(parity.substring(i - 2, i - 1));
            parityCounter++;
            if ((parityCounter % 3) == 0) {
                parityCounter = 0;
                sb.append(resolveParityBit(parityNumber));
                sb.append(SEPARATOR);
                parityNumber = 0;
            }
        }

        //handle if there is no full byte i.e bits less than 8, for example only 2 bits
        if (parityCounter % 4 != 0) {
            for (int i = parityCounter; i < 3; i++) {
                sb.append("00");
            }
            sb.append(resolveParityBit(parityNumber));
        }
        return sb.toString().trim();
    }

    private String resolveParityBit(int parityNumber) {
        int result = parityNumber % 2;
        return String.valueOf(result) + String.valueOf(result);
    }

    private String format(String[] binaryView) {
        StringBuilder result = new StringBuilder();
        for (String s : binaryView) {
            result.append(s.substring(0, s.length() / 2));
            result.append(SEPARATOR);
            result.append(s.substring(s.length() / 2));
            result.append(SEPARATOR);
        }
        return result.toString().trim();
    }

    public void readFileContent() {
        try (InputStream inputStream = new FileInputStream(inputFilePath)) {
            bytes = inputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String content) {
        // outputStream.write((byte) Integer.parseInt(str, 2)),
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEncoded() {
        //writeToFile(parity());
        saveEncodedFile();
    }

    public void saveEncodedFile() {
        try (OutputStream writer = new FileOutputStream(outputFilePath, false)) {
            for (String s : parity().split(SEPARATOR)) {
                byte b = (byte) Integer.parseInt(s);
                System.out.print(b + " ");
                writer.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

