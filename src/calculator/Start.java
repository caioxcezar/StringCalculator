/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Scanner;

/**
 *
 * @author ccezar
 */
public class Start {
    public static void main(String[] args){
        System.out.println("Digite a equação: ");
        Scanner sc = new Scanner(System.in);
        String equacao = sc.next();
        Calculator cl = new Calculator(equacao);
        System.out.println(cl.calculoEquacao());
    }
}
