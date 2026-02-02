import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class DataHandler {
    static BigDecimal loadBalance() {
        File balanceFile = new File("balance.txt");
        try (Scanner scanner = new Scanner(balanceFile)) {
            String content = scanner.nextLine();
            return BigDecimal.valueOf(Double.parseDouble(content));
        } catch (IOException e) {
            System.out.println("Could not find balance file.");
        }
        return new BigDecimal(0);
    }
    static void saveBalance(BigDecimal balance) {
        try (FileWriter balanceFile = new FileWriter("balance.txt")) {
            balanceFile.write(String.valueOf(balance.doubleValue()));
        } catch (Exception exception) {
            System.out.println("An error occurred during depositing");
        }
    }
}
