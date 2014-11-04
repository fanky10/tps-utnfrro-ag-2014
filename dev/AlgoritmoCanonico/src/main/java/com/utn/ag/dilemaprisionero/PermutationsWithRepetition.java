/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.dilemaprisionero;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fanky
 */
public class PermutationsWithRepetition {

    private String a;
    private int n;

    public PermutationsWithRepetition(String a, int n) {
        this.a = a;
        this.n = n;
    }
    
    public PermutationsWithRepetition(String[] a) {
        StringBuilder sb = new StringBuilder();
        for(String s: a){
            sb.append(s);
        }
        this.a = sb.toString();
        this.n = a.length;
    }

    public List<String> getVariations() {
        int l = a.length();
        int permutations = (int) Math.pow(l, n);
        char[][] table = new char[permutations][n];
        for (int x = 0; x < n; x++) {
            int t2 = (int) Math.pow(l, x);
            for (int p1 = 0; p1 < permutations;) {
                for (int al = 0; al < l; al++) {
                    for (int p2 = 0; p2 < t2; p2++) {
                        table[p1][x] = a.charAt(al);
                        p1++;
                    }
                }
            }
        }
        List<String> result = new ArrayList<String>();
        for (char[] permutation : table) {
            result.add(new String(permutation));
        }
        return result;
    }

    public static void main(String[] args) {
        PermutationsWithRepetition gen = new PermutationsWithRepetition("an", 2);
        List<String> v = gen.getVariations();
        System.out.println("String given");
        for (String s : v) {
            System.out.println(s);
        }
        System.out.println("String[] given");
        gen = new PermutationsWithRepetition(new String[]{"a","n"});
        v = gen.getVariations();
        for (String s : v) {
            System.out.println(s);
        }
    }
}
