package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo de notificação:");
        System.out.println("1: Email");
        System.out.println("2: SMS");
        System.out.println("3: Push Notification");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String type = "";
        switch (choice) {
            case 1:
                type = "email";
                break;
            case 2:
                type = "sms";
                break;
            case 3:
                type = "push";
                break;
            default:
                System.out.println("Opção inválida.");
                scanner.close();
                return;
        }

        System.out.print("Digite a mensagem: ");
        String message = scanner.nextLine();

        Notification notification = NotificationFactory.createNotification(type);

        notification.send(message);

        scanner.close();
    }
}