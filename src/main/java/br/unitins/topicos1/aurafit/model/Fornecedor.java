package br.unitins.topicos1.aurafit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A razão social é obrigatória.")
    @Column(name = "razao_social", nullable = false, length = 120)
    private String razaoSocial;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Column(name = "cnpj", nullable = false, length = 18, unique = true)
    private String cnpj;

    @NotBlank(message = "O telefone é obrigatório.")
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Email(message = "O e-mail informado é inválido.")
    @Column(name = "email", length = 100)
    private String email;

    public Fornecedor() {
    }

    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}