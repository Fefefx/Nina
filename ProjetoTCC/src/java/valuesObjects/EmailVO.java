/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valuesObjects;

/**
 * Armazena as informações do provedor de email que será utilizado pela classe Email.
 * Também guarda os dados da mensagem que será enviada.
 * @author felipe
 */
public class EmailVO {

    private final String emailRemetente = "";
    private final String senhaRemetente = "";

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public String getSenhaRemetente() {
        return senhaRemetente;
    }

    private String emailDestinatario;
    private String assunto;
    private String msg;

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
