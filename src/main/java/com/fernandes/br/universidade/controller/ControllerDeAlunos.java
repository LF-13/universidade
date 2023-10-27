package com.fernandes.br.universidade.controller;


import com.fernandes.br.universidade.model.ModelAluno;
import com.fernandes.br.universidade.repository.RepositoryUniversidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/universidade")
public class ControllerDeAlunos {

    @Autowired
    private RepositoryUniversidade repositoryUniversidade;

    @GetMapping
    public List<ModelAluno> listar() {
        var listaAluno = this.repositoryUniversidade.findAll();
        return listaAluno;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<ModelAluno> buscar(@PathVariable UUID matricula) {
        Optional<ModelAluno> alunoBusca = repositoryUniversidade.findById(matricula);
        if (alunoBusca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.OK).body(alunoBusca.get());

    }

    @PostMapping
    public ResponseEntity<ModelAluno> cadastrar(@RequestBody ModelAluno aluno) {

        var alunoSalvo = this.repositoryUniversidade.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<ModelAluno> atualizar(@PathVariable UUID matricula, @RequestBody ModelAluno aluno) {
        Optional<ModelAluno> alunoRepository = repositoryUniversidade.findById(matricula);

        if (alunoRepository.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (aluno.getNome() != null) {
            alunoRepository.get().setNome(aluno.getNome());
        }
        if (aluno.getEmail() != null) {
            alunoRepository.get().setEmail(aluno.getEmail());
        }
        ModelAluno salvou = repositoryUniversidade.save(alunoRepository.get());
        return ResponseEntity.status(HttpStatus.OK).body(salvou);

    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable UUID matricula) {
        Optional<ModelAluno> alunoOptional = repositoryUniversidade.findById(matricula);

        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repositoryUniversidade.deleteById(matricula);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}