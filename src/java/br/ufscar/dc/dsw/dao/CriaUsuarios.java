package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        PapelDAO papelDAO = new PapelDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
	ClienteDAO clienteDAO = new ClienteDAO();
	LocadoraDAO locadoraDAO = new LocadoraDAO();

        // Criando Usuario admin com papel ROLE_ADMIN
     
        Usuario u1 = new Usuario();
        u1.setEmail("admin@admin");
        u1.setSenha(encoder.encode("admin"));
        u1.setAtivo(1);
        usuarioDAO.save(u1);

        Papel p1 = new Papel();
        p1.setNome("ROLE_ADMIN");
        papelDAO.save(p1);

        u1.getPapel().add(p1);
        usuarioDAO.update(u1);

        // Criando Usuario user com papel ROLE_USER
        
        Usuario u2 = new Usuario();
        u2.setEmail("user@user");
        u2.setSenha(encoder.encode("user"));
        u2.setAtivo(1);
        usuarioDAO.save(u2);

        Papel p2 = new Papel();
        p2.setNome("ROLE_USER");
        papelDAO.save(p2);

        u2.getPapel().add(p2);
        usuarioDAO.update(u2);

	// Teste Cliente

	Cliente c = new Cliente();
        c.setId(11111L);
        c.setCpf("44411122200");
        c.setNome("Ana Silva");
	c.setTelefone("38881111");
        c.setSexo("feminino");
        c.setNascimento("05/05/1980");
        c.setEmail("cliente@cliente");
        c.setSenha(encoder.encode("cliente"));
        c.setAtivo(1);
        clienteDAO.save(c);

        Papel p3 = new Papel();
        p3.setNome("ROLE_CLIENTE");
        papelDAO.save(p3);

        c.getPapel().add(p3);
        clienteDAO.update(c);

	// Teste Locadora
	Locadora l = new Locadora();
        l.setId(22222L);
        l.setNome("Locadora Top");
        l.setCidade("São Carlos");
        l.setCnpj("7777888899");
        l.setEmail("locadora@locadora");
        l.setSenha(encoder.encode("locadora"));
        l.setAtivo(1);
        locadoraDAO.save(l);

        Papel p4 = new Papel();
        p4.setNome("ROLE_LOCADORA");
        papelDAO.save(p4);

        l.getPapel().add(p4);
        locadoraDAO.update(l);


    }
}

