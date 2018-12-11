/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import info.Banco;
import java.util.ArrayList;
import valuesObjects.PaginaVO;
import valuesObjects.AulaVO;

/**
 * Trata das manipulações de dados da entidade página do banco de dados.
 * @author felipe
 */
public class PaginasModel {

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
     * Localiza o conteúdo de uma aula no banco de dados e retorna as informações da mesma.
     * Caso não encontre a aula retorna null. 
     * 
     * @author Felipe Mantoan Pardim
     * @throws SQLException Erro de instrução SQL 
     * @param codigo O código da aula que deverá ser localizada no banco de dados.
     * @return PaginaVO - O conteúdo da aula que deve ser exibida. 
     */
    public PaginaVO buscarConteudo(int codigo) {
        criarConexao();
        try {
            String sql = "select * from paginas where codigo=" + codigo + ";";
            acessar = conectar.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet buscar = acessar.executeQuery(sql);
            if (buscar.next()) {
                PaginaVO informacao = new PaginaVO();
                informacao.setCodigo(buscar.getInt("codigo"));
                informacao.setNome(buscar.getString("nome"));
                informacao.setConteudo(buscar.getString("conteudo"));
                informacao.setImagem(buscar.getString("Imagem"));
                buscar.close();
                return informacao;
            }
        } catch (SQLException sqlex) {
            throw new RuntimeException(sqlex);
        }
        return null;
    }
    /**
     * Localiza as informações das aulas disponíveis no banco de dados. Retorna uma breve descrição das aulas. 
     * @author Felipe Mantoan Pardim
     * @throws SQLException Erro de instrução SQL
     * @return ArrayList - Uma lista contendo informações de aulas  
     */
    public ArrayList buscarAulas(){
        criarConexao();
        ArrayList listaAulas = new ArrayList();
        try{
            String sql = "select codigo, nome, Imagem from paginas";
            acessar = conectar.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet aulas = acessar.executeQuery(sql);
            while(aulas.next()){
                AulaVO materia = new AulaVO();
                materia.setCodigo(aulas.getInt("codigo"));
                materia.setNome(aulas.getString("nome"));
                materia.setImagem(aulas.getString("Imagem"));
                listaAulas.add(materia);
            }
            aulas.close();
            return listaAulas;
        }catch(SQLException sqlex){
            throw new RuntimeException(sqlex);
        }
    }
    /**
     * Retorna o código da primeira e última aulas armazenadas no banco de dados.
     * @author Felipe Mantoan Pardim
     * @throws SQLException Erro de instrução SQL
     * @return ArrayList - Uma lista contendo os códigos da primeira e última aula 
     */
    public ArrayList primeiraUltimaAula(){
        criarConexao();
        ArrayList limites = new ArrayList();
        try{
            String sql="select min(codigo) as minimo, max(codigo) as maximo from paginas;";
            acessar = conectar.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet dados = acessar.executeQuery(sql);
            if(dados.next()){
               limites.add(dados.getInt("minimo"));
               limites.add(dados.getInt("maximo"));
               dados.close();
               return limites;
            }
        }catch(SQLException sqlex){
            throw new RuntimeException(sqlex);
        }
        return null;
    }
}
