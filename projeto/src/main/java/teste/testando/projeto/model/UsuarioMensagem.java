package teste.testando.projeto.model;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMensagem {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
