/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author ccezar
 */
class Operador {
    public char simbulo;
    public int prioridade;
    public Operador(char simbulo, int prioridade){
        this.simbulo = simbulo;
        this.prioridade = prioridade;
    }
}
