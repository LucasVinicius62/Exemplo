package controller;

import entity.Pet;
import java.util.ArrayList;
import util.Alexa;

import java.sql.*;

import connection.FabricaConexao;

public class PetsDao {

    public ArrayList<Pet> pets = new ArrayList<Pet>();

    public boolean inserir(Pet pet) {
        //pets.add(pet);
        Connection conexao = (Connection) FabricaConexao.getConnection();
        try {
            //INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) 
            //VALUES ('1','Jose','Masc','6299999-9999','Rua 1','01-01-1960');
            PreparedStatement pstm = (PreparedStatement) conexao.prepareStatement("INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) VALUES (?,?,?,?,?,?);");
            pstm.setInt(1, pet.getMatricula());
            pstm.setString(2, pet.getNome());
            pstm.setString(3, pet.getGenero());
            pstm.setString(4, pet.getFone());
            pstm.setString(5, pet.getEndereco());
            pstm.setString(6, pet.getDtNasc());

            int linhas = pstm.executeUpdate();
            conexao.close();
            return (linhas > 0) ? true : false;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return false;
    }

    public boolean atualizar(Pet pet) {
        //UPDATE pet set nome='Jose Maria' WHERE id = 1;

        Connection conexao = (Connection) FabricaConexao.getConnection();
        try {
            //INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) 
            //VALUES ('1','Jose','Masc','6299999-9999','Rua 1','01-01-1960');
            PreparedStatement pstm = (PreparedStatement) conexao.prepareStatement("UPDATE pet set matricula=?, nome=?, genero=? ,fone=? ,endereco=? ,dt_nasc=? WHERE id = ?;");
            pstm.setInt(1, pet.getMatricula());
            pstm.setString(2, pet.getNome());
            pstm.setString(3, pet.getGenero());
            pstm.setString(4, pet.getFone());
            pstm.setString(5, pet.getEndereco());
            pstm.setString(6, pet.getDtNasc());
            pstm.setInt(7, pet.getId());

            int linhas = pstm.executeUpdate();
            conexao.close();
            return (linhas > 0) ? true : false;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return false;
    }
    
    public boolean excluir(Integer id) {
        //UPDATE pet set nome='Jose Maria' WHERE id = 1;

        Connection conexao = (Connection) FabricaConexao.getConnection();
        try {
            //INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) 
            //VALUES ('1','Jose','Masc','6299999-9999','Rua 1','01-01-1960');
            PreparedStatement pstm = (PreparedStatement) conexao.prepareStatement("DELETE from pet WHERE id = ?;");
            pstm.setInt(1, id);

            int linhas = pstm.executeUpdate();
            conexao.close();
            return (linhas > 0) ? true : false;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return false;
    }
    
    public Pet buscar(Integer id) {
        //UPDATE pet set nome='Jose Maria' WHERE id = 1;
        Pet pet = new Pet();
        Connection conexao = (Connection) FabricaConexao.getConnection();
        try {
            //INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) 
            //VALUES ('1','Jose','Masc','6299999-9999','Rua 1','01-01-1960');
            PreparedStatement pstm = (PreparedStatement) conexao.prepareStatement("SELECT * from Pet WHERE id = ?;");
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setGenero(rs.getString("genero"));
                pet.setFone(rs.getString("fone"));
                pet.setEndereco(rs.getString("endereco"));
                pet.setDtNasc(rs.getString("dt_nasc"));
            }
            
            conexao.close();
            
            return pet;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return null;
    }
    
    public ArrayList<Pet> getListaPets() {
        //UPDATE pet set nome='Jose Maria' WHERE id = 1;
        Pet pet = new Pet();
        ArrayList<Pet> pets = new ArrayList<Pet>();
        Connection conexao = (Connection) FabricaConexao.getConnection();
        try {
            //INSERT INTO pet (matricula,nome,genero,fone,endereco,dt_nasc) 
            //VALUES ('1','Jose','Masc','6299999-9999','Rua 1','01-01-1960');
            PreparedStatement pstm = (PreparedStatement) conexao.prepareStatement("SELECT * from pet;");
 

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setGenero(rs.getString("genero"));
                pet.setFone(rs.getString("fone"));
                pet.setEndereco(rs.getString("endereco"));
                pet.setDtNasc(rs.getString("dt_nasc"));
                pets.add(pet);
            }
            
            conexao.close();
            
            return pets;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return null;
    }

    public Pet buscar(Pet petBuscar) {
        Pet pet = new Pet ();
        pet.setNome("");
        for (Pet  a : pets) {
            if (a.getNome().equalsIgnoreCase(petBuscar.getNome())) {
                pet = a;
            }
        }
        return pet;
    }

    public boolean excluir(Pet pet) {
        for (Pet a : pets) {
            if (a.getNome().equalsIgnoreCase(pet.getNome())) {
                pets.remove(a);
            }
        }
        return true;
    }

    public ArrayList<Pet> listar() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        return pets;
    }

}
