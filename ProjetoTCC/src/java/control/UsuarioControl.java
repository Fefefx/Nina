/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import modelo.UsuarioModel;
import valuesObjects.UsuarioVO;
import valuesObjects.EmailVO;
import services.Senha;
import services.Email;
import services.Criptografia;

/**
 * Define as regras de negócio da entidade Usuário.
 * @author felipe
 */
public class UsuarioControl {

    UsuarioModel modelo = new UsuarioModel();

    /**
     * Insere um usuário no banco de dados. Verifica se o email dele já existe
     * no banco de dados, se sim retorna uma mensagem pedindo que o usuário
     * digite outro email, senão insere o usuário no banco de dados e retorna
     * a palavra inserido indicando o sucesso da operação. Caso dê algum erro durante
     * o processo retorna o mesmo.
     *
     * @author Felipe Mantoan Pardim
     * @param user O usuário que será inserido no banco de dados.
     * @return String - Se o usuário foi inserido ou não no banco de dados.
     */
    public String inserirUsuario(UsuarioVO user) {
        String teste = modelo.verificarEmail(user.getEmail());
        if (teste != null) {
            if (teste.equals("existe")) {
                return "O Email digitado já existe no banco de dados. Tente outro.";
            } else {
                return teste;
            }
        } else {
            teste = modelo.inserirUsuario(user);
            if (teste != null) {
                return teste;
            }
        }
        return "inserido";
    }

    /**
     * Verifica se o login digitado é válido. Se sim retorna null, senão retorna
     * a frase 'Usuário incorreto'. Caso dê erro retorna o mesmo.
     *
     * @author Felipe Mantoan Pardim
     * @param email E-mail do usuário
     * @param pass Senha do usuário
     * @return String - Se o usuário é válido ou não
     */
    public String verificarLogin(String email, String pass) {
        return modelo.verificarLogin(email, pass);
    }

    /**
     * Gera uma senha temporária e envia para o email do usuário. Verifica se o
     * email informado é válido ou não. Se for válido gera uma senha aleatória,
     * atualiza esse campo no banco de dados e envia um Email com a nova senha
     * para o usuário, retornando enviado ao final.
     *
     * @author Felipe Mantoan Pardim
     * @param email E-mail do usuário
     * @return String - Informe da recuperação de senha
     */
    public String recuperarLogin(String email) {
        String valor = modelo.verificarEmail(email);
        if (valor == null) {
            return "Email incorreto ou não cadastrado no sistema. Tente novamente.";
        } else {
            if (valor.equals("existe")) {
                String pass = Senha.senhaAleatoria();
                valor = modelo.alterarSenha(email,Criptografia.criptografiaSHA256(pass));
                if (valor != null) {
                    return valor;
                } else {
                    EmailVO mensagem = new EmailVO();
                    mensagem.setEmailDestinatario(email);
                    mensagem.setAssunto("Recuperação de Senha");
                    mensagem.setMsg("Você solicitou a recuperação de senha. Sua nova senha temporária é: " + pass);
                    boolean validar = Email.enviarHotmail(mensagem);
                    if (!validar) {
                        return "Erro ao enviar o email de recuperação de senha";
                    } else {
                        return "enviado";
                    }
                }
            } else {
                return valor;
            }
        }
    }

    /**
     * Atualiza as informações de um usuário no banco de dados. Retorna null
     * quando a atualização for feita com sucesso. Pode gerar erro ao realizar
     * consulta de informações.
     *
     * @author Felipe Mantoan Pardim
     * @param info Informações do usuário que serão atualizadas
     * @return String - O resultado da atualização
     */
    public String alterarDados(UsuarioVO info) {
        return modelo.alterarDados(info);
    }

    /**
     * Localiza um usuário no banco de dados a partir de seu email e retorna os
     * dados do mesmo. Caso ele não seja encontrado retorna null. Se der algum
     * erro imprime o mesmo no log do servidor.
     *
     * @author Felipe Mantoan Pardim
     * @param email O E-mail do usuário que deverá ser localizado.
     * @return UsuarioVO Os dados do usuário que foi localizado.
     */
    public UsuarioVO pesquisarUsuario(String email) {
        return modelo.pesquisarUsuario(email);
    }
    
    /**
     * Atualiza a senha de um usuário no banco de dados. Retorna null quando a
     * atualização for feita com sucesso, caso contrário retorna a frase 
     * 'Nenhuma senha foi atualizada'. Pode gerar erro ao realizar atualização.
     * 
     * @param email O email do usuário que tenha a senha atualizada
     * @param pass A nova senha do usuário
     * @return String - A efetivação ou não da atualização.
     */
    public String alterarSenha(String email,String pass){
        return modelo.alterarSenha(email, pass);
    }
    
}
