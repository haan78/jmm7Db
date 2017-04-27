/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

/**
 *
 * @author BARIS
 */
public abstract class MyProcedureParam {    
    
    protected String name = null;

    public final void setName(String name) {
        if (name == null) {
            this.name = null;
        } else if ( name.isEmpty() ) {
            this.name = null;
        } else {
            this.name = name;
        }
        
    }

    public final String getName() {
        return name;
    }
    
    public abstract int getSQLType();
    
    public abstract Object getParamValue();
    
}
