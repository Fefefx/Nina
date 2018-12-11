/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import valuesObjects.EmailVO;

/**
 * Envia emails para o cliente utilizando das informações de provedor configuradas na classe EmailVO. 
 * @author felipe
 */
public class Email {

    /**
     * Envia um email utilizando o provedor Outlook/Hotmail. Configura o protolo
     * smtp e a porta de envio, estabele uma conexão com o provedor de email,
     * compõem a mensagem e a envia para o destinatário, retorna um booleano
     * indicando o sucesso da operação.
     *
     * @author Marcelo Siedler (alterado por Felipe Mantoan Pardim)
     * @param dados O email que deve ser enviado
     * @throws MessagingException Erro ao enviar o email
     * @return boolean - Se o email foi enviado ou não
     */
    public static boolean enviarHotmail(EmailVO dados) {
        boolean retorno = false;
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(dados.getEmailRemetente(), dados.getSenhaRemetente());
            }
        });
        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(dados.getEmailRemetente())); //Remetente
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(dados.getEmailDestinatario())); //Destinatário(s)
            message.setSubject(dados.getAssunto());//Assunto
            message.setText(dados.getMsg());
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
            System.out.println("Feito!!!");
            retorno = true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return retorno;

    }

}
