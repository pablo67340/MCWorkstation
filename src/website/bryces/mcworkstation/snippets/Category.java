/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.snippets;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Bryce
 */
public class Category {
    
    Set<Snippet> snippets = new HashSet<>();
    
    private String name;
    
    public Category(String input){
        name = input;
    }
    
    public void setName(String input){
        this.name = input;
    }
    
    public String getName(){
        return name;
    }
    
    public void addSnippet(Snippet input){
        snippets.add(input);
    }
    
    public Set<Snippet> getSnippets(){
        return snippets;
    }
    
    
}
