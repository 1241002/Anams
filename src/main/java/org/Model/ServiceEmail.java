package org.Model;

public class ServiceEmail {

    public void sendEmail(String email, String mensagem) {
        System.out.println("--- [Simulação Email] ---");
        System.out.println("Para: " + email);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("-------------------------");
    }
}