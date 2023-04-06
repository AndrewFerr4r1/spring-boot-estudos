package teste.testando.projeto.controller;

import com.mysql.cj.xdevapi.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.testando.projeto.model.Usuario;
import teste.testando.projeto.repositorio.Repositorio;
import teste.testando.projeto.servico.UsuarioServico;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private Repositorio acao;

    @Autowired
    private UsuarioServico servico;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Usuario u){
        return servico.cadastrar(u);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Usuario u){
        return servico.editar(u);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
        return servico.remover(codigo);
    }

    @GetMapping("/api/contador")
    public ResponseEntity<Long> contador(){
        return ResponseEntity.status(200).body(acao.count());
    }

    @GetMapping("/api/ordenarNomes")
    public ResponseEntity<List<Usuario>> ordenarNomes(){
        return ResponseEntity.status(200).body(acao.findByOrderByNomeDesc());
    }

    @GetMapping("/api/ordenarNomes/{nome}")
    public  ResponseEntity<List<Usuario>> ordenarNomes2(@PathVariable String nome){
        return ResponseEntity.status(200).body(acao.findByNomeOrderByCidade(nome));
    }

    @GetMapping("/api/nomeContem/{termo}")
    public ResponseEntity<List<Usuario>> NomeContem(@PathVariable String termo){
        return  ResponseEntity.status(200).body(acao.findByNomeContaining(termo));
    }

    @GetMapping("/api/iniciaCom/{termo}")
    public ResponseEntity<List<Usuario>> iniciaCom(@PathVariable String termo) {
        return ResponseEntity.status(200).body(acao.findByNomeStartsWith(termo));
    }

    @GetMapping("/api/terminaCom/{termo}")
    public ResponseEntity<List<Usuario>> terminaCom(@PathVariable String termo) {
        return ResponseEntity.status(200).body(acao.findByNomeEndsWith(termo));
    }

    @GetMapping("/api/pesquisa/{id}")
    public ResponseEntity<List<Usuario>> pesquisaPorId(@PathVariable int id) {
        return ResponseEntity.status(200).body(acao.pesquisarPorId(id));
    }

    @GetMapping
    public String teste(){
        return "Hello word";
    }

}
