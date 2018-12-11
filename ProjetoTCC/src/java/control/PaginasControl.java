/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.PaginasModel;
import valuesObjects.PaginaVO;

/**
 * Define as regras de negócio da entidade página.
 * @author felipe
 */
public class PaginasControl {
    /**
     * Localiza o conteúdo de uma aula no banco de dados e retorna as informações da mesma.
     * Caso não encontre a aula retorna null. Pode gerar erro ao realizar consulta de informações.
     * @author Felipe Mantoan Pardim
     * @param codigo O codigo da aula que deverá ser localizada no banco de dados.
     * @return PaginaVO - O conteúdo da aula que deve ser exibida.
     */
    public PaginaVO buscarConteudo(int codigo) {
        return new PaginasModel().buscarConteudo(codigo);
    }
    /**
     * Localiza as informações das aulas disponíveis no banco de dados.
     * Retorna uma breve descrição das aulas.
     * Pode gerar erro ao realizar consulta de informações.
     * @author Felipe Mantoan Pardim
     * @return ArrayList - Uma lista contendo as informações das aulas
     */
    public ArrayList buscarAulas() {
        return new PaginasModel().buscarAulas();
    }
    /**
     * Retorna o código da primeira e última aulas armazenadas no banco de dados.
     * Pode gerar erro ao realizar consulta de informações.
     * @author Felipe Mantoan Pardim
     * @return ArrayList - Uma lista contendo os códigos da primeira e última aula. 
     */
    public ArrayList primeiraUltimaAula() {
        return new PaginasModel().primeiraUltimaAula();
    }
}
