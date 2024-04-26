package med.voll.api.infra.exception;

public class ValidacaoNegocioException extends RuntimeException{

    public ValidacaoNegocioException(String mensagem){
        super(mensagem);
    }

}
