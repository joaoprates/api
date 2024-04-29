package prates.desenvolvimento.api.domain.usuario;

public record DadosAutenticacao(String login, String senha) {

    public String login() {
        return login;
    }

    public String senha() {
        return senha;
    }
}
