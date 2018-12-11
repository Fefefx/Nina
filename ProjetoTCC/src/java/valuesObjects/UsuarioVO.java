/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valuesObjects;

/**
 * Armazena as informações da entidade Usuário do banco de dados.
 * @author felipe
 */
public class UsuarioVO {

    private String nome;
    private String password;
    private String email;
    private String telefone;
    private String RA;
    private int modulo;

    public UsuarioVO(String nome, String password, String email, String telefone, String RA, int modulo) {
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.telefone = telefone;
        this.RA = RA;
        this.modulo = modulo;
    }

    public UsuarioVO() {
    }

    public int getModulo() {
        return modulo;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRA() {
        return RA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

}
