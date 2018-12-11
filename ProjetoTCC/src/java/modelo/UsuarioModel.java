/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import info.Banco;
import valuesObjects.UsuarioVO;

/**
 * Trata das manipulações de dados da entidade usuário do banco de dados.
 * @author felipe
 */
public class UsuarioModel {

    Connection conectar = null;
    Statement acessar;

    /**
     * Efetua uma conexão com o Banco de Dados. Caso tudo dê certo retorna
     * null,senão devolve o erro ocorrido.
     *
     * @author Felipe Mantoan Pardim
     * @return String - Estado da conexão com o banco de dados
     * @throws ClassNotFoundException Não foi possível carregar o driver do
     * banco de dados
     * @throws SQLException Não foi possível acessar o banco de dados
     * @throws Exception Erro gerérico ao efetuar conexão com o banco de dados
     */
    private String criarConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Banco info = new Banco();
            conectar = DriverManager.getConnection(info.getUrl(), info.getUser(), info.getPassword());
        } catch (ClassNotFoundException clex) {
            return "Erro ao carregar o driver do MYSQL: " + clex;
        } catch (SQLException sqlex) {
            return "Erro ao acessar o banco de dados: " + sqlex;
        } catch (Exception ex) {
            return "Erro: " + ex;
        }
        return null;
    }

    /**
     * Verifica se um email existe no banco de dados. Caso exista retorna a
     * palavra 'existe' senão 'null'.
     *
     * @author Felipe Mantoan Pardim
     * @param email Email que será verificado.
     * @throws SQLException Erro de instrução SQL
     * @return String - A existência ou não do email no banco de dados
     */
    public String verificarEmail(String email) {
        criarConexao();
        try {
            acessar = conectar.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String sql = "select count(*) as verificar from usuario where email='" + email + "';";
            ResultSet dado = acessar.executeQuery(sql);
            if (dado.next()) {
                int valor = dado.getInt("verificar");
                if (valor == 1) {
                    dado.close();
                    return "existe";
                }
            }
        } catch (SQLException sqlex) {
            return "Erro de SQL: " + sqlex;
        }
        return null;
    }

    /**
     * Insere um usuário no banco de dados. Retorna null caso o registro tenha
     * sido inserido com sucesso.
     *
     * @author Felipe Mantoan Pardim
     * @param user O usuário que será inserido no banco de dados
     * @throws SQLException Erro de instrução SQL
     * @return String - Indica se o registro foi inserido ou não no banco de
     * dados.
     */
    public String inserirUsuario(UsuarioVO user) {
        criarConexao();
        try {
            String sql = "INSERT INTO usuario (`email`, `password`, `nome`, `telefone`, `RA`, `modulo`) VALUES (?,?,?,?,?,?);";
            PreparedStatement sqlinsere = conectar.prepareStatement(sql);
            sqlinsere.setString(1, user.getEmail());
            sqlinsere.setString(2, user.getPassword());
            sqlinsere.setString(3, user.getNome());
            sqlinsere.setString(4, user.getTelefone());
            sqlinsere.setString(5, user.getRA());
            sqlinsere.setInt(6, user.getModulo());
            sqlinsere.execute();
            sqlinsere.close();
        } catch (SQLException sqlex) {
            return "Erro de SQL: " + sqlex;
        }
        return null;
    }

    /**
     * Verifica se o login digitado é válido. Se sim retorna null, senão retorna
     * a frase 'Usuário incorreto.'
     *
     * @author Felipe Mantoan Pardim
     * @param email E-mail do usuário
     * @param pass Senha do usuário
     * @throws SQLException Erro de instrução SQL
     * @return String - Se o usuário é válido ou não
     */
    public String verificarLogin(String email, String pass) {
        criarConexao();
        try {
            acessar = conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select count(*) as verdade from usuario where email='" + email + "' and password='" + pass + "';";
            ResultSet dado = acessar.executeQuery(sql);
            if (dado.next()) {
                int valor = dado.getInt("verdade");
                if (valor == 0) {
                    dado.close();
                    return "Usuário incorreto";
                }
            }
        } catch (SQLException sqlex) {
            return "Erro de SQL: " + sqlex;
        }
        return null;
    }

    /**
     * Atualiza a senha do usuário. Retorna null quando a atualização for feita
     * com sucesso, caso contrário retorna a frase 'Nenhuma senha foi
     * atualizada'.
     *
     * @author Felipe Mantoan Pardim
     * @param email O email do usuário que terá a senha atualizada
     * @param pass A nova senha que será introduzida no banco
     * @throws SQLException Erro de instrução SQL
     * @return String - O estado da alteração da senha
     */
    public String alterarSenha(String email, String pass) {
        criarConexao();
        try {
            acessar = conectar.createStatement();
            String sql = "update usuario set password='" + pass + "' where email='" + email + "';";
            int valor = acessar.executeUpdate(sql);
            if (valor == -1) {
                return "Nenhuma senha foi atualizada";
            }
        } catch (SQLException sqlex) {
            return "Erro de SQL: " + sqlex;
        }
        return null;
    }

    /**
     * Atualiza as informações de um usuário no banco de dados. Retorna null
     * quando a atualização for feita com sucesso.
     *
     * @author Felipe Mantoan Pardim
     * @param info Informações do usuário que serão atualizadas
     * @throws SQLException Erro de instrução SQL
     * @return String - O resultado da atualização
     */
    public String alterarDados(UsuarioVO info) {
        criarConexao();
        try {
            String sql = "UPDATE usuario SET password = ?, nome = ?, telefone = ?, RA = ?, modulo = ? WHERE email = ?;";
            PreparedStatement sqlAltera = conectar.prepareStatement(sql);
            sqlAltera.setString(1, info.getPassword());
            sqlAltera.setString(2, info.getNome());
            sqlAltera.setString(3, info.getTelefone());
            sqlAltera.setString(4, info.getRA());
            sqlAltera.setInt(5, info.getModulo());
            sqlAltera.setString(6, info.getEmail());
            sqlAltera.execute();
            sqlAltera.close();
        } catch (SQLException sqlex) {
            return "Erro de SQL: " + sqlex;
        }
        return null;
    }

    /**
     * Localiza um usuário no banco de dados a partir de seu email e retorna os
     * dados do mesmo. Caso ele não seja encontrado retorna null.
     *
     * @author Felipe Mantoan Pardim
     * @param email O E-mail do usuário que deverá ser localizado.
     * @throws SQLException Erro de instrução SQL
     * @return UsuarioVO Os dados do usuário localizado
     */
    public UsuarioVO pesquisarUsuario(String email) {
        criarConexao();
        try {
            acessar = conectar.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String sql = "SELECT * FROM usuario WHERE email='" + email + "';";
            ResultSet dados = acessar.executeQuery(sql);
            if (dados.next()) {
                UsuarioVO usuarioLocalizado = new UsuarioVO();
                usuarioLocalizado.setEmail(email);
                usuarioLocalizado.setPassword(dados.getString("password"));
                usuarioLocalizado.setNome(dados.getString("nome"));
                usuarioLocalizado.setTelefone(dados.getString("telefone"));
                usuarioLocalizado.setRA(dados.getString("RA"));
                usuarioLocalizado.setModulo(dados.getInt("modulo"));
                dados.close();
                return usuarioLocalizado;
            }
        } catch (SQLException sqlex) {
            throw new RuntimeException(sqlex);
        }
        return null;
    }
}
