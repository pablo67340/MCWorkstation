/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.snippets;

/**
 *
 * @author Bryce
 */
public class Snippet {
    
    private String name, description, returns, snippet;
    
    public Snippet(String name, String description, String returns, String snippet){
        this.name = name;
        this.description = description;
        this.returns = returns;
        this.snippet = snippet;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getReturns(){
        return returns;
    }
    
    public String getSnippet(){
        return snippet;
    }
    
    public static class Builder {
    
    private String name, description, returns, snippet;
    
    public Builder(){
        
    }
    
    public Builder withName(String input){
        name = input;
        return this;
    }
    
    public Builder withDescription(String input){
        description = input;
        return this;
    }
    
    public Builder withReturns(String input){
        returns = input;
        return this;
    }
    
    public Builder withSnippet(String input){
        snippet = input;
        return this;
    }
    
    public Snippet build(){
        return new Snippet(name, description, returns, snippet);
    }
}
    
}


