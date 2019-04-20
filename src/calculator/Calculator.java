/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.ArrayList;

/**
 *
 * @author ccezar
 */
public class Calculator {

    private String equacao;
    private ArrayList<Operador> operadores;
    private ArrayList<Double> numeros;

    public Calculator(String equacao) {
        operadores = new ArrayList<Operador>();
        numeros = new ArrayList<Double>();
        setEquacao(equacao);
    }

    public void setEquacao(String equacao) {
        this.equacao = equacao;
        separar();
    }
    
    private boolean testeParenteses() {
        int parenteses = 0;
        for(char x : equacao.toCharArray()){
            if(x == '('){
                parenteses++;
            }else if(x == ')'){
                parenteses--;
            }
            if(parenteses < 0) {
                return false;
            }
        }
        boolean retorno = (parenteses!=0?false:true);
        return retorno;
    }
    
    private void separar() {
        if (testeParenteses()) {
            ArrayList<String> numero = new ArrayList<String>();
            int parenteses = 0;
            for (int i = 0; i < equacao.toCharArray().length; i++) {
                char x = equacao.toCharArray()[i];
                boolean numeroAntes = true;
                if (x == '(') {
                    parenteses += 10;
                } else if (x == ')') {
                    parenteses -= 10;
                } else if (x == '+') {
                    operadores.add(new Operador(x, 1 + parenteses));
                    numeroAntes = false;
                } else if (x == '-') {
                    operadores.add(new Operador(x, 1 + parenteses));
                    numeroAntes = false;
                } else if (x == '*') {
                    operadores.add(new Operador(x, 2 + parenteses));
                    numeroAntes = false;
                } else if (x == '/') {
                    operadores.add(new Operador(x, 2 + parenteses));
                    numeroAntes = false;
                } else if (x == '^') {
                    operadores.add(new Operador(x, 3 + parenteses));
                    numeroAntes = false;
                } else {
                    numero.add(x + "");
                    numeroAntes = true;
                }
                if ((numeroAntes == false && numero.size() != 0) || i + 1 == equacao.toCharArray().length) {
                    String doubleNumero = numero.toString().substring(1, numero.toString().length() - 1).replace(", ", "");
                    try {                 
                        numeros.add(Double.parseDouble(doubleNumero));
                        numero = new ArrayList<String>();
                    } catch (NumberFormatException e) {
                        out("Caracter invalido: " + e.getMessage());
                    } catch (Exception e) {
                        out(e.getMessage());
                    }
                }
            }
        }
    }

    public Double calculoEquacao() {
        if(numeros.size() < 1) {
            return numeros.get(0);
        }
        while (operadores.size() != 0) {
                int maior = 0;
                for (int j = 0; j < operadores.size(); j++) {
                    if (operadores.get(j).prioridade > operadores.get(maior).prioridade) {
                        maior = j;
                    }
                }
                numeros.set(maior, calculo(numeros.get(maior), numeros.get(maior + 1), operadores.get(maior).simbulo));
                numeros.remove(maior + 1);
                operadores.remove(maior);
        }
        return numeros.get(0);
    }
    
    public Double calculo(Double valor1, Double valor2, char operador) {
        Double resultado = 0.0;
            switch (operador) {
                case '+':
                    resultado = valor1 + valor2;
                    break;
                case '-':
                    resultado = valor1 - valor2;
                    break;
                case '/':
                    resultado = valor1 / valor2;
                    break;
                case '*':
                    resultado = valor1 * valor2;
                    break;
                case '^':
                    resultado = Math.pow(valor2, valor1);
                    break;
            }
        return resultado;
    }
    private void out(String msg) {
        System.out.println(msg);
    }
}
