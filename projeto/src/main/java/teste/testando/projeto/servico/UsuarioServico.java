package teste.testando.projeto.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import teste.testando.projeto.model.Usuario;
import teste.testando.projeto.model.UsuarioMensagem;
import teste.testando.projeto.repositorio.Repositorio;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioMensagem mensagem;
    
    @Autowired
    private Repositorio acao;
    
    public ResponseEntity<?> cadastrar(Usuario u){
        
        if(u.getNome().equals("") || u.getCidade().equals("")){
            mensagem.setMensagem("O nome ou a cidade deve ser preenchido!");
            return  new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return  ResponseEntity.status(201).body(acao.save(u));
        }

    }

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public  ResponseEntity<?> selecionarPeloCodigo(int id) {
        if (acao.countById(id) == 0){
            mensagem.setMensagem("Não foi encontrado nenhum usuario");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
        }
    }
    
    public ResponseEntity<?> editar(Usuario u) {
        if (acao.countById(u.getId()) == 0) {
            mensagem.setMensagem("O Id informado não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (u.getNome().equals("")) {
            mensagem.setMensagem("Necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (u.getCidade().equals("")) {
            mensagem.setMensagem("Necessário informar uma cidade");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(acao.save(u), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int id){
        if (acao.countById(id) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else {
            acao.delete(acao.findById(id));
            mensagem.setMensagem("Pessoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
